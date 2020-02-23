package mgmix.dev.line.repository

import android.util.Log
import mgmix.dev.line.repository.data.db.LineDatabase
import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem

internal class LineRepositoryImpl(
    private val local: LineDatabase
) : LineRepository {

    override suspend fun addNoteWithAttachments(
        noteItem: NoteItem,
        attachments: List<AttachmentItem>
    ) {
        local.addNoteWithAttachments(noteItem, attachments)
    }

    override suspend fun getAllNotes() = local.getNoteWithAttachments()

    override suspend fun deleteNote(keyId: Long) {
        local.deleteNote(keyId)
    }

    override suspend fun removeAttachment(id: Int) {
        local.removeAttachment(id)
    }

    override suspend fun getNoteDetail(keyId: Long): NoteItem {
        Log.e("LineRepository", "keyId: $keyId")
        return local.getNoteDetail(keyId)
    }
}