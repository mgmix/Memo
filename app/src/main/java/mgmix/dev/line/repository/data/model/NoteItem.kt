package mgmix.dev.line.repository.data.model

data class NoteItem(
    val keyId: Long,
    val title: String?,
    val contents: String?,
    val attachments:  List<AttachmentItem>?
)