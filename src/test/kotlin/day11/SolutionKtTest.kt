
package day11

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return monkey business level") {
            val result = readFile("day11_sample.txt")
                .map { it.body }
                .map { part1(it) }

            result shouldBe Some(10605)
        }

        should("return monkey business level part2") {
            val result = readFile("day11_sample.txt")
                .map { it.body }
                .map { part2(it) }

            result shouldBe Some(10197)
        }
    }

    context("solution") {
        should("return monkey business level") {
            readFile("day11.txt")
                .map { it.body }
                .map { part1(it) }
                .tap { println("Final solution - day 11 part 1: $it") }
        }

        should("return monkey business level - part2") {
            readFile("day11.txt")
                .map { it.body }
                .map { part2(it) }
                .tap { println("Final solution - day 11 part 2: $it") }
        }
    }
})