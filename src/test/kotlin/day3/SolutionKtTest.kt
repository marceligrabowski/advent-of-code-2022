package day3

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return total priority") {
            val result = readFile("day3_sample.txt")
                .map { it.body }
                .map { solution(it) }

            result shouldBe Some(157)

        }

        should("return total priority part2") {
            val result = readFile("day3_sample.txt")
                .map { it.body }
                .map { solutionPart2(it) }

            result shouldBe Some(70)

        }
    }
    context("solution") {
        should("return total priority") {
            readFile("day3.txt")
                .map { it.body }
                .map { solution(it) }
                .tap { println("Final solution - day 3 part 1: $it") }
        }

        should("return total priority - part2") {
            readFile("day3.txt")
                .map { it.body }
                .map { solutionPart2(it) }
                .tap { println("Final solution - day 3 part 2: $it") }
        }
    }
})