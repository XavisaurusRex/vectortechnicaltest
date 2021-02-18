package cat.devsofthecoast.vectortechincaltest.screen.adapter.dw

import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.screen.adapter.GithubUsersAdapter
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper

class UserDataWrapper(val apiUser: ApiUser) : BaseDataWrapper() {
    override val viewType: Int = GithubUsersAdapter.VIEW_TYPE_USERDATA
}