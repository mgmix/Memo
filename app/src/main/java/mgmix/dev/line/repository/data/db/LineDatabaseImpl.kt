package mgmix.dev.line.repository.data.db

import android.util.Log
import androidx.lifecycle.map
import mgmix.dev.line.repository.data.db.dao.LineDao
import mgmix.dev.line.repository.data.mapper.toNoteEntity
import mgmix.dev.line.repository.data.mapper.toNoteItem
import mgmix.dev.line.repository.data.model.NoteItem
import org.w3c.dom.Entity

// Entity <-> Model Mapping

class LineDatabaseImpl(
    private val lineDao: LineDao
): LineDatabase {

    override suspend fun getAllNotes(): List<NoteItem> {
        return lineDao.getAllNotes().map { it.toNoteItem() }
    }

    override suspend fun addNote(noteItem: NoteItem) {
        // If already exist keyId  UPDATE else ADD
        lineDao.addNote(noteItem.toNoteEntity())
    }

    override suspend fun saveNote(noteItem: NoteItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteNote(id: NoteItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getNoteDetail(keyId: Long): NoteItem {
        Log.d("LineDatabaseImpl", "keyId: $keyId ");
        val test = lineDao.getNoteDetail(keyId)
//        Log.d("LineDatabaseImpl", "NoteEntity: ${test.keyId} :: ${test.title} :: ${test.content}")
        val toTest = test.toNoteItem()
        Log.d("LineDatabaseImpl", "NoteEntity: ${toTest.keyId} :: ${toTest.title} :: ${toTest.contents}")
        return toTest
    }
}