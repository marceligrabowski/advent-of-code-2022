package day6

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample1") {
        should("after which character marker appears") {
            val result = readFile("day6_sample1.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some(7)

        }

        should("after which character message appears") {
            val result = readFile("day6_sample1.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some(19)

        }
    }

    context("sample2") {
        should("after which character marker appears") {
            val result = readFile("day6_sample2.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some(5)

        }

        should("after which character message appears") {
            val result = readFile("day6_sample2.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some(23)

        }
    }

    context("sample3") {
        should("after which character marker appears") {
            val result = readFile("day6_sample3.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some(6)

        }

        should("after which character message appears") {
            val result = readFile("day6_sample3.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some(23)
        }
    }

    context("sample4") {
        should("after which character marker appears") {
            val result = readFile("day6_sample4.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some(10)

        }

        should("after which character message appears") {
            val result = readFile("day6_sample4.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some(29)

        }
    }

    context("sample5") {
        should("after which character marker appears") {
            val result = readFile("day6_sample5.txt")
                .map { it.body }
                .map { step1(it) }

            result shouldBe Some(11)
        }

        should("after which character message appears") {
            val result = readFile("day6_sample5.txt")
                .map { it.body }
                .map { step2(it) }

            result shouldBe Some(26)
        }
    }
    
    
    context("solution") {
        should("after which character marker appears") {
            readFile("day6.txt")
                .map { it.body }
                .map { step1(it) }
                .tap { println("Final solution - day 6 part 1: $it") }
        }

        should("after which character message appears") {
            readFile("day6.txt")
                .map { it.body }
                .map { step2(it) }
                .tap { println("Final solution - day 6 part 2: $it") }
        }
    }
})