package mgmix.dev.line.repository.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attachment")
data class AttachmentEntity(
    @PrimaryKey(autoGenerate = true)
    val attachId: Int,
    val path: String?,
    val attachNoteId: Int

)