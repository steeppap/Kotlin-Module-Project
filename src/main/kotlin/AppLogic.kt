import java.util.Scanner

class AppLogic() {

    var archiveList: MutableList<ArchiveMenu> = mutableListOf()
    var notesList: MutableList<NoteMenu> = mutableListOf()

    fun start() {
        showMenu(MainMenu())
    }

    fun <T> inputOfCommand(quantityCommand: Int, item: T): Int {
        val scanner = Scanner(System.`in`)
        while (true) {
            print("Введите номер команды: ")
            val input = scanner.nextLine().trim()

            if (input.isEmpty()) {
                println("Пустой ввод: введите номер команды")
                return -1
            }

            val command = input.toIntOrNull()
            if (command == null) {
                println("Неверный ввод: введите число")
                return -1
            } else if (command !in 0..quantityCommand) {
                println("Такой команды нет: введите корректный номер")
                return -1
            } else {
                return command
            }
        }
    }

    fun <T> createItem(item: T) {

        if (item is MainMenu) {
            while (true) {
                println("Введите название архива")
                val name: String = Scanner(System.`in`).nextLine()
                if (name.isEmpty()) {
                    println("Имя архива не может быть пустым")
                } else {
                    archiveList.add(ArchiveMenu(name))
                    println("Архив $name создан")
                    break
                }
            }
        } else {
            while (true) {
                println("Введите имя заметки")
                val noteName = Scanner(System.`in`).nextLine().trim()
                if (noteName.isEmpty()) {
                    println("Имя заметки не может быть пустым")
                    continue
                }
                while (true) {
                    println("Введите содержимое заметки")
                    val noteText = Scanner(System.`in`).nextLine()
                    if (noteText.isEmpty()) {
                        println("Заметка не может быть пустой")
                        continue
                    } else {
                        val note: NoteMenu = NoteMenu(noteName, noteText)
                        notesList.add(note)
                        println("Заметка ${noteName} создана")
                        break
                    }
                }
                break
            }
        }
    }

    fun <T> showMenu(item: T) {
        if (item is MainMenu) {
            while (true) {
                println("Список архивов:")
                println("0. Создать архив")
                archiveList.forEachIndexed { i, archive -> println("${i + 1}. ${archive.archiveName}") }
                println("${archiveList.size + 1}. Выход")
                val quantityCommand = archiveList.size + 1
                when (val i = inputOfCommand(quantityCommand, item)) {
                    0 -> {createItem(MainMenu())}
                    in 1..archiveList.size -> {showMenu(archiveList[i - 1])}
                    archiveList.size + 1 -> {
                        println("Завершение программы")
                        break
                    }
                    -1 -> continue
                }
            }
        } else if (item is ArchiveMenu) {
            while (true) {
                println("Список заметок архива ${item.archiveName}:")
                println("0. Создать заметку")
                notesList.forEachIndexed { i, note -> println("${i + 1}. ${note.noteName}") }
                println("${notesList.size + 1}. Вернуться назад")
                val quantityCommand = notesList.size + 1
                when (val i = inputOfCommand(quantityCommand, item)) {
                    0 -> {createItem(ArchiveMenu(item.archiveName))}
                    in 1..notesList.size -> {showMenu(notesList[i - 1])}
                    notesList.size + 1 -> break
                    -1 -> continue
                }
            }
        } else if(item is NoteMenu){
            while (true) {
                println("Содержимое заметки ${item.noteName}:")
                println(item.noteText)
                println("0. Вернуться назад")
                when (val i = inputOfCommand(0, item)) {
                    0 -> break
                    -1 -> continue
                }
            }
        }
    }
}
