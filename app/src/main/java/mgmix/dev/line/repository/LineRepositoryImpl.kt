package mgmix.dev.line.repository

import mgmix.dev.line.repository.data.db.LineDatabase
import mgmix.dev.line.repository.data.model.NoteItem

internal class LineRepositoryImpl(
    private val local: LineDatabase
): LineRepository {

    override suspend fun getAllNotes() = local.getAllNotes()


    override suspend fun addNote(noteItem: NoteItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateNote(noteItem: NoteItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeNote(id: Int) {
        local.deleteNote()
    }

    override suspend fun getNoteDetail(id: Int) {
        local.getNoteDetail()
    }
}