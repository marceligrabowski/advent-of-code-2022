package day1

fun solution(elvesSnacksCalories: List<String>, take: Int = 1): Int {
    val elvesByCarriedCalories = elvesSnacksCalories.fold(mutableListOf(0)) { acc, s ->
        if (s.isEmpty()) {
            acc.add(0)
            acc
        } else {
            val result = acc.last() + s.toInt()
            acc[acc.size - 1] = result
            acc
        }
    }

    return elvesByCarriedCalories
        .sortedDescending()
        .take(take)
        .sum()
}