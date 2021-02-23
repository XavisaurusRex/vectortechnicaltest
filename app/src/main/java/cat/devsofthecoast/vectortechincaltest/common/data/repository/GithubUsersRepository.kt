package cat.devsofthecoast.vectortechincaltest.common.data.repository

import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.model.ApiUser
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.data.model.ApiUserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUsersRepository {
    @GET("/users")
    suspend fun requestUserList(
        @Query("page") pageIndex: Int? = 0,
        @Query("per_page") responseLength: Int? = 20
    ): Response<List<ApiUser>>

    @GET("/users/{username}")
    suspend fun requestUserDetails(
        @Path("username") userId: String): Response<ApiUserDetail>

}