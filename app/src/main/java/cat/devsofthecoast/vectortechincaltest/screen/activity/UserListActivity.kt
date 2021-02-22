package cat.devsofthecoast.vectortechincaltest.screen.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.VTTViewModelFactory
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
import cat.devsofthecoast.vectortechincaltest.userlist.presentation.viewmodel.UserListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    @Inject
    lateinit var viewModelFactory: VTTViewModelFactory


    private val viewModel: UserListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UserListViewModel::class.java)
    }

    override fun injectView(presentationComponent: PresentationComponent) {
        presentationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcyGithubUsers.adapter = GithubUsersAdapter(this)

        setUpObservers()

        if (viewModel.usersListLoaded.not()) {
            viewModel.updateUsersList()
        }
    }

    private fun setUpObservers() {
        viewModel.userDataList.observe(::getLifecycle) { updateList(it) }
        viewModel.loading.observe(::getLifecycle) { showLoading(it) }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            dialogsNavigator.showLoading(getString(R.string.dialog_userslist_loading_message))
        } else {
            dialogsNavigator.hideLoading()
        }
    }

    private fun updateList(userDataWrappers: List<UserDataWrapper>) {
        viewModel.changeUserListStateToLoaded()
        (binding.rcyGithubUsers.adapter as GithubUsersAdapter).addData(userDataWrappers)
    }

    override fun onUserSelected(apiUser: ApiUser) {
        screensNavigator.toUserDetails(apiUser.username)
    }

    override fun loadMoreUsers(page: Int) {
        viewModel.updateUsersList(false)
    }
}