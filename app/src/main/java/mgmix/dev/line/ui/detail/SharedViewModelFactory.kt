package mgmix.dev.line.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mgmix.dev.line.repository.LineRepository

class SharedViewModelFactory(
    val repository: LineRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShareViewModel(repository) as T
    }
}