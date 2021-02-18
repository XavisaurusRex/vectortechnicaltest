package cat.devsofthecoast.vectortechincaltest.screen.activity

import android.os.Bundle
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.di.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserListBinding
import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.GithubUsersAdapter
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.screen.base.activity.BaseActivity
import cat.devsofthecoast.vectortechincaltest.screen.navigator.DialogsNavigator
import cat.devsofthecoast.vectortechincaltest.screen.navigator.ScreensNavigator
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserListActivity : BaseActivity(), GithubUsersListener {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var binding: ActivityUserListBinding

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
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GithubUsersAdapter()
        binding.rcyGithubUsers.adapter = adapter
        adapter.setListener(this)

        dialogsNavigator.showLoading(getString(R.string.dialog_userslist_loading_message))
        requestUsersData(0, 100)
    }

    private fun requestUsersData(page: Int, itemsInPage: Int) {
        coroutineScope.launch {

            val response: Response<List<ApiUser>> = withContext(Dispatchers.Default) {
                githubUsersRepository.requestUserList(page, itemsInPage)
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    val userDataWrappers: ArrayList<UserDataWrapper> = arrayListOf()
                    it.forEach { apiUser ->
                        userDataWrappers.add(UserDataWrapper(apiUser))
                    }
                    (binding.rcyGithubUsers.adapter as GithubUsersAdapter).addData(userDataWrappers)
                }
            } else {
                Snackbar.make(binding.root, "ERROR...", Snackbar.LENGTH_SHORT).show()
            }
            dialogsNavigator.hideLoading()
        }
    }

    override fun onUserSelected(apiUser: ApiUser) {
        screensNavigator.toUserDetails(apiUser.username)
    }

    override fun loadMoreUsers(page: Int) {
        requestUsersData(page, 20)
    }
}