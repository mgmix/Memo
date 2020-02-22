package mgmix.dev.line.repository.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mgmix.dev.line.repository.data.db.entity.NoteEntity

@Dao
interface LineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)
//    suspend fun addNote(note: NoteEntity, attachment: AttachmentEntity? = null)

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

    @Query("SELECT * FROM note WHERE keyId = :keyId")
    suspend fun getNoteDetail(keyId: Long): NoteEntity




}