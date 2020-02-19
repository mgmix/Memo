package mgmix.dev.line.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import mgmix.dev.line.LineApp
import mgmix.dev.line.di.ui.HomeModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        Binder::class,
        HomeModule::class,
        LocalModule::class,
        RepositoryModule::class
    ])
interface ApplicationComponent: AndroidInjector<LineApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): ApplicationComponent
    }

}