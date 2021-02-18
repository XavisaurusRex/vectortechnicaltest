package cat.devsofthecoast.vectortechincaltest.screen

import androidx.appcompat.app.AppCompatActivity
import cat.devsofthecoast.vectortechincaltest.screen.activity.UserDetailsActivity

class ScreensNavigator (private val activity: AppCompatActivity) {

    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toUserDetails(userId: String) {
        UserDetailsActivity.start(activity, userId)
    }
}