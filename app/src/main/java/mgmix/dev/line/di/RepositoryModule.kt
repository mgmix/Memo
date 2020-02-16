package mgmix.dev.line.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import mgmix.dev.line.repository.HomeRepository
import mgmix.dev.line.repository.data.dao.HomeDao
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository
            = HomeRepository()
}