package cat.devsofthecoast.vectortechincaltest.userlist.domain.usecases

import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUserDetail
import cat.devsofthecoast.vectortechincaltest.userlist.data.UsersRepository
import cat.devsofthecoast.vectortechincaltest.userlist.domain.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserDetailsUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun execute(userId: String): ResponseWrapper<ApiUserDetail> =
        withContext(Dispatchers.IO) {
            val userDetailsResponseWrapper = usersRepository.getUserDetails(userId)

            return@withContext if (userDetailsResponseWrapper is ResponseWrapper.Success) {
                ResponseWrapper.Success(userDetailsResponseWrapper.data)
            } else ResponseWrapper.Error()
        }

}