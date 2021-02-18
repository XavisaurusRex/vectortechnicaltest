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
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        binding.rcyUserDetails.adapter = UserDetailsAdapter()

        getUserIdFromExtras()?.let {
            requestUserDetails(it)
        }
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
                Thread.sleep(5000)
                githubUsersRepository.requestUserDetails(userId)
            }
            if (response.isSuccessful) {
                onUserLoaded(response.body())
            } else {
                Snackbar.make(binding.root, "Error has occurr...", Snackbar.LENGTH_SHORT).show()
            }
            dialogsNavigator.hideLoading()

        }
    }

    private fun onUserLoaded(apiUserDetail: ApiUserDetail?) {

        Glide.with(this)
            .load(apiUserDetail?.avatarUrl)
            .placeholder(R.drawable.progress_animation)
            .into(binding.ivUserAvatar)

        binding.toolbar.title = apiUserDetail?.name

        val dataWrappers: ArrayList<UserDetailRowDataWrapper> = arrayListOf()

        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_usenameid),
                apiUserDetail?.usenameId
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_id),
                apiUserDetail?.id.toString()
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_node_id),
                apiUserDetail?.node_id
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_avatarurl),
                apiUserDetail?.avatarUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_gravatarid),
                apiUserDetail?.gravatarId
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_url),
                apiUserDetail?.url
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_htmlurl),
                apiUserDetail?.htmlUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_followersurl),
                apiUserDetail?.followersUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_followingurl),
                apiUserDetail?.followingUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_gistsurl),
                apiUserDetail?.gistsUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_starredurl),
                apiUserDetail?.starredUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_subscriptionsurl),
                apiUserDetail?.subscriptionsUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_organizationsurl),
                apiUserDetail?.organizationsUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_reposurl),
                apiUserDetail?.reposUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_eventsurl),
                apiUserDetail?.eventsUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_receivedeventsurl),
                apiUserDetail?.receivedEventsUrl
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_type),
                apiUserDetail?.type
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_siteadmin),
                apiUserDetail?.siteAdmin
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_name),
                apiUserDetail?.name
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_company),
                apiUserDetail?.company
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_blog),
                apiUserDetail?.blog
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_location),
                apiUserDetail?.location
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_email),
                apiUserDetail?.email
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_hireable),
                apiUserDetail?.hireable
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_bio),
                apiUserDetail?.bio
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_twitterusername),
                apiUserDetail?.twitterUsername
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_publicrepos),
                apiUserDetail?.publicRepos
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_publicgists),
                apiUserDetail?.publicGists
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_followers),
                apiUserDetail?.followers
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_following),
                apiUserDetail?.following
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_createdat),
                apiUserDetail?.createdAt
            )
        )
        dataWrappers.add(
            UserDetailRowDataWrapper(
                getString(R.string.activity_user_detail_updatedat),
                apiUserDetail?.updatedAt
            )
        )

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
