package mgmix.dev.line.di.ui

import dagger.Module
import dagger.Provides
import mgmix.dev.line.repository.LineRepository
import mgmix.dev.line.ui.detail.DetailViewModelFactory

@Module
class DetailModule {
    // TODO ViewholderFactory 하나로 묶고 Module 또한 통합하기
    @Provides
    fun provideViewModelFactory(
        repository: LineRepository
    ) = DetailViewModelFactory(repository)


}