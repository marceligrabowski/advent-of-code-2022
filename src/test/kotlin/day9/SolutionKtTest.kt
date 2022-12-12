
package day9

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return amount of visited positions") {
            val result = readFile("day9_sample.txt")
                .map { it.body }
                .map { part1(it) }

            result shouldBe Some(13)
        }

        should("return amount of visited positions - part2") {
            val result = readFile("day9_sample.txt")
                .map { it.body }
                .map { part2(it) }

            result shouldBe Some(1)
        }

        should("return amount of visited positions - part2 s2") {
            val result = readFile("day9_sample2.txt")
                .map { it.body }
                .map { part2(it) }

            result shouldBe Some(36)
        }
    }

    context("solution") {
        should("return amount of visited positions") {
            readFile("day9.txt")
                .map { it.body }
                .map { part1(it) }
                .tap { println("Final solution - day 9 part 1: $it") }
        }

        should("return amount of visited positions - part2 ") {
            val result = readFile("day9.txt")
                .map { it.body }
                .map { part2(it) }
                .tap { println("Final solution - day 9 part 2: $it") }

        }
    }
})