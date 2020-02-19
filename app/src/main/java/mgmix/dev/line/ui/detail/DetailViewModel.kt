package mgmix.dev.line.ui.detail

import androidx.lifecycle.ViewModel
import mgmix.dev.line.repository.LineRepository
import mgmix.dev.line.repository.data.model.NoteItem

class DetailViewModel(
    private val repository: LineRepository
): ViewModel() {

    fun init() {


    }

    suspend fun saveNote(noteItem: NoteItem) {
        if (noteItem.id == null) {
            repository.addNote(noteItem)
        } else {
            repository.updateNote(noteItem)
        }
    }

}