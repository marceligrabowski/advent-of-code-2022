
package day12

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return shortest path to E from S") {
            val result = readFile("day12_sample.txt")
                .map { it.body }
                .map { Day12(it).part1() }

            result shouldBe Some(31)
        }

        should("return shortest path to E from a") {
            val result = readFile("day12_sample.txt")
                .map { it.body }
                .map { Day12(it).part2() }

            result shouldBe Some(29)
        }
    }

    context("solution") {
        should("return shortest path to E from S") {
            readFile("day12.txt")
                .map { it.body }
                .map { Day12(it).part1() }
                .tap { println("Final solution - day 12 part 1: $it") }
        }

        should("return shortest path to E from a") {
            readFile("day12.txt")
                .map { it.body }
                .map { Day12(it).part2() }
                .tap { println("Final solution - day 12 part 2: $it") }
        }
    }
})