package cat.devsofthecoast.vectortechincaltest.screen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.di.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserDetailBinding
import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUserDetail
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.UserDetailsAdapter
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.activity.BaseActivity
import cat.devsofthecoast.vectortechincaltest.screen.navigator.DialogsNavigator
import cat.devsofthecoast.vectortechincaltest.screen.navigator.ScreensNavigator
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserDetailsActivity : BaseActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var binding: ActivityUserDetailBinding

    @Inject
    lateinit var githubUsersRepository: GithubUsersRepository

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    override fun injectView(presentationComponent: PresentationComponent) {
        presentationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rcyUserDetails.adapter = UserDetailsAdapter()

        getUserIdFromExtras()?.let {
            requestUserDetails(it)
            binding.toolbar.title = it
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            screensNavigator.navigateBack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUserIdFromExtras(): String? {
        return intent.extras?.getString(EXTRA_USER_ID);
    }

    private fun requestUserDetails(userId: String) {
        coroutineScope.launch {

            dialogsNavigator.showLoading(
                String.format(
                    getString(R.string.dialog_userdetails_loading_message),
                    userId
                )
            )
            val response: Response<ApiUserDetail> = withContext(Dispatchers.Default) {
                githubUsersRepository.requestUserDetails(userId)
            }
            if (response.isSuccessful) {
                onUserLoaded(response.body()!!)
            } else {
                Snackbar.make(binding.root, "Error has occurr...", Snackbar.LENGTH_SHORT).show()
            }
            dialogsNavigator.hideLoading()

        }
    }

    private fun onUserLoaded(apiUserDetail: ApiUserDetail) {

        Glide.with(this)
            .load(apiUserDetail.avatarUrl)
            .placeholder(R.drawable.progress_animation)
            .into(binding.ivUserAvatar)

        binding.toolbar.title = apiUserDetail.name

        val dataWrappers: ArrayList<UserDetailRowDataWrapper> = arrayListOf()

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

        (binding.rcyUserDetails.adapter as UserDetailsAdapter).setData(dataWrappers)
    }

    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
        fun start(context: Context, userId: String) {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            context.startActivity(intent)
        }
    }
}
