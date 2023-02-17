class ArchiveMenu (var archiveId: String, var archiveList: MutableMap<String, MutableMap<String, String>>) : Menu() {

    companion object {
        val archiveList: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
    }

    private val noteList: MutableMap<String, String> = mutableMapOf()

    fun createArchive()  {

        println("Введите название архива")
        val archiveTitle = Navigation().checkInputString()!!
        archiveList[archiveTitle] = noteList
        println("Архив $archiveTitle создан")

    }

    fun openArchive()  {

        if (archiveList.isEmpty()) {
            println("Список архивов пуст, создайте новый архив")
        } else {
            println("Введите название архива")
            println("Список существующих архивов: ")
            archiveList.keys.forEach { key -> println(key) }
            archiveId = Navigation().checkInputString()!!
            if (archiveList.containsKey(archiveId)) {
                val note = NoteMenu(ArchiveMenu(archiveId, archiveList))
                Navigation().show(note, archiveId, archiveList)
            } else println("Такого архива нет")
        }
    }

}