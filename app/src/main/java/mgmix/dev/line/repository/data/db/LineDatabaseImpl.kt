package mgmix.dev.line.repository.data.db

import mgmix.dev.line.repository.data.db.dao.LineDao
import mgmix.dev.line.repository.data.model.NoteItem

// Entity <-> Model Mapping

class LineDatabaseImpl(
    private val lineDao: LineDao
): LineDatabase {

    override fun getAllNotes(): List<NoteItem> {
        val list = arrayListOf<NoteItem>()
        val str = listOf("https://www.stripes.com/polopoly_fs/1.579681.1556982244!/image/image.jpg_gen/derivatives/landscape_900/image.jpg")
        for (i in 0..20) {
            list.add(
                NoteItem(
                    i,
                    "Example::$i",
                    "Contents::$i",
                    str
                )
            )
        }
        return list
    }

    override fun addNote(noteItem: NoteItem) {
//        lineDao.addNote()
    }

    override fun saveNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNoteDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}