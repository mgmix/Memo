package mgmix.dev.line.repository

import mgmix.dev.line.repository.data.model.NoteItem

interface LineRepository {

    // Repository 메소드 정의

    // add
    suspend fun addNote(noteItem: NoteItem)

    // update
    suspend fun updateNote(noteItem: NoteItem)

    // remove
    suspend fun removeNote(id: Int)

    // fetch
    suspend fun getAllNotes(): List<NoteItem>
    suspend fun getNoteDetail(keyId: Long): NoteItem



}