package cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.usecase

import android.content.Context
import androidx.annotation.StringRes
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.model.UserDetails
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.remote.UserDetailsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserDetailsUseCase(
    private val context: Context,
    private val userDetailsRequest: UserDetailsRequest
) {
    suspend fun execute(userId: String): ResponseWrapper<UserDetails> =
        withContext(Dispatchers.IO) {
            val userDetailsResponseWrapper = userDetailsRequest.getUserDetails(userId)

            return@withContext if (userDetailsResponseWrapper is ResponseWrapper.Success) {
                try {
                    val dataWrappers: ArrayList<UserDetailRowDataWrapper> = arrayListOf()
                    val apiUserDetail = userDetailsResponseWrapper.data
                    dataWrappers.add(
                        UserDetailRowDataWrapper(
                            getString(R.string.activity_user_detail_usenameid),
                            apiUserDetail.usenameId
                        )
                    )

                    dataWrappers.add(
                        UserDetailRowDataWrapper(
                            getString(R.string.activity_user_detail_id),
                            apiUserDetail.id.toString()
                        )
                    )

                    apiUserDetail.node_id?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_node_id),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.avatarUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_avatarurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.gravatarId?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_gravatarid),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.url?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_url),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.htmlUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_htmlurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.followersUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_followersurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.followingUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_followingurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.gistsUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_gistsurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.starredUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_starredurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.subscriptionsUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_subscriptionsurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.organizationsUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_organizationsurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.reposUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_reposurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.eventsUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_eventsurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.receivedEventsUrl?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_receivedeventsurl),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.type?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_type),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.siteAdmin?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_siteadmin),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.name?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_name),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.company?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_company),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.blog?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_blog),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.location?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_location),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.email?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_email),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.hireable?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_hireable),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.bio?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_bio),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.twitterUsername?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_twitterusername),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.publicRepos?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_publicrepos),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.publicGists?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_publicgists),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.followers?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_followers),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.following?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_following),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.createdAt?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_createdat),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    apiUserDetail.updatedAt?.let {
                        dataWrappers.add(
                            UserDetailRowDataWrapper(
                                getString(R.string.activity_user_detail_updatedat),
                                if (it.isEmpty()) getString(R.string.itemview_userdetails_row_default_value_empty) else it
                            )
                        )
                    }

                    ResponseWrapper.Success(
                        UserDetails(
                            apiUserDetail.avatarUrl,
                            apiUserDetail.name,
                            dataWrappers
                        )
                    )
                } catch (ex: Exception) {
                    ResponseWrapper.Error(ex)
                }
            } else ResponseWrapper.Error()
        }

    private fun getString(@StringRes stringId: Int): String = context.getString(stringId)

}