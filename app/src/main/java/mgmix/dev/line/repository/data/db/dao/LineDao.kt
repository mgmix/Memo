package mgmix.dev.line.repository.data.db.dao

import androidx.room.*
import mgmix.dev.line.repository.data.db.entity.AttachmentEntity
import mgmix.dev.line.repository.data.db.entity.NoteEntity
import mgmix.dev.line.repository.data.db.entity.NoteWithAttachments

@Dao
interface LineDao {

    @Insert
    suspend fun addNote(note: NoteEntity, attachment: AttachmentEntity? = null)

//    @Update
//    suspend fun updateNote()
//
//    @Update
//    suspend fun updateAttachment()
//
//    @Delete
//    suspend fun deleteNote()
//
//    @Delete
//    suspend fun deletAttachment()

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM note")
    suspend fun getNoteWithAttachments(): List<NoteWithAttachments>


}