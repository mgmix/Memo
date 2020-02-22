package mgmix.dev.line.repository.data.db.entity

import androidx.room.*

@Entity(tableName = "attachment",
    indices = [Index("attachNoteId")],
    foreignKeys = [ForeignKey(entity = NoteEntity::class,
        parentColumns = arrayOf("keyId"),
        childColumns = arrayOf("attachNoteId")
    )]
)
data class AttachmentEntity(
    @PrimaryKey(autoGenerate = true)
    val attachId: Int,
    val path: String?,
    val attachNoteId: Long
)