package cat.devsofthecoast.vectortechincaltest.userlist.domain.usecases

import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.userlist.data.UsersRepository
import cat.devsofthecoast.vectortechincaltest.userlist.domain.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPaginatedUsersUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun execute(page: Int, entryCount: Int): ResponseWrapper<List<UserDataWrapper>> =
        withContext(Dispatchers.IO) {
            val paginatedUsers = usersRepository.getUsers(page, entryCount)

            return@withContext if (paginatedUsers is ResponseWrapper.Success) {
                val userDataWrappers: ArrayList<UserDataWrapper> = arrayListOf()

                paginatedUsers.data.forEach {
                    userDataWrappers.add(UserDataWrapper(it))
                }

                ResponseWrapper.Success(userDataWrappers)
            } else ResponseWrapper.Error()
        }

}