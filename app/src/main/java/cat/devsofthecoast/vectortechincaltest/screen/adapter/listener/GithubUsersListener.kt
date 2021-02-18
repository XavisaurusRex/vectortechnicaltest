package cat.devsofthecoast.vectortechincaltest.screen.adapter.listener

import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.listener.BaseAdapterListener

interface GithubUsersListener: BaseAdapterListener<BaseDataWrapper> {

    fun onUserSelected(apiUser: ApiUser)

    fun loadMoreUsers(page: Int)

}