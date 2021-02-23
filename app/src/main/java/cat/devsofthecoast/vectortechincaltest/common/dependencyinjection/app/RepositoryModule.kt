package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.app

import cat.devsofthecoast.vectortechincaltest.common.data.repository.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote.UserListRequest
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.remote.impl.UserListRequestImpl
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote.UserDetailsRequest
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote.impl.UserDetailsRequestImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    @AppScope
    fun provideUserListRequest(githubUsersRepository: GithubUsersRepository): UserListRequest =
        UserListRequestImpl(githubUsersRepository)

    @Provides
    @AppScope
    fun provideUserLDetailsRequest(githubUsersRepository: GithubUsersRepository): UserDetailsRequest =
        UserDetailsRequestImpl(githubUsersRepository)

    @Provides
    @AppScope
    fun githubUsersApi(@GithubApiScope retrofit: Retrofit): GithubUsersRepository =
        retrofit.create(GithubUsersRepository::class.java)
}
