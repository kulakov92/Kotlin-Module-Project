import java.util.Scanner

class Note(
    val nameOfNote: String,
    val contentOfNote: String
)

class MenuOfNote(
    private val archive: Archive,
    private val onReturn: () -> Unit
) {
    private val scanner = Scanner(System.`in`)

    fun showMenu() {
        while (true) {
            val menu = Menu(
                listOf(
                    Pair("Назад") { returnToArchiveMenu() },
                    Pair("Создать заметку") { createNote() },
                    Pair("Список заметок") { listNotes() }
                )
            )
            menu.showMenu()
        }
    }

    private fun returnToArchiveMenu() {
        onReturn.invoke()
    }

    private fun createNote() {
        var title: String
        while (true) {
            println("Введите название заметки:")
            title = scanner.nextLine()
            if (title.isBlank()) {
                println("Введите корректное название заметки - название не может пустым!")
            } else {
                break
            }
        }

        var content: String
        while (true) {
            println("Введите содержание заметки:")
            content = scanner.nextLine()
            if (content.isBlank()) {
                println("Заметка не может быть пустой - введите содержание заметки!")
            } else {
                break
            }
        }

        archive.addNote(Note(title, content))
        println("Заметка '$title' создана!")
    }

    private fun listNotes() {
        while (true) {
            val notes = archive.listNotes()
            if (notes.isEmpty()) {
                println("В данном архиве еще нет ни одной заметки!")
                return
            }

            println("Список заметок:" + "\n")
            val noteOptions = mutableListOf<Pair<String, () -> Unit>>()

            noteOptions.add(Pair("Назад") { showMenu() })

            notes.forEachIndexed { index, note ->
                noteOptions.add(Pair(note.nameOfNote) { viewNoteContent(note) })
            }

            val menu = Menu(noteOptions)
            menu.showMenu()
        }
    }

    private fun viewNoteContent(note: Note) {
        println("\"${note.nameOfNote}\"")
        println()
        println(note.contentOfNote)
    }
}