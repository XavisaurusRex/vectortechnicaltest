package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw

import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.GithubUsersAdapter
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper

class LoadingDataWrapper : BaseDataWrapper() {
    override val viewType: Int = GithubUsersAdapter.VIEW_TYPE_LOADING
}