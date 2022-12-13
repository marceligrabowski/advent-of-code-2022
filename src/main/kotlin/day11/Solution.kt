package day11


data class Monkey(
    val items: MutableList<Long>,
    val operation: (Long) -> (Long),
    val test: (Long) -> Boolean,
    val testAction: (Boolean) -> Int,
    val dividableBy: Long,
    var inspects: Long = 0
)

data class Transfer(
    val toMonkey: Int,
    val item: Long
)

sealed interface Op {
    object Add : Op
    object Mul : Op
}

fun part1(input: List<String>): Long {
    val monkeys = parseMonkeys(input)
    repeat((0 until 20).count()) {
        (0 until monkeys.size).forEach {
            val monkey = monkeys[it]
            val transfers = mutableListOf<Transfer>()
            monkeys[it] = monkey.copy(items = monkey.items.map { item ->
                // inspects
                var currentLevel: Long = monkey.operation(item)
                // bored
                currentLevel /= 3
                // decide to which monkey
                val divisible = monkey.test(currentLevel)
                val toMonkey = monkey.testAction(divisible)
                transfers.add(Transfer(toMonkey, currentLevel))

                currentLevel
            }.toMutableList(), inspects = monkey.inspects + monkey.items.size)
            transfers.forEach { tr ->
                monkeys[it].items.remove(tr.item)
                monkeys[tr.toMonkey].items.add(tr.item)
            }
        }
    }

    return monkeys.map { it.inspects }
        .sortedDescending()
        .take(2)
        .fold(1) { acc, item -> acc * item }
}

fun part2(input: List<String>): Long {
    val monkeys = parseMonkeys(input)
    val modulo: Long = monkeys.map { it.dividableBy }.fold(1) { acc, it -> acc * it }
    repeat((0 until 10000).count()) {
        (0 until monkeys.size).forEach {
            val monkey = monkeys[it]
            val transfers = mutableListOf<Transfer>()
            monkeys[it] = monkey.copy(items = monkey.items.map { item ->
                // inspects
                var currentLevel = monkey.operation(item)
                // bored
                currentLevel %= modulo
                // decide to which monkey
                val divisible = monkey.test(currentLevel)
                val toMonkey = monkey.testAction(divisible)
                transfers.add(Transfer(toMonkey, currentLevel))

                currentLevel
            }.toMutableList(), inspects = monkey.inspects + monkey.items.size)
            transfers.forEach { tr ->
                monkeys[it].items.remove(tr.item)
                monkeys[tr.toMonkey].items.add(tr.item)
            }
        }
    }

    return monkeys.map { it.inspects }
        .sortedDescending()
        .take(2)
        .fold(1) { acc, item -> acc * item }
}

fun parseMonkeys(input: List<String>): MutableList<Monkey> {
    return input.chunked(7)
        .map {
            val items =
                it[1].trim().replace("Starting items: ", "").split(",").map { it.trim().toLong() }
            val operation = it[2].trim().replace("Operation: new = ", "")
            val op = when {
                operation.contains("*") -> Op.Mul
                else -> Op.Add
            }

            val opera: (Long) -> (Long) = { opperand1 ->
                val op2 = when (val operand2 = operation.split(" ")[2]) {
                    "old" -> opperand1
                    else -> operand2.toLong()
                }
                when (op) {
                    Op.Add -> opperand1.plus(op2)
                    Op.Mul -> opperand1.times(op2)
                }
            }

            val divisibleBy = it[3].trim().replace("Test: divisible by ", "").toLong()
            val test: (Long) -> (Boolean) = { it -> it % divisibleBy == 0L }
            val ifTrue = it[4].trim().replace("If true: throw to monkey ", "").toInt()
            val ifFalse = it[5].trim().replace("If false: throw to monkey ", "").toInt()
            val testAction: (Boolean) -> (Int) = { testResult ->
                when (testResult) {
                    true -> ifTrue
                    false -> ifFalse
                }
            }
            return@map Monkey(items.toMutableList(), opera, test, testAction, divisibleBy)
        }.toMutableList()
}