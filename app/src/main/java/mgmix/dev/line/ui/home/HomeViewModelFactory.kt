package mgmix.dev.line.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mgmix.dev.line.repository.HomeRepository

class HomeViewModelFactory(
    val repository: HomeRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}