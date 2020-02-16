package mgmix.dev.line.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mgmix.dev.line.di.scope.FragmentScope
import mgmix.dev.line.di.ui.HomeModule
import mgmix.dev.line.ui.home.HomeFragment

@Module
abstract class Binder{

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment

}