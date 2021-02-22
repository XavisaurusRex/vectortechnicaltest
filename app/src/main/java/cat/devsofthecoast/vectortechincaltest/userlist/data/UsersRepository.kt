package cat.devsofthecoast.vectortechincaltest.userlist.data

import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUserDetail
import cat.devsofthecoast.vectortechincaltest.userlist.domain.utils.ResponseWrapper

interface UsersRepository {
    suspend fun getUsers(page: Int, entryCount: Int): ResponseWrapper<List<ApiUser>>
    suspend fun getUserDetails(userId: String): ResponseWrapper<ApiUserDetail>
}