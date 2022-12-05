package day4

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return pairs with ranges fully overlapped") {
            val result = readFile("day4_sample.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some(2L)

        }

        should("return pairs with ranges overlapped") {
            val result = readFile("day4_sample.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some(4L)

        }
    }
    context("solution") {
        should("return pairs with ranges fully overlapped") {
            readFile("day4.txt")
                .map { it.body }
                .map { step1(it) }
                .tap { println("Final solution - day 4 part 1: $it") }
        }

        should("return pairs with ranges overlapped") {
            readFile("day4.txt")
                .map { it.body }
                .map { step2(it) }
                .tap { println("Final solution - day 4 part 2: $it") }
        }
    }
})