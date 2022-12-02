package day2

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return total points from strategy") {
            val result = readFile("day2_sample.txt")
                .map { it.body }
                .map { solution(it) }

            result shouldBe Some(15)
        }

        should("return total points from strategy part2") {
            val result = readFile("day2_sample.txt")
                .map { it.body }
                .map { solutionPart2(it) }

            result shouldBe Some(12)
        }
    }

    context("solution") {
        should("return total points from strategy") {
            readFile("day2.txt")
                .map { it.body }
                .map { solution(it) }
                .tap {
                    println("Final solution - day 2 part 1: $it")
                }
        }

        should("return total points from strategy part2") {
            readFile("day2.txt")
                .map { it.body }
                .map { solutionPart2(it) }
                .tap {
                    println("Final solution - day 2 part 2: $it")
                }
        }
    }

})
