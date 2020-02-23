package mgmix.dev.line.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mgmix.dev.line.di.scope.FragmentScope
import mgmix.dev.line.di.ui.DetailModule
import mgmix.dev.line.di.ui.HomeModule
import mgmix.dev.line.ui.attachment.AttachmentFragment
import mgmix.dev.line.ui.detail.DetailFragment
import mgmix.dev.line.ui.home.HomeFragment

@Module
abstract class Binder{

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindDetailFragment(): DetailFragment

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindAttachFragment(): AttachmentFragment

}