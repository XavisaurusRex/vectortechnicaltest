package cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.VTTViewModelFactory
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserDetailBinding
import cat.devsofthecoast.vectortechincaltest.common.data.repository.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.UserDetailsAdapter
import cat.devsofthecoast.vectortechincaltest.common.view.activity.BaseActivity
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.Resource
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.model.UserDetails
import cat.devsofthecoast.vectortechincaltest.common.view.navigator.DialogsNavigator
import cat.devsofthecoast.vectortechincaltest.common.view.navigator.ScreensNavigator
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.viewmodel.UserDetailsViewModel
import com.bumptech.glide.Glide
import javax.inject.Inject

class UserDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    @Inject
    lateinit var githubUsersRepository: GithubUsersRepository

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    @Inject
    lateinit var viewModelFactory: VTTViewModelFactory

    private val viewModel: UserDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UserDetailsViewModel::class.java)
    }

    override fun injectView(presentationComponent: PresentationComponent) {
        presentationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObservers()

        binding.rcyUserDetails.adapter = UserDetailsAdapter()

        getUserIdFromExtras()?.let {
            binding.toolbar.title = it
            viewModel.updateUserDetails(it)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setUpObservers() {
        viewModel.userDetails.observe(::getLifecycle) { updateList(it) }
    }

    private fun updateList(result: Resource<UserDetails>) {
        when (result) {
            is Resource.Loading -> dialogsNavigator.showLoading(getString(R.string.dialog_userslist_loading_message))
            is Resource.Success -> {
                dialogsNavigator.hideLoading()
                manageSuccessResponse(result.data)
            }
            is Resource.UnknownError -> {
                dialogsNavigator.hideLoading()
                dialogsNavigator.showErrorDialog()
            }
        }
    }

    private fun manageSuccessResponse(userDetails: UserDetails) {
        Glide.with(this)
            .load(userDetails.avatarUrl)
            .placeholder(R.drawable.progress_animation)
            .into(binding.ivUserAvatar)

        (binding.rcyUserDetails.adapter as UserDetailsAdapter).setData(userDetails.userDetailsDataWrappers)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            screensNavigator.navigateBack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUserIdFromExtras(): String? {
        return intent.extras?.getString(EXTRA_USER_ID)
    }

    companion object {

        private const val EXTRA_USER_ID = "EXTRA_USER_ID"

        fun start(context: Context, userId: String) {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            context.startActivity(intent)
        }
    }
}
