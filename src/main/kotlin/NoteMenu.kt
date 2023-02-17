class NoteMenu(val source: ArchiveMenu) : Menu() {

    fun createNote(archiveId: String, notes: MutableMap<String, String>) {

        println("Введите название заметки")
        val noteTitle = Navigation().checkInputString()!!
        println("Введите текст заметки")
        val note = Navigation().checkInputString()!!
        notes[noteTitle] = note
        ArchiveMenu.archiveList[archiveId] = notes
        println("Заметка $noteTitle создана")

    }

    fun openNote(archiveId: String) {

        val noteLis: MutableMap<String, String>? = ArchiveMenu.archiveList[archiveId]
        println("Список существующих заметок: ")
        if (noteLis != null) {
            noteLis.keys.forEach { key -> println(key) }

        println("Введите название заметки для просмотра:")
        val number = Navigation().checkInputString()!!
            println(noteLis.getOrDefault(number, "Такой заметки нет"))
        } else println("Список заметок пуст")
    }

}