package cat.devsofthecoast.vectortechincaltest.common.view.navigator

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import cat.devsofthecoast.vectortechincaltest.common.view.fragment.ErrorDialogFragment
import cat.devsofthecoast.vectortechincaltest.common.view.fragment.LoadingDialogFragment

class DialogsNavigator(private val fragmentManager: FragmentManager) {

    fun showLoading(loadingMessage: String?) {
        fragmentManager.beginTransaction()
            .add(LoadingDialogFragment.newInstance(loadingMessage), LOADING_DIALOG_FRAGMENT)
            .addToBackStack(LOADING_DIALOG_FRAGMENT)
            .commit()
    }

    fun hideLoading() {
        fragmentManager.popBackStack(LOADING_DIALOG_FRAGMENT, POP_BACK_STACK_INCLUSIVE)
    }

    fun hideError() {
        fragmentManager.popBackStack(ERROR_DIALOG_FRAGMENT, POP_BACK_STACK_INCLUSIVE)
    }

    fun showErrorDialog(errorMessage: String? = null) {
        fragmentManager.beginTransaction()
            .add(ErrorDialogFragment.newInstance(errorMessage), ERROR_DIALOG_FRAGMENT)
            .addToBackStack(ERROR_DIALOG_FRAGMENT)
            .commit()
    }

    companion object {
        const val LOADING_DIALOG_FRAGMENT = "Loading Fragment"
        const val ERROR_DIALOG_FRAGMENT = "Error Fragment"
    }
}