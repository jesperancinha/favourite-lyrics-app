package org.jesperancinha.lyrics.test

import org.jesperancinha.lyrics.test.Main.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class MainTest {
    @Test
    fun testMain() {
        Assertions.assertAll(Executable { main() })
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll(): Unit {
            ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true)
        }
    }
}