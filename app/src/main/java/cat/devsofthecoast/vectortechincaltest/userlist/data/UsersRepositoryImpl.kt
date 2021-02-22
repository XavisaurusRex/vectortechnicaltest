package cat.devsofthecoast.vectortechincaltest.userlist.data

import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUserDetail
import cat.devsofthecoast.vectortechincaltest.userlist.domain.utils.ResponseWrapper
import retrofit2.Response

class UsersRepositoryImpl(
    val githubUsersRepository: GithubUsersRepository
) : UsersRepository {

    // TODO: 19/02/2021 IMPROVE THIS POINT, have to map into app unique response, and handle errors.
    override suspend fun getUsers(page: Int, entryCount: Int): ResponseWrapper<List<ApiUser>> {
        val response: Response<List<ApiUser>> = githubUsersRepository.requestUserList(page, entryCount)
        Thread.sleep(10000)
        return if (response.isSuccessful)
            ResponseWrapper.Success(
                response.body()!!
            ) else ResponseWrapper.Error()
    }

    override suspend fun getUserDetails(userId: String): ResponseWrapper<ApiUserDetail> {
        val response: Response<ApiUserDetail> = githubUsersRepository.requestUserDetails(userId)
        return if (response.isSuccessful)
            ResponseWrapper.Success(
                response.body()!!
            ) else ResponseWrapper.Error()
    }
}