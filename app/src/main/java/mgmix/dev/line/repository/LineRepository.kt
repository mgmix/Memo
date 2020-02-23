package mgmix.dev.line.repository

import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem

interface LineRepository {

    // Repository 메소드 정의

    // add
    suspend fun addNoteWithAttachments(noteItem: NoteItem, attachments: List<AttachmentItem>)
//    suspend fun addAttachments(attachments: List<AttachmentItem>)

    // remove
    suspend fun deleteNote(keyId: Long)

    suspend fun removeAttachment(id: Int)

    // fetch
    suspend fun getAllNotes(): List<NoteItem> // Home Fragment

    suspend fun getNoteDetail(keyId: Long): NoteItem // Detail Fragment


}