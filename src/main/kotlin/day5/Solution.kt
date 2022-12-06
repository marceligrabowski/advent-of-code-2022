package day5

data class Move(val quantity: Int, val from: Int, val to: Int)

val stackRegex = Regex("(\\s{3})\\s?|\\[(\\w)]\\s?")
val numbersRegex = Regex("\\d")
val movesRegex = Regex("move (\\d+) from (\\d+) to (\\d+)")

fun moveStacks(input: List<String>, multipleAtOnce: Boolean): String {
    // -- stacks --
    val lastStacks = input.indexOfFirst { numbersRegex.containsMatchIn(it) }
    val stacks = input.subList(0, lastStacks).map { string ->
        stackRegex.findAll(string).map { it.groupValues.last() }.toList()
    }.transpose().map { it.filter { s -> s.isNotBlank() } }.toMutableList()
    // -- moves --
    val moves = input.mapNotNull {
        movesRegex.find(it)
    }.map { it.groupValues }.map { Move(it[1].toInt(), it[2].toInt(), it[3].toInt()) }
    moves.forEach { move ->
        val crates = when (multipleAtOnce) {
            true -> stacks[move.from - 1].take(move.quantity)
            false -> stacks[move.from - 1].take(move.quantity).reversed()
        }
        stacks[move.from - 1] = stacks[move.from - 1].drop(move.quantity)
        stacks[move.to - 1] = crates + stacks[move.to - 1]
    }

    return stacks.joinToString("") { it.first() }
}

fun step1(input: List<String>): String = moveStacks(input, false)


fun step2(input: List<String>): String = moveStacks(input, true)


fun List<List<String>>.transpose(): List<List<String>> {
    val result: MutableList<MutableList<String>> = MutableList(this.first().size) {
        MutableList(size = this.size, init = { "" })
    }
    this.forEachIndexed { i, list ->
        list.forEachIndexed { j, s ->
            result[j][i] = s
        }
    }
    return result
}