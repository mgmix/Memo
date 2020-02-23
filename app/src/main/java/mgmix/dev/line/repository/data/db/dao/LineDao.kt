package mgmix.dev.line.repository.data.db.dao

import androidx.room.*
import mgmix.dev.line.repository.data.db.entity.AttachmentEntity
import mgmix.dev.line.repository.data.db.entity.NoteEntity
import mgmix.dev.line.repository.data.db.entity.NoteWithAttachments

@Dao
interface LineDao {

    @Transaction
    suspend fun addNoteWithAttachments(note: NoteEntity, attachments: List<AttachmentEntity>) {
        addNote(note)
        addAttachments(attachments)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttachments(attachments: List<AttachmentEntity>)


    @Query("DELETE FROM note WHERE keyId = :keyId")
    suspend fun deleteNote(keyId: Long)

    @Query("DELETE FROM attachment WHERE attachId = :id")
    suspend fun removeAttachment(id: Int)


    // Get
    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<NoteEntity>

    @Transaction
    @Query("SELECT note.keyId, note.title, note.content, attachment.path FROM note LEFT JOIN attachment ON note.keyId = attachNoteId IN (SELECT DISTINCT(note.keyId))")
    suspend fun getNoteWithThumbNail(): List<NoteWithAttachments>


    @Transaction
    @Query("SELECT note.keyId, note.title, note.content, attachment.attachId, attachment.path, attachment.`desc` FROM note LEFT JOIN attachment ON note.keyId = attachNoteId WHERE keyId = :keyId")
    suspend fun getNoteDetail(keyId: Long): NoteWithAttachments


}