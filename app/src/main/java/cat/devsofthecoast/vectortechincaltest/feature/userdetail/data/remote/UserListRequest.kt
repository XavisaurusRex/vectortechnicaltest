package cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote

import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.model.ApiUser

interface UserListRequest {
    suspend fun getUsers(page: Int, entryCount: Int): ResponseWrapper<List<ApiUser>>
}