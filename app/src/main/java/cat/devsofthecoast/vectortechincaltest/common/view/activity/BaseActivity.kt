package cat.devsofthecoast.vectortechincaltest.common.view.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.VTTApplication
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.activity.ActivityModule
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent

abstract class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as VTTApplication).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    private val injector get() = presentationComponent

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectView(injector)
        super.onCreate(savedInstanceState)
    }

    abstract fun injectView(presentationComponent: PresentationComponent)
}