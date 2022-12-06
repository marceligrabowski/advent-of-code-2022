package day5

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return top crates") {
            val result = readFile("day5_sample.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some("CMZ")

        }

        should("return top crates - CrateMover 9001") {
            val result = readFile("day5_sample.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some("MCD")

        }

    }
    context("solution") {
        should("return top crates") {
            readFile("day5.txt")
                .map { it.body }
                .map { step1(it) }
                .tap { println("Final solution - day 5 part 1: $it") }
        }

        should("return top crates - CrateMover 9001") {
            readFile("day5.txt")
                .map { it.body }
                .map { step2(it) }
                .tap { println("Final solution - day 5 part 2: $it") }
        }
    }
})