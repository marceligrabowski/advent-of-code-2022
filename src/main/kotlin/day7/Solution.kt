package day7

const val cdRoot = "$ cd /"
val regexCdFolder = Regex("\\\$ cd (\\w+)")
const val cdParent = "$ cd .."
const val ls = "$ ls"
val regexDir = Regex("dir (\\w+)")
val regexFile = Regex("(\\d+)\\s(.+)")

sealed interface Operation {
    object None : Operation
    object MoveToRoot : Operation
    class MoveToFolder(val name: String) : Operation
    object ReadFolderContent : Operation
    object MoveToParent : Operation

    companion object {
        fun getOperationFromCommand(command: String): Operation {
            return when {
                command == cdRoot -> MoveToRoot
                command == cdParent -> MoveToParent
                command == ls -> ReadFolderContent
                regexCdFolder.matches(command) -> {
                    val path = regexCdFolder.find(command)!!.groupValues[1]
                    MoveToFolder(path)
                }

                else -> None
            }
        }
    }
}

sealed class Entity(val name: String) {
    class File(name: String, val size: Int) : Entity(name)
    class Folder(
        name: String, private val parent: Folder?, private var _children: List<Entity> = listOf()
    ) : Entity(name) {
        fun addChild(entity: Entity) {
            _children += entity
        }

        fun isRoot(): Boolean = parent == null

        val children: List<Entity> get() = _children

        fun goToParent(): Folder = parent ?: throw Exception()

        fun goToChild(name: String): Folder =
            _children.filterIsInstance<Folder>().first { it.name == name }

        fun size(): Int = children.sumOf {
            return@sumOf when (it) {
                is Folder -> it.size()
                is File -> it.size
            }
        }
    }
}

val root = Entity.Folder("/", null)

fun parse(input: List<String>) {
    var currentOperation: Operation = Operation.None
    var currentFolder: Entity.Folder? = null
    input.forEach {
        val readOperation = Operation.getOperationFromCommand(it)
        if (readOperation == Operation.None && currentOperation == Operation.ReadFolderContent) {
            processLsOutput(currentFolder, it)
        } else {
            currentFolder = processOperation(currentFolder, readOperation)
            currentOperation = readOperation
        }
    }
}

fun part1(input: List<String>): Int {
    parse(input)
    return part1(root)
}

fun part1(folder: Entity.Folder): Int {
    if (!folder.isRoot()) {
        val result = when (val folderSize = folder.size()) {
            in 0..100000 -> folderSize
            else -> 0
        }
        return result + folder.children.filterIsInstance(Entity.Folder::class.java)
            .sumOf { part1(it) }
    }
    return folder.children
        .filterIsInstance(Entity.Folder::class.java)
        .sumOf { part1(it) }
}

fun part2(input: List<String>): Int {
    parse(input)
    val total = 70000000
    val needed = 30000000
    val free = total - root.size()
    val toFree = needed - free
    return part2(root, toFree)
        .map { it.size() }.minOf { it }
}

fun part2(folder: Entity.Folder, toFree: Int): List<Entity.Folder> {
    if (folder.size() >= toFree) {
        return listOf(folder) + folder.children.filterIsInstance(Entity.Folder::class.java)
            .flatMap { part2(it, toFree) }
    }
    return emptyList()
}


fun processOperation(currentFolder: Entity.Folder?, operation: Operation): Entity.Folder? {
    return when (operation) {
        is Operation.MoveToFolder -> currentFolder?.goToChild(operation.name)
        Operation.MoveToParent -> currentFolder?.goToParent()
        Operation.MoveToRoot -> root
        Operation.None -> currentFolder
        Operation.ReadFolderContent -> currentFolder
    }
}

fun processLsOutput(currentFolder: Entity.Folder?, input: String): Entity.Folder? {
    when {
        regexDir.matches(input) -> {
            val name = regexDir.find(input)!!.groupValues[1]
            currentFolder?.addChild(Entity.Folder(name, currentFolder))
        }

        regexFile.matches(input) -> {
            val groups = regexFile.find(input)!!.groupValues
            val size = groups[1].toInt()
            val name = groups[2]
            currentFolder?.addChild(Entity.File(name, size))
        }
    }

    return currentFolder
}