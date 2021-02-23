package cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote

import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.model.ApiUserDetail

interface UserDetailsRequest {
    suspend fun getUserDetails(userId: String): ResponseWrapper<ApiUserDetail>
}