package cat.devsofthecoast.vectortechincaltest.common.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import cat.devsofthecoast.vectortechincaltest.screen.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: AppCompatActivity
) {

    @Provides
    fun activity() = activity

    @Provides
    @ActivityScope
    fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

}