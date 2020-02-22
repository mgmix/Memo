package mgmix.dev.line.repository.data.mapper

import mgmix.dev.line.repository.data.db.entity.AttachmentEntity
import mgmix.dev.line.repository.data.db.entity.NoteEntity
import mgmix.dev.line.repository.data.model.NoteItem

// Join 으로 대체
//internal fun NoteItem.toNoteWithAttachments(): NoteWithAttachments {
//    return NoteWithAttachments(
//        note = NoteEntity(
//            noteId = id ?: -1,
//            title = title,
//            content = contents
//        ),
//        attachments = attachments
//    )
//}

internal fun NoteItem.toNoteEntity(): NoteEntity {
    return NoteEntity(
        keyId = keyId,
        title = title,
        content = contents
    )
}

internal fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        keyId = keyId,
        title = title,
        contents = content
    )
}

//internal fun NoteItem.toAttachmentEntity(): AttachmentEntity {
//    return AttachmentEntity(
//        attachNoteId = keyId,
//        path = attachments.map { it }
//
//    )
//}




//internal fun NoteWithAttachments.toNoteItem(): NoteItem {
//    return NoteItem(
//        id = note.noteId,
//        title = note.title,
//        contents = note.content,
//        attachments = attachments
//    )
//}

