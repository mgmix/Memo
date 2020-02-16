package mgmix.dev.line.ui.home

import androidx.lifecycle.ViewModel
import mgmix.dev.line.repository.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {


    val getNotes
            = repository.getAllNotes()

}