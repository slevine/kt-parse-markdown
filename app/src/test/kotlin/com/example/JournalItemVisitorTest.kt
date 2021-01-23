package com.example

import kotlin.test.Test
import kotlin.test.assertNotNull

class JournalItemVisitorTest {
    @Test
    fun testAppHasAGreeting() {
        val classUnderTest = JournalItemVisitor()
        assertNotNull(classUnderTest.visitor, "NodeVisitor was null")
    }
}
