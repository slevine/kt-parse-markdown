package com.example

import com.vladsch.flexmark.ast.BulletListItem
import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.NodeVisitor
import com.vladsch.flexmark.util.ast.VisitHandler
import java.io.File

class JournalItemVisitor {

    private var month = ""
    private var week = ""
    private var day = ""

    private fun setDay(m: String) {
        day = m.split(" ").joinToString()
    }

    val visitor = NodeVisitor(
        VisitHandler(Heading::class.java) { processHeading(it) },
        VisitHandler(BulletListItem::class.java) { processItem(it) }
    )

    private fun processHeading(node: Heading) {
        val heading = node.chars.unescape().replace("#", "").trim()

        when (node.level) {
            1 -> month = heading
            2 -> week = heading
            3 -> setDay(heading)
        }
    }

    private fun processItem(node: BulletListItem) {
        fun convertToSingleLine(line: String) = line
            .replace("\n", "")
            .replace("-", ",")

        fun convertParen(line: String) = line
            .replaceFirst("(", ",")
            .replaceFirst(")", "")

        fun removeExtraItems(line: String) = line
            .split(",")
            .take(4).joinToString { it.trim() }

        fun generateCSVLine(item: String) =
            "$month,$week,$day${removeExtraItems(convertParen(convertToSingleLine(item)))}"

        println(generateCSVLine(node.chars.unescape()))
    }
}

fun setup(inFile: String) {
    val file = File(inFile)
    val mdText: String = file.readText()
    val document = Parser.builder().build().parse(mdText)

    println("Month,Week,Day,Date,Customer,Time,Task")
    JournalItemVisitor().visitor.visit(document)
}

fun main(args: Array<String>) {
    if (args.isEmpty())
        error("You must pass in a file path.")
    setup(args[0])
}