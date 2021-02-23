package cat.devsofthecoast.vectortechincaltest.feature.userlist.domain.usecase

import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote.UserListRequest
import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GetPaginatedUsersUseCase(
    private val usersRepository: UserListRequest
) {
    suspend fun execute(page: Int, entryCount: Int): ResponseWrapper<List<UserDataWrapper>> =
        withContext(Dispatchers.IO) {

            val paginatedUsers = usersRepository.getUsers(page, entryCount)

            return@withContext if (paginatedUsers is ResponseWrapper.Success) {
                try {
                    val userDataWrappers: ArrayList<UserDataWrapper> = arrayListOf()

                    paginatedUsers.data.forEach {
                        userDataWrappers.add(UserDataWrapper(it))
                    }

                    ResponseWrapper.Success(userDataWrappers)
                } catch (exception: Exception) {
                    ResponseWrapper.Error(exception)
                }
            } else ResponseWrapper.Error()
        }

}