package cat.devsofthecoast.vectortechincaltest.common.di.presentation

import cat.devsofthecoast.vectortechincaltest.screen.activity.UserDetailsActivity
import cat.devsofthecoast.vectortechincaltest.screen.activity.UserListActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(fragment: UserListActivity)

    fun inject(activity: UserDetailsActivity)
}