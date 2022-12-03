package day3

val priority = ('a'..'z') + ('A'..'Z')

fun solution(input: List<String>) =
    input.map {
        val chunked = it.chunked(it.length / 2)
        chunked[0].toSet().intersect(chunked[1].toSet()).first()
    }.sumOf {
        priority.indexOf(it) + 1
    }

fun solutionPart2(input: List<String>) =
    input.chunked(3)
        .map { it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first() }
        .sumOf { priority.indexOf(it) + 1 }


