package cat.devsofthecoast.vectortechincaltest.common.di.presentation

import cat.devsofthecoast.vectortechincaltest.screen.activity.UserDetailsActivity
import cat.devsofthecoast.vectortechincaltest.screen.activity.UserListActivity
import cat.devsofthecoast.vectortechincaltest.screen.fragment.LoadingDialogFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(activity: UserListActivity)

    fun inject(activity: UserDetailsActivity)

    fun inject(fragment: LoadingDialogFragment)
}