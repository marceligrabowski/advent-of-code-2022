package day8

data class Tree(val height: Int, val x: Int, val y: Int)

data class Neighibours(
    val left: List<Tree>,
    val right: List<Tree>,
    val top: List<Tree>,
    val bottom: List<Tree>
)

fun part1(input: List<String>): Int {
    val treesInt = input.map { it.toList().map { it.toString().toInt() } }
    val trees = treesInt.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, treeHeight ->
            Tree(treeHeight, colIndex, rowIndex)
        }
    }
    return trees
        .flatten()
        .map { it.isVisible(trees) }
        .groupBy { it }
        .mapValues { it.value.size }[true]!!
}

fun part2(input: List<String>): Int? {
    val treesInt = input.map { it.toList().map { it.toString().toInt() } }
    val trees = treesInt.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, treeHeight ->
            Tree(treeHeight, colIndex, rowIndex)
        }
    }
    return trees
        .flatten().maxOfOrNull { it.scenicScore(trees) }
}

fun Tree.scenicScore(list: List<List<Tree>>): Int {
    val neighbours = listOf(
        getLeft(list).reversed(),
        getRight(list),
        getTop(list).reversed(),
        getBottom(list)
    )

    return neighbours.map { it.scenicScore(this.height) }.reduce { acc, it -> acc * it }
}

fun List<Tree>.scenicScore(height: Int): Int {
    var shouldContinue = true
    return this.takeWhile {
        val result = shouldContinue
        shouldContinue = height > it.height
        result
    }.size
}

fun Tree.isVisible(list: List<List<Tree>>): Boolean {
    val neighbours = Neighibours(getLeft(list), getRight(list), getTop(list), getBottom(list))
    val (left, right, top, bottom) = neighbours
    return left.isVisible(height)
            || bottom.isVisible(height)
            || right.isVisible(height)
            || top.isVisible(height)
}

fun List<Tree>.isVisible(height: Int): Boolean {
    return this.all { it.height < height }
}

private fun Tree.getBottom(
    list: List<List<Tree>>
) = when (this.y + 1) {
    list.size -> emptyList()
    else -> list.subList(y + 1, list.size).map { it[this.x] }
}

private fun Tree.getTop(list: List<List<Tree>>) =
    when (this.y) {
        0 -> emptyList()
        else -> list.subList(0, this.y).map { it[this.x] }
    }

private fun Tree.getRight(
    list: List<List<Tree>>
) = when (this.x + 1) {
    list.first().size -> emptyList()
    else -> list[y].subList(this.x + 1, list.first().size)
}

private fun Tree.getLeft(list: List<List<Tree>>) =
    when (this.x) {
        0 -> emptyList()
        else -> list[y].subList(0, this.x)
    }