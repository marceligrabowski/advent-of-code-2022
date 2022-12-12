
package day10

import arrow.core.Some
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import utils.readFile

class SolutionKtTest : ShouldSpec({
    context("sample") {
        should("return sum of power cycles") {
            val result = readFile("day10_sample.txt")
                .map { it.body }
                .map { part1(it) }

            result shouldBe Some("13140")
        }

        should("return render stuff") {
            val result = readFile("day10_sample.txt")
                .map { it.body }
                .map { part2(it) }

            result shouldBe Some("##..##..##..##..##..##..##..##..##..##..\n" +
                    "###...###...###...###...###...###...###.\n" +
                    "####....####....####....####....####....\n" +
                    "#####.....#####.....#####.....#####.....\n" +
                    "######......######......######......####\n" +
                    "#######.......#######.......#######.....\n")
        }
    }

    context("solution") {
        should("return sum of power cycles") {
            readFile("day10.txt")
                .map { it.body }
                .map { part1(it) }
                .tap { println("Final solution - day 10 part 1: $it") }
        }

        should("return render stuff") {
            readFile("day10.txt")
                .map { it.body }
                .map { part2(it) }
                .tap { println("Final solution - day 10 part 2:\n $it") }

        }
    }
})