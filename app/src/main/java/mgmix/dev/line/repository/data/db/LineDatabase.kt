package mgmix.dev.line.repository.data.db

import mgmix.dev.line.repository.data.model.NoteItem

interface LineDatabase {

    // Database Event 정의
    suspend fun getAllNotes(): List<NoteItem>

    suspend fun addNote(noteItem: NoteItem)

    suspend fun saveNote(noteItem: NoteItem)

    // delete - Delete
    suspend fun deleteNote(id: NoteItem)

    // get - get
    suspend fun getNoteDetail(keyId: Long): NoteItem


}