package mgmix.dev.line.repository.data.db

import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem

interface LineDatabase {

    suspend fun getNoteWithAttachments(): List<NoteItem>

    suspend fun addNoteWithAttachments(noteItem: NoteItem, attachments: List<AttachmentItem>)

    suspend fun deleteNote(keyId: Long)

    suspend fun removeAttachment(id: Int)

    suspend fun getNoteDetail(keyId: Long): NoteItem


}