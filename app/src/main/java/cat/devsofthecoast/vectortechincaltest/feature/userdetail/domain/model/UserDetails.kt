package cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.model

import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.dw.UserDetailRowDataWrapper

data class UserDetails(
    val avatarUrl: String?,
    val name: String?,
    val userDetailsDataWrappers: List<UserDetailRowDataWrapper>
)