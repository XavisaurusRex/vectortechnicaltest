package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation

import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.activity.UserDetailsActivity
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.activity.UserListActivity
import cat.devsofthecoast.vectortechincaltest.common.view.fragment.ErrorDialogFragment
import cat.devsofthecoast.vectortechincaltest.common.view.fragment.LoadingDialogFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(activity: UserListActivity)

    fun inject(activity: UserDetailsActivity)

    fun inject(fragment: LoadingDialogFragment)

    fun inject(errorDialogFragment: ErrorDialogFragment)

}