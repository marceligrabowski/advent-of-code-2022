package utils

import arrow.core.None
import arrow.core.Option
import arrow.core.continuations.effect
import arrow.core.continuations.ensureNotNull

@JvmInline
value class Content(val body: List<String>)
sealed interface FileError


@JvmInline
value class FileNotFound(val path: String) : FileError
object EmptyPath : FileError {
    override fun toString() = "EmptyPath"
}


suspend fun readFile(path: String?): Option<Content> = effect {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isNotEmpty()) { EmptyPath }
    val resource = this::class.java.classLoader.getResource(path)
    ensureNotNull(resource) { FileNotFound(path) }

    Content(resource.readText().split('\n'))
}.toOption { None }