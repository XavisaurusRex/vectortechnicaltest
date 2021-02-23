package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.activity

import android.content.Context
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote.UserListRequest
import cat.devsofthecoast.vectortechincaltest.feature.userlist.domain.usecase.GetPaginatedUsersUseCase
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.usecase.GetUserDetailsUseCase
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote.UserDetailsRequest
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideUsersUseCase(usersRepository: UserListRequest) = GetPaginatedUsersUseCase(usersRepository)

    @Provides
    fun provideUserDetails(context: Context, userDetailsRequest: UserDetailsRequest) = GetUserDetailsUseCase(context, userDetailsRequest)

}