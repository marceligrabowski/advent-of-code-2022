package day8

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return all visible trees") {
            val result = readFile("day8_sample.txt")
                .map { it.body }
                .map { part1(it) }

            result shouldBe Some(21)
        }

        should("return tree's best score") {
            val result = readFile("day8_sample.txt")
                .map { it.body }
                .map { part2(it) }

            result shouldBe Some(8)
        }
    }

    context("solution") {
        should("return all visible trees") {
            readFile("day8.txt")
                .map { it.body }
                .map { part1(it) }
                .tap { println("Final solution - day 8 part 1: $it") }
        }

        should("return tree's best score") {
            val result = readFile("day8.txt")
                .map { it.body }
                .map { part2(it) }
                .tap { println("Final solution - day 8 part 2: $it") }

        }
    }
})