package cat.devsofthecoast.vectortechincaltest.screen.navigator

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import cat.devsofthecoast.vectortechincaltest.screen.fragment.LoadingDialogFragment

class DialogsNavigator(private val fragmentManager: FragmentManager) {

    fun showLoading(loadingMessage: String?) {
        fragmentManager.beginTransaction()
            .add(LoadingDialogFragment.newInstance(loadingMessage), LOADING_FRAGMENT)
            .addToBackStack(LOADING_FRAGMENT)
            .commit()
    }

    fun hideLoading() {
        fragmentManager.popBackStack(LOADING_FRAGMENT, POP_BACK_STACK_INCLUSIVE)
    }

    companion object {
        const val LOADING_FRAGMENT = "Loading Fragment"
    }
}