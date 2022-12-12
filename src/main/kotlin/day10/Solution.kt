package day10

import day10.Operation.Addx
import day10.Operation.Noop

val addXRegex = Regex("addx (-?\\d+)")

data class RegisterX(val current: Int)
sealed class Operation(val cycles: Int) {
    private var remainingCycles = cycles

    fun cycle(): Int {
        remainingCycles -= 1
        return remainingCycles
    }

    abstract fun done(x: RegisterX): RegisterX

    class Noop : Operation(1) {
        override fun done(x: RegisterX): RegisterX {
            return x
        }
    }

    class Addx(val input: Int) : Operation(2) {
        override fun done(x: RegisterX): RegisterX {
            return RegisterX(input + x.current)
        }
    }
}

fun part1(input: List<String>): Int {
    val operations = input.filter { it.isNotBlank() }.map {
        if (it == "noop") {
            return@map Noop()
        } else {
            return@map Addx(addXRegex.find(it)?.groupValues!![1].toInt())
        }
    }
    val requiredCycleNumber = operations.sumOf { it.cycles }
    var registerX = RegisterX(1)
    var currentOperationIterator = operations.iterator()
    var currentOperation = currentOperationIterator.next()
    val powerValue = mutableListOf<Int>()
    val powerCycles = intArrayOf(20, 60, 100, 140, 180, 220)
    (1..requiredCycleNumber).forEach {
        if (it in powerCycles) {
            powerValue.add(it * registerX.current)
        }
        val remainingCycles = currentOperation.cycle()
        if (remainingCycles == 0) {
            registerX = currentOperation.done(registerX)
            if (currentOperationIterator.hasNext()) {
                currentOperation = currentOperationIterator.next()
            }
        }
    }
    return powerValue.sum()
}

fun part2(input: List<String>): String {
    val operations = input.filter { it.isNotBlank() }.map {
        if (it == "noop") {
            return@map Noop()
        } else {
            return@map Addx(addXRegex.find(it)?.groupValues!![1].toInt())
        }
    }
    val requiredCycleNumber = operations.sumOf { it.cycles }
    var registerX = RegisterX(1)
    var currentSprite = getSpriteIndexes(registerX)
    var currentOperationIterator = operations.iterator()
    var currentOperation = currentOperationIterator.next()
    var result = ""
    (1..requiredCycleNumber).forEach {
        result += if (currentSprite.contains((it - 1) % 40)) {
            "#"
        } else "."
        if (it % 40 == 0) result += "\n"
        val remainingCycles = currentOperation.cycle()
        if (remainingCycles == 0) {
            registerX = currentOperation.done(registerX)
            currentSprite = getSpriteIndexes(registerX)
            if (currentOperationIterator.hasNext()) {
                currentOperation = currentOperationIterator.next()
            }
        }
    }
    return result
}

private fun getSpriteIndexes(x: RegisterX) = x.current - 1..x.current + 1