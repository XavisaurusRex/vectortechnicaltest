package cat.devsofthecoast.vectortechincaltest.networking.api

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("login") val username: String
)
