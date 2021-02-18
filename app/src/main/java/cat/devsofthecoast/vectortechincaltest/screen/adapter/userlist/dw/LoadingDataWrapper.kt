package cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw

import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.GithubUsersAdapter
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper

class LoadingDataWrapper : BaseDataWrapper() {
    override val viewType: Int = GithubUsersAdapter.VIEW_TYPE_LOADING
}