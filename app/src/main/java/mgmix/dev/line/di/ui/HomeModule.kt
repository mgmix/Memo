package mgmix.dev.line.di.ui

import dagger.Module
import dagger.Provides
import mgmix.dev.line.repository.HomeRepository
import mgmix.dev.line.ui.home.HomeListAdapter
import mgmix.dev.line.ui.home.HomeViewModelFactory

@Module
class HomeModule {

    @Provides
    fun provideViewModelFactory(
        repository: HomeRepository
    ) = HomeViewModelFactory(repository)

    @Provides
    fun provideHomeAdapter()
            = HomeListAdapter()
}