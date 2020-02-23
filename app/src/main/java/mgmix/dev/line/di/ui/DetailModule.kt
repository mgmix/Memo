package mgmix.dev.line.di.ui

import dagger.Module
import dagger.Provides
import mgmix.dev.line.repository.LineRepository
import mgmix.dev.line.ui.detail.SharedViewModelFactory

@Module
class DetailModule {

    @Provides
    fun provideViewModelFactory(
        repository: LineRepository
    ) = SharedViewModelFactory(repository)


}