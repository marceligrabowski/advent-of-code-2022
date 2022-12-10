package day7

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("sum of folders size <= 100000") {
            val result = readFile("day7_sample.txt")
                .map { it.body }
                .map { part1(it) }

            result shouldBe Some(95437)
        }

        should("folder size which should be deleted") {
            val result = readFile("day7_sample.txt")
                .map { it.body }
                .map { part2(it) }

            result shouldBe Some(24933642)
        }
    }

    context("solution") {
        should("sum of folders size <= 100000") {
            readFile("day7.txt")
                .map { it.body }
                .map { part1(it) }
                .tap { println("Final solution - day 7 part 1: $it") }

        }


        should("folder size which should be deleted") {
            val result = readFile("day7.txt")
                .map { it.body }
                .map { part2(it) }
                .tap { println("Final solution - day 7 part 2: $it") }
        }
    }
})