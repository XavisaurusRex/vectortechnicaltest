package cat.devsofthecoast.vectortechincaltest.screen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.di.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserDetailBinding
import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUserDetail
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.UserDetailsAdapter
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.activity.BaseActivity
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


    override fun injectView(presentationComponent: PresentationComponent) {
        presentationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.rcyUserDetails.adapter = UserDetailsAdapter()

        getUserIdFromExtras()?.let {
            requestUserDetails(it)
        }
    }

    private fun getUserIdFromExtras(): String? {
        return intent.extras?.getString(EXTRA_USER_ID);

    }

    private fun requestUserDetails(userId: String) {
        coroutineScope.launch {

            Snackbar.make(binding.root, "Loading", Snackbar.LENGTH_SHORT).show()
            val response: Response<ApiUserDetail> = withContext(Dispatchers.Default) {
                githubUsersRepository.requestUserDetails(userId)
            }
            if (response.isSuccessful) {
                Snackbar.make(binding.root, "LOADED USER DETAILS", Snackbar.LENGTH_SHORT).show()
                onUserLoaded(response.body())
            } else {
                Snackbar.make(binding.root, "ERROR...", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun onUserLoaded(apiUserDetail: ApiUserDetail?) {

        Glide.with(this)
            .load(apiUserDetail?.avatarUrl)
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
