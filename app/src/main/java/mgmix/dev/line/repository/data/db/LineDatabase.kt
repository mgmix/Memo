package mgmix.dev.line.repository.data.db

import mgmix.dev.line.repository.data.model.NoteItem

interface LineDatabase {

    // Database Event 정의
    fun getAllNotes(): List<NoteItem>

    // add - Insert
    fun addNote(noteItem: NoteItem)

    // save - Update
    fun saveNote()

    // delete - Delete
    fun deleteNote()

    // get - get
    fun getNoteDetail()


}