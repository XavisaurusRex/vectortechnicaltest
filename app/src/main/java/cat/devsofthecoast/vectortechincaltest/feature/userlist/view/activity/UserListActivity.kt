package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.VTTViewModelFactory
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserListBinding
import cat.devsofthecoast.vectortechincaltest.common.data.repository.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.feature.userlist.data.model.ApiUser
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.GithubUsersAdapter
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.common.view.activity.BaseActivity
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.Resource
import cat.devsofthecoast.vectortechincaltest.common.view.navigator.DialogsNavigator
import cat.devsofthecoast.vectortechincaltest.common.view.navigator.ScreensNavigator
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.viewmodel.UserListViewModel
import javax.inject.Inject

class UserListActivity : BaseActivity(), GithubUsersListener {

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
    }

    private fun updateList(result: Resource<List<UserDataWrapper>>) {
        when (result) {
            is Resource.Loading -> dialogsNavigator.showLoading(getString(R.string.dialog_userslist_loading_message))
            is Resource.Success -> {
                dialogsNavigator.hideLoading()
                viewModel.changeUserListStateToLoaded()
                manageSuccessResponse(result.data)
            }
            is Resource.UnknownError -> {
                dialogsNavigator.hideLoading()
                dialogsNavigator.showErrorDialog()
            }
        }
    }

    private fun manageSuccessResponse(dataWrappers: List<UserDataWrapper>) {
        (binding.rcyGithubUsers.adapter as GithubUsersAdapter).addData(dataWrappers)
    }

    override fun onUserSelected(apiUser: ApiUser) {
        screensNavigator.toUserDetails(apiUser.username)
    }

    override fun loadMoreUsers(page: Int) {
        viewModel.updateUsersList(false)
    }
}