package day2

import day2.Move.Rock.getMoveToResult
import day2.Move.Rock.versus

sealed class Move(val points: Int) {
    object Rock : Move(1)
    object Paper : Move(2)
    object Scissors : Move(3)

    companion object {
        fun getMoveByOpponentCode(code: String): Move = when (code) {
            "A" -> Rock
            "B" -> Paper
            "C" -> Scissors
            else -> {
                throw Exception()
            }
        }

        fun getMoveByMineCode(code: String): Move = when (code) {
            "X" -> Rock
            "Y" -> Paper
            "Z" -> Scissors
            else -> {
                throw Exception()
            }
        }
    }

    infix fun Move.versus(move: Move): Result {
        return when {
            this is Rock && move is Scissors -> Result.Win
            this is Rock && move is Paper -> Result.Lost
            this is Paper && move is Rock -> Result.Win
            this is Paper && move is Scissors -> Result.Lost
            this is Scissors && move is Paper -> Result.Win
            this is Scissors && move is Rock -> Result.Lost
            else -> Result.Draw
        }
    }

    infix fun Move.getMoveToResult(result: Result): Move {
        return when {
            this is Rock && result is Result.Lost -> Scissors
            this is Rock && result is Result.Win -> Paper
            this is Paper && result is Result.Lost -> Rock
            this is Paper && result is Result.Win -> Scissors
            this is Scissors && result is Result.Lost -> Paper
            this is Scissors && result is Result.Win -> Rock
            else -> this
        }
    }
}

sealed class Result(val points: Int) {
    object Lost : Result(0)
    object Draw : Result(3)
    object Win : Result(6)

    companion object {
        fun getResultByCode(code: String): Result = when (code) {
            "X" -> Lost
            "Y" -> Draw
            "Z" -> Win
            else -> {
                throw Exception()
            }
        }
    }
}

fun solution(strategy: List<String>): Int {
    return strategy.map {
        it.split(" ")
    }.map {
        Pair(Move.getMoveByOpponentCode(it[0]), Move.getMoveByMineCode(it[1]))
    }.sumOf {
        val resultPoints = (it.second versus it.first).points
        val movePoints = it.second.points
        resultPoints + movePoints
    }
}

fun solutionPart2(strategy: List<String>): Int {
    return strategy.map {
        it.split(" ")
    }.map {
        Pair(Move.getMoveByOpponentCode(it[0]), Result.getResultByCode(it[1]))
    }.sumOf {
        val resultPoints = it.second.points
        val movePoints = (it.first getMoveToResult it.second).points
        resultPoints + movePoints
    }
}