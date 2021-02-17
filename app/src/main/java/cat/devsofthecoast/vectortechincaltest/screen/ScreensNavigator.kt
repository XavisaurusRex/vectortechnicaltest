package cat.devsofthecoast.vectortechincaltest.screen

import androidx.appcompat.app.AppCompatActivity
import cat.devsofthecoast.vectortechincaltest.screen.activity.UserDetailsActivity

class ScreensNavigator (private val activity: AppCompatActivity) {

    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toQuestionDetails(questionId: String) {
        UserDetailsActivity.start(activity, questionId)
    }
}