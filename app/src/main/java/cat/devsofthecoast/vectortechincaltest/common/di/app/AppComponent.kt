package cat.devsofthecoast.vectortechincaltest.common.di.app

import cat.devsofthecoast.vectortechincaltest.common.di.activity.ActivityComponent
import cat.devsofthecoast.vectortechincaltest.common.di.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}