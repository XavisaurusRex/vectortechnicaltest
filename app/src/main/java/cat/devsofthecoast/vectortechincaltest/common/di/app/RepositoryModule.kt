package cat.devsofthecoast.vectortechincaltest.common.di.app

import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.userlist.data.UsersRepository
import cat.devsofthecoast.vectortechincaltest.userlist.data.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    @AppScope
    fun provideUsersRepository(githubUsersRepository: GithubUsersRepository): UsersRepository =
        UsersRepositoryImpl(githubUsersRepository)

    @Provides
    @AppScope
    fun githubUsersApi(@GithubApiScope retrofit: Retrofit): GithubUsersRepository =
        retrofit.create(GithubUsersRepository::class.java)
}
