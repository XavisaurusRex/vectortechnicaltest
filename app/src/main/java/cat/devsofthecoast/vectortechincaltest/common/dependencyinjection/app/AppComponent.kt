package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.app

import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.activity.ActivityComponent
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}