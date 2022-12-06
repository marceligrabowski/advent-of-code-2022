package day6

fun step1(input: List<String>): Int {
    val bufferSize = 4
    return input.first()
        .windowed(bufferSize, 1)
        .indexOfFirst { it.toCharArray().distinct().size == bufferSize } + bufferSize
}

fun step2(input: List<String>): Int {
    val bufferSize = 14
    return input.first()
        .windowed(bufferSize, 1)
        .indexOfFirst { it.toCharArray().distinct().size == bufferSize } + bufferSize
}