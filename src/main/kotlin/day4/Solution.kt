package day4

val regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

fun step1(input: List<String>): Long = input.sumOf {
    val result = regex.find(it)!!.groupValues
    val e1start = result[1].toInt()
    val e1end = result[2].toInt()
    val e2start = result[3].toInt()
    val e2end = result[4].toInt()
    if ((e1start <= e2start && e1end >= e2end) || (e2start <= e1start && e2end >= e1end)) return@sumOf 1L
    0L
}

fun step2(input: List<String>): Long = input.sumOf {
    val result = regex.find(it)!!.groupValues
    val e1start = result[1].toInt()
    val e1end = result[2].toInt()
    val e2start = result[3].toInt()
    val e2end = result[4].toInt()
    if(IntRange(e1start, e1end).intersect(IntRange(e2start, e2end)).any()) return@sumOf 1L
    0L
}