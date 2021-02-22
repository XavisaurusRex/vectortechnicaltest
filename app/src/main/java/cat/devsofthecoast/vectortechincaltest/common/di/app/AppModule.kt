package cat.devsofthecoast.vectortechincaltest.common.di.app

import android.app.Application
import cat.devsofthecoast.vectortechincaltest.BuildConfig
import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {

    @Provides
    fun application() = application

    @Provides
    @AppScope
    @GithubApiScope
    fun retrofit(@GithubApiScope okHttpClient: OkHttpClient): Retrofit {
        //https://api.github.com/users?page=2&per_page=3
        return Retrofit.Builder()
            .baseUrl(BASE_GITHUB_USERS_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    @GithubApiScope
    fun provideOkHttpClientForGithubApiRequests(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClientBuilder.addInterceptor(logging)
        }
        return httpClientBuilder.build()
    }


    companion object {
        const val BASE_GITHUB_USERS_URL = "https://api.github.com/"
    }
}