package cat.devsofthecoast.vectortechincaltest.common.view.navigator

import androidx.appcompat.app.AppCompatActivity
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.activity.UserDetailsActivity

class ScreensNavigator(private val activity: AppCompatActivity) {

    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toUserDetails(userId: String) {
        UserDetailsActivity.start(activity, userId)
    }
}