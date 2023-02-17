import java.util.*
import kotlin.system.exitProcess

class Navigation {

    companion object {
        val sc = Scanner(System.`in`)
    }

    fun <T: Menu> show(item: T, archiveId: String, archiveList: MutableMap<String, MutableMap<String, String>>) {

        while (true) {

            println("Выберите пункт меню:")

            when (item) {
                is ArchiveMenu -> {
                    item.menu["Создать архив"] = { item.createArchive() }
                    item.menu["Открыть архив"] = { item.openArchive() }
                    item.menu["Выход из программы"] = { println("Работа с приложением завершена"); exitProcess(0) }
                }
                is NoteMenu -> {

                    item.menu["Создать заметку"] = {
                        val notes: MutableMap<String, String> = archiveList[archiveId]!!
                        item.createNote(archiveId, notes)
                    }
                    item.menu["Открыть заметку"] = { item.openNote(archiveId) }
                    item.menu["Вернуться к выбору архива"] = { Navigation().show(item.source, archiveId ,archiveList) }
                }
            }
            createMenu(item)
        }

    }

    private fun <T: Menu> createMenu(items: T){
        for (i in items.menu.keys.toList()) {
            println("${items.menu.keys.toList().indexOf(i)} - $i")
        }
        val input = checkInput()
        try {
            items.menu.values.toList()[input].invoke()
        } catch (e: java.lang.IndexOutOfBoundsException) {
            println("Неверная команда: такой цифры нет. Введите цифру из пунктов меню:")
        }
    }

    private fun checkInput(): Int {
        var output: Int? = null
        while (output == null) {
            val input = sc.nextLine().trim()
            output = try {
                input.toInt()
            } catch (e: java.lang.NumberFormatException) {
                println("Неверная команда: Вы ввели не цифру. Введите цифру из пунктов меню:")
                null
            }
        }
        return output
    }

    fun checkInputString(): String? {
        var output: String? = null
        while (output.equals(null)) {
            val input = sc.nextLine().trim()
            if (input.isEmpty()) {
                println("Ввод не может быть пустым, повторите ввод")
            } else output = input
        }
        return output
    }
}