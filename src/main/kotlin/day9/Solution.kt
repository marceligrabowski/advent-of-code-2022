package day9

import kotlin.math.abs

sealed interface Direction {
    object Left : Direction
    object Up : Direction
    object Down : Direction
    object Right : Direction

    companion object {
        fun fromString(dir: String): Direction = when (dir) {
            "U" -> Up
            "D" -> Down
            "L" -> Left
            "R" -> Right
            else -> throw Exception()
        }
    }
}

data class Move(val direction: Direction, val moveTime: Int)
data class Position(val x: Int, val y: Int)

fun part1(input: List<String>): Int {
    var headCurrPosition = Position(0, 0)
    var tailCurrPosition = Position(0, 0)
    val tailVisitedPositions = mutableListOf<Position>()
    input.map {
        val splittedString = it.split(" ")
        Move(Direction.fromString(splittedString[0]), splittedString[1].toInt())
    }.forEach {
        (0 until it.moveTime).forEach { moveIndex ->
            headCurrPosition = moveHead(headCurrPosition, it.direction)
            tailCurrPosition = moveTail(tailCurrPosition, headCurrPosition)
            tailVisitedPositions.add(tailCurrPosition)
        }
    }
    return tailVisitedPositions.distinct().size
}

fun part2(input: List<String>): Int {
    val positions = (0..9).map {
        Position(0, 0)
    }.toMutableList()
    val tailVisitedPositions = mutableListOf<Position>()
    input.map {
        val splittedString = it.split(" ")
        Move(Direction.fromString(splittedString[0]), splittedString[1].toInt())
    }.forEach {
        (0 until it.moveTime).forEach { moveIndex ->
            positions[0] = moveHead(positions[0], it.direction)
            (1..9).forEach { index ->
                positions[index] = moveTail(positions[index], positions[index - 1])
                if (index == 9) {
                    tailVisitedPositions.add(positions[index])
                }
            }
        }
    }
    return tailVisitedPositions.distinct().size
}

fun moveHead(currPosition: Position, direction: Direction) =
    when (direction) {
        Direction.Down -> currPosition.copy(y = currPosition.y - 1)
        Direction.Left -> currPosition.copy(x = currPosition.x - 1)
        Direction.Right -> currPosition.copy(x = currPosition.x + 1)
        Direction.Up -> currPosition.copy(y = currPosition.y + 1)
    }

fun moveTail(currPosition: Position, headPosition: Position): Position {
    if (abs(currPosition.x - headPosition.x) <= 1 && abs(currPosition.y - headPosition.y) <= 1) {
        return currPosition
    }
    if (currPosition.x == headPosition.x) {
        return when {
            currPosition.y > headPosition.y -> currPosition.copy(y = currPosition.y - 1)
            currPosition.y < headPosition.y -> currPosition.copy(y = currPosition.y + 1)
            else -> {
                currPosition
            }
        }
    }

    if (currPosition.y == headPosition.y) {
        return when {
            currPosition.x > headPosition.x -> currPosition.copy(x = currPosition.x - 1)
            currPosition.x < headPosition.x -> currPosition.copy(x = currPosition.x + 1)
            else -> {
                currPosition
            }
        }
    }

    return when {
        currPosition.x > headPosition.x && currPosition.y > headPosition.y -> currPosition.copy(
            x = currPosition.x - 1,
            y = currPosition.y - 1
        )

        currPosition.x > headPosition.x && currPosition.y < headPosition.y -> currPosition.copy(
            x = currPosition.x - 1,
            y = currPosition.y + 1
        )

        currPosition.x < headPosition.x && currPosition.y > headPosition.y -> currPosition.copy(
            x = currPosition.x + 1,
            y = currPosition.y - 1
        )

        currPosition.x < headPosition.x && currPosition.y < headPosition.y -> currPosition.copy(
            x = currPosition.x + 1,
            y = currPosition.y + 1
        )

        else -> {
            currPosition
        }
    }
}
