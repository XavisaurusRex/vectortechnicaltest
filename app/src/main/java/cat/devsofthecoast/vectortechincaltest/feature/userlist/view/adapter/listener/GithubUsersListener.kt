package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.listener

import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.model.ApiUser
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.listener.BaseAdapterListener

interface GithubUsersListener: BaseAdapterListener<BaseDataWrapper> {

    fun onUserSelected(apiUser: ApiUser)

    fun loadMoreUsers()

}