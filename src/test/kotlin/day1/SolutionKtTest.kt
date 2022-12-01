package day1

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return total Calories carried by elv who cares them the most") {
            val result = readFile("day1_sample.txt")
                .map { it.body }
                .map { solution(it, 1) }

            result shouldBe Some(24000)
        }

        should("return total Calories carried by 3 elves who cares them the most") {
            val result = readFile("day1_sample.txt")
                .map { it.body }
                .map { solution(it, 3) }

            result shouldBe Some(45000)
        }
    }

    context("solution") {
        should("return total Calories carried by elv who cares them the most") {
            readFile("day1.txt")
                .map { it.body }
                .map { solution(it, 1) }
                .tap {
                    println("Final solution - day 1 part 1: $it")
                }
        }

        should("return total Calories carried by 3 elves who cares them the most") {
            readFile("day1.txt")
                .map { it.body }
                .map { solution(it, 3) }
                .tap {
                    println("Final solution - day 1 part 2: $it")
                }
        }
    }

})
