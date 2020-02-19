package mgmix.dev.line.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mgmix.dev.line.repository.LineRepository

class HomeViewModelFactory(
    val repository: LineRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}