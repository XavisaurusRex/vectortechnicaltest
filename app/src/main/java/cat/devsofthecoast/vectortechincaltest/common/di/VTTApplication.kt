package cat.devsofthecoast.vectortechincaltest.common.di

import android.app.Application
import cat.devsofthecoast.vectortechincaltest.common.di.app.AppModule
import cat.devsofthecoast.vectortechincaltest.common.di.app.DaggerAppComponent

class VTTApplication : Application() {

    public val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}