package mgmix.dev.line.repository.data.db

import mgmix.dev.line.repository.data.db.dao.LineDao
import mgmix.dev.line.repository.data.mapper.toEntity
import mgmix.dev.line.repository.data.mapper.toModel
import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem

// Entity <-> Model Mapping

class LineDatabaseImpl(
    private val lineDao: LineDao
) : LineDatabase {

    // Add & Update
    override suspend fun addNoteWithAttachments(
        noteItem: NoteItem,
        attachments: List<AttachmentItem>
    ) {
        lineDao.addNote(noteItem.toEntity())
        if (attachments.isNotEmpty()) lineDao.addAttachments(attachments.map { it.toEntity() })
    }

    // Delete
    override suspend fun deleteNote(keyId: Long) {
        lineDao.deleteNote(keyId)
    }

    override suspend fun removeAttachment(id: Int) {
        lineDao.removeAttachment(id)
    }

    // Get
    override suspend fun getNoteWithAttachments(): List<NoteItem> {
        return lineDao.getNoteWithThumbNail().map {
            it.toModel()
        }
    }

    override suspend fun getNoteDetail(keyId: Long): NoteItem {
        return lineDao.getNoteDetail(keyId).toModel()
    }
}