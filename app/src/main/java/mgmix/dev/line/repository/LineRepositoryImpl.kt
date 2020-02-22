package mgmix.dev.line.repository

import android.util.Log
import mgmix.dev.line.repository.data.db.LineDatabase
import mgmix.dev.line.repository.data.model.NoteItem

internal class LineRepositoryImpl(
    private val local: LineDatabase
): LineRepository {

    override suspend fun getAllNotes() = local.getAllNotes()


    override suspend fun addNote(noteItem: NoteItem) {
        local.addNote(noteItem)
    }

    override suspend fun updateNote(noteItem: NoteItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeNote(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getNoteDetail(keyId: Long): NoteItem {
        Log.d("LineRepository", "keyId: $keyId")
        return local.getNoteDetail(keyId)
    }
}