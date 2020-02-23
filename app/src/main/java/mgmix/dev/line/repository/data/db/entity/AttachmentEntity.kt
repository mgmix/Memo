package mgmix.dev.line.repository.data.db.entity

import androidx.room.*

@Entity(tableName = "attachment",
    indices = [Index("attachNoteId")],
    foreignKeys = [ForeignKey(entity = NoteEntity::class,
        parentColumns = arrayOf("keyId"),
        childColumns = arrayOf("attachNoteId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class AttachmentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "attachId")
    val attachId: Int,

    @ColumnInfo(name = "attachNoteId")
    val attachNoteId: Long,

    @ColumnInfo(name = "path")
    val path: String?,

    @ColumnInfo(name = "desc")
    val desc: String?
)