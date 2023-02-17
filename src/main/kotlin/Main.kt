fun main() {
    val archiveId = ""
    val archiveList: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
    println("Вас приветствует приложение Заметки!")
    val archive = ArchiveMenu(archiveId, archiveList)
    Navigation().show(archive, archiveId, archiveList)

}