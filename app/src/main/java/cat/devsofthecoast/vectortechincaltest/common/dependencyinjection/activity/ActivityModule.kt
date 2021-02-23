package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.activity

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.VTTViewModelFactory
import cat.devsofthecoast.vectortechincaltest.common.view.navigator.DialogsNavigator
import cat.devsofthecoast.vectortechincaltest.common.view.navigator.ScreensNavigator
import cat.devsofthecoast.vectortechincaltest.feature.userlist.domain.usecase.GetPaginatedUsersUseCase
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.usecase.GetUserDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: AppCompatActivity
) {

    @Provides
    fun activity() = activity

    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun provideViewModelFactory(
        getPaginatedUsersUseCase: GetPaginatedUsersUseCase,
        userDetailsUseCase: GetUserDetailsUseCase
    ): VTTViewModelFactory =
        VTTViewModelFactory(
            getPaginatedUsersUseCase,
            userDetailsUseCase
        )

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