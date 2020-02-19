package mgmix.dev.line.ext

import mgmix.dev.line.repository.data.db.entity.NoteEntity
import mgmix.dev.line.repository.data.model.NoteItem

internal fun NoteItem.toNoteEntitiy(): NoteEntity {
    return NoteEntity(
        noteId = id ?: -1,
        title = title,
        content = contents
    )
}

