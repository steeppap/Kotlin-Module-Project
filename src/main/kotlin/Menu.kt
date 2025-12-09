data class ArchiveMenu(val archiveName: String, val notesList: MutableList<NoteMenu> = mutableListOf())

data class MainMenu(val archiveList: MutableList<ArchiveMenu> = mutableListOf())

data class NoteMenu(val noteName: String, val noteText: String)

