package mgmix.dev.line.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mgmix.dev.line.repository.LineRepository
import mgmix.dev.line.repository.data.model.NoteItem
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: LineRepository
): ViewModel() {

    init {
        Log.d(TAG, "HomeViewModel Injection ... ")
    }

    private val _noteList = MutableLiveData<List<NoteItem>>()
    val noteList: LiveData<List<NoteItem>>
        get() = _noteList

    fun getNotes() {
        viewModelScope.launch {
            _noteList.value = withContext(Dispatchers.IO) {
                repository.getAllNotes()
            }
        }
    }

    companion object {
        const val TAG = "HomeViewModel"
    }

}