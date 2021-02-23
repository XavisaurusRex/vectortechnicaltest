package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection

import android.app.Application
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.app.AppComponent
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.app.AppModule
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.app.DaggerAppComponent

class VTTApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}