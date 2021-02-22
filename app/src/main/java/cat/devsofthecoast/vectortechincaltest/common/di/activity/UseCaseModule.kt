package cat.devsofthecoast.vectortechincaltest.common.di.activity

import cat.devsofthecoast.vectortechincaltest.userlist.data.UsersRepository
import cat.devsofthecoast.vectortechincaltest.userlist.domain.usecases.GetPaginatedUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun users(usersRepository: UsersRepository) = GetPaginatedUsersUseCase(usersRepository)

}