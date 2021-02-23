package cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote.impl

import cat.devsofthecoast.vectortechincaltest.common.data.repository.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.model.ApiUserDetail
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote.UserDetailsRequest
import retrofit2.Response

class UserDetailsRequestImpl(
    private val githubUsersRepository: GithubUsersRepository
) : UserDetailsRequest {

    override suspend fun getUserDetails(userId: String): ResponseWrapper<ApiUserDetail> {
        val response: Response<ApiUserDetail> = githubUsersRepository.requestUserDetails(userId)
        return if (response.isSuccessful)
            ResponseWrapper.Success(
                response.body()!!
            ) else ResponseWrapper.Error()
    }

}