package mgmix.dev.line.repository.data.model

data class NoteItem(
    val id: Int? = null,
    val title: String?,
    val contents: String?,
    val attachments: List<String>?
)