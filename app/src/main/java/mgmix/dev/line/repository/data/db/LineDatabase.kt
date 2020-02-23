package mgmix.dev.line.repository.data.db

import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem

interface LineDatabase {

    // Database Event 정의
    suspend fun getNoteWithAttachments(): List<NoteItem>

    suspend fun addNoteWithAttachments(noteItem: NoteItem, attachments: List<AttachmentItem>)


    // delete - Delete
    suspend fun deleteNote(keyId: Long)

    suspend fun removeAttachment(id: Int)

    // get - get
    suspend fun getNoteDetail(keyId: Long): NoteItem


}