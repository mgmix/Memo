package mgmix.dev.line.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mgmix.dev.line.ext.LiveDataList
import mgmix.dev.line.repository.LineRepository
import mgmix.dev.line.repository.data.model.AttachmentItem
import mgmix.dev.line.repository.data.model.NoteItem
import javax.inject.Inject

class ShareViewModel @Inject constructor(
    private val repository: LineRepository
) : ViewModel() {

    init {
        Log.e(TAG, "ShareViewModel Injection ... ")
    }

    private val _keyId = MutableLiveData<Long>()
    val keyId: LiveData<Long>
        get() = _keyId

    private val _mode = MutableLiveData<Boolean>()
    val mode: LiveData<Boolean>
        get() = _mode

    private val _attachments = LiveDataList<AttachmentItem>()
    val attachments: LiveDataList<AttachmentItem>
        get() = _attachments


    val mTitle = MutableLiveData<String>()
    val mContent = MutableLiveData<String>()

    // Fix
    fun getNoteDetail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _keyId.value?.let {
                    repository.getNoteDetail(it).apply {
                        mTitle.postValue(title)
                        mContent.postValue(contents)
                        _attachments.postValue(attachments?.toMutableList())
                    }
                }
            }
        }
    }

    fun addNote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _keyId.value?.let {
                    repository.addNoteWithAttachments(
                        NoteItem(
                            keyId = it,
                            title = mTitle.value,
                            contents = mContent.value,
                            attachments = null
                        ),
                        attachments.value ?: emptyList()
                    )
                }
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                keyId.value?.let { repository.deleteNote(it) }
            }
        }
    }

    fun removeAttachment(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.removeAttachment(id)
            }
        }
    }


    fun addAttachment(path: String, desc: String) {
        _keyId.value?.let {
            _attachments.add(
                AttachmentItem(
                    null,
                    it,
                    path,
                    desc
                )
            )
        }
    }

    fun saveMode(mode: Boolean) {
        _mode.postValue(mode)
    }

    fun setKeyId(keyId: Long) {
        _keyId.value = keyId
    }

    companion object {
        const val TAG = "ShareViewModel"
    }

}