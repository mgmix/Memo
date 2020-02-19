package mgmix.dev.line.repository.data.db.entity

import androidx.room.*

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int,
    val title: String?,
    val content: String?

)

data class NoteWithAttachments(
    @Embedded val note: NoteEntity,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "attachNoteId"
    )
    val attachments: List<AttachmentEntity>
)