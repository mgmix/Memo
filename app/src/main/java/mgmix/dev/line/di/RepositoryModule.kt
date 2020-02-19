package mgmix.dev.line.di

import dagger.Module
import dagger.Provides
import mgmix.dev.line.repository.LineRepositoryImpl
import mgmix.dev.line.repository.LineRepository
import mgmix.dev.line.repository.data.db.LineDatabase
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLineRepository(
        db: LineDatabase
    ): LineRepository
            = LineRepositoryImpl(db)
}