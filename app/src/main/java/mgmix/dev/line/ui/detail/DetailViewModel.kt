package mgmix.dev.line.ui.detail

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
import java.util.*
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: LineRepository
) : ViewModel() {

    init {
        Log.d(TAG, "DetailViewModel Injection ... ")
    }

    private val _keyId = MutableLiveData<Long>()
    val keyId: LiveData<Long>
        get() = _keyId

    private val _mode = MutableLiveData<Boolean>()
    val mode: LiveData<Boolean>
        get() = _mode

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _content = MutableLiveData<String>()
    val content: LiveData<String>
        get() = _content

    private val _item = MutableLiveData<NoteItem>()
    val item: LiveData<NoteItem>
        get() = _item


    fun getNoteDetail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d(TAG, "keyId: ${keyId.value}")
                val result = repository.getNoteDetail(keyId.value ?: Calendar.getInstance().timeInMillis)
                Log.d("DetailView", "result: ${result.title} :: ${result.contents}");
                _item.postValue(result)
            }
        }
    }

    fun addNote(noteItem: NoteItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                repository.addNote(noteItem)
                Log.d(TAG, "DetailViewModel NoteItem Binding: ${item.value} ");
            }
        }
    }

    fun saveMode(mode: Boolean) {
        _mode.postValue(mode)
    }

    fun setKeyId(keyId: Long) {
        _keyId.value = keyId
    }

    //    suspend fun saveNote(noteItem: NoteItem) {
//        if (noteItem.id == null) {
//            repository.addNote(noteItem)
//        } else {
//            repository.updateNote(noteItem)
//        }
//    }
    companion object {
        const val TAG = "DetailViewModel"
    }

}