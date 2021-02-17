package cat.devsofthecoast.vectortechincaltest.networking

import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUsersRepository {
    @GET("/users")
    suspend fun lastActiveQuestions(
        @Query("page") pageIndex: Int? = 0,
        @Query("per_page") responseLength: Int? = 20
    ): Response<List<ApiUser>>

}