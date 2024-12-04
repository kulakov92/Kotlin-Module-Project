import java.util.Scanner
import kotlin.system.exitProcess

class Archive(val nameOfArchive: String) {
    private val notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun listNotes(): List<Note> {
        return notes
    }
}

class MenuOfArchive {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun showMenuOfArchive() {
        while (true) {
            val menu = Menu(
                listOf(
                    Pair("Выход") { exitProcess(0) },
                    Pair("Создать архив") { createArchive() },
                    Pair("Список архивов") { listArchives() }
                )
            )
            menu.showMenu()
        }
    }

    private fun createArchive() {
        while (true) {
            println("Введите название архива:")
            val title = scanner.nextLine()
            if (title.isBlank()) {
                println("Введите корректное название архива - название не может пустым!")
                continue
            }
            archives.add(Archive(title))
            println("Архив '$title' создан!")
            return
        }
    }

    private fun listArchives() {
        while (true) {
            if (archives.isEmpty()) {
                println("На данный момент не создано ни одного архива!")
                return
            }

            val archiveOptions = mutableListOf<Pair<String, () -> Unit>>()
            archiveOptions.add(Pair("Назад") { showMenuOfArchive() })

            archives.forEach { archive ->
                archiveOptions.add(Pair(archive.nameOfArchive) {
                    val menuOfNote = MenuOfNote(archive) { showMenuOfArchive() }
                    menuOfNote.showMenu()
                })
            }

            val menu = Menu(archiveOptions)
            menu.showMenu()
        }
    }
}