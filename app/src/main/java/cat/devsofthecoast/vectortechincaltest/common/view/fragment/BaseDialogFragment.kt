package cat.devsofthecoast.vectortechincaltest.common.view.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.common.view.activity.BaseActivity

abstract class BaseDialogFragment : DialogFragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }

    private val injector get() = presentationComponent

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectView(injector)
        setStyle(STYLE_NORMAL, R.style.Theme_App_Dialog_FullScreen)

        super.onCreate(savedInstanceState)
    }


    abstract fun injectView(presentationComponent: PresentationComponent)
}