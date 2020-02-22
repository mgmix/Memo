package mgmix.dev.line.repository.data.db.entity

import androidx.room.*

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "keyId")
    val keyId: Long,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "content")
    val content: String?
)

