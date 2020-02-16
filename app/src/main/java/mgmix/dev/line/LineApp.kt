package mgmix.dev.line

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import mgmix.dev.line.di.DaggerApplicationComponent


open class LineApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}