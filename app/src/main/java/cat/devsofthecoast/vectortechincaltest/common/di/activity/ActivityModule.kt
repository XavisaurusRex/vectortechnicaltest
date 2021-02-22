package cat.devsofthecoast.vectortechincaltest.common.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import cat.devsofthecoast.vectortechincaltest.VTTViewModelFactory
import cat.devsofthecoast.vectortechincaltest.screen.navigator.DialogsNavigator
import cat.devsofthecoast.vectortechincaltest.screen.navigator.ScreensNavigator
import cat.devsofthecoast.vectortechincaltest.userlist.domain.usecases.GetPaginatedUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: AppCompatActivity
) {

    @Provides
    fun activity() = activity

    @Provides
    fun provideViewModelFactory(getPaginatedUsersUseCase: GetPaginatedUsersUseCase): VTTViewModelFactory =
        VTTViewModelFactory(getPaginatedUsersUseCase)

    @Provides
    @ActivityScope
    fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

    @Provides
    @ActivityScope
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    @ActivityScope
    fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

}