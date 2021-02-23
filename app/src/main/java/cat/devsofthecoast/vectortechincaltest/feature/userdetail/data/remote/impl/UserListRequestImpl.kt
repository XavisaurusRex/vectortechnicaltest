package cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote.impl

import cat.devsofthecoast.vectortechincaltest.common.data.repository.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote.UserListRequest
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.model.ApiUser
import retrofit2.Response

class UserListRequestImpl(
    private val githubUsersRepository: GithubUsersRepository
) : UserListRequest {

    override suspend fun getUsers(page: Int, entryCount: Int): ResponseWrapper<List<ApiUser>> {
        val response: Response<List<ApiUser>> =
            githubUsersRepository.requestUserList(page, entryCount)
        return if (response.isSuccessful)
            ResponseWrapper.Success(
                response.body()!!
            ) else ResponseWrapper.Error()
    }

}