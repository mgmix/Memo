package mgmix.dev.line.repository.data.mapper

import mgmix.dev.line.repository.data.db.entity.AttachmentEntity
import mgmix.dev.line.repository.data.db.entity.NoteEntity
import mgmix.dev.line.repository.data.db.entity.NoteWithAttachments
import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem

internal fun NoteEntity.toModel(): NoteItem {
    return NoteItem(
        keyId = keyId,
        title = title,
        contents = content,
        attachments = emptyList()
    )
}

fun NoteWithAttachments.toModel(): NoteItem {
    return NoteItem(
        keyId = note.keyId,
        title = note.title,
        contents = note.content,
        attachments = attachments.map { it.toModel() }
    )
}

fun AttachmentEntity.toModel(): AttachmentItem {
    return AttachmentItem(
        id = attachId,
        keyId = attachNoteId,
        path = path,
        desc = desc
    )
}

internal fun NoteItem.toEntity(): NoteEntity {
    return NoteEntity(
        keyId = keyId,
        title = title,
        content = contents
    )
}

fun AttachmentItem.toEntity(): AttachmentEntity {
    return AttachmentEntity(
        attachId = 0,
        attachNoteId = keyId,
        path = path,
        desc = desc
    )
}