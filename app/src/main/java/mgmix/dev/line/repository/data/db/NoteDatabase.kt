package mgmix.dev.line.repository.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mgmix.dev.line.repository.data.db.dao.LineDao
import mgmix.dev.line.repository.data.db.entity.AttachmentEntity
import mgmix.dev.line.repository.data.db.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
        AttachmentEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class NoteDatabase: RoomDatabase() {

    abstract fun homeDao(): LineDao
}