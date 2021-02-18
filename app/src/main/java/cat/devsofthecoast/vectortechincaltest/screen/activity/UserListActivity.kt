package cat.devsofthecoast.vectortechincaltest.screen.activity

import android.os.Bundle
import cat.devsofthecoast.vectortechincaltest.common.di.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserListBinding
import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.screen.adapter.GithubUsersAdapter
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.screen.base.activity.BaseActivity
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
        requestUsersData(0, 20)

    }

    private fun requestUsersData(page: Int, itemsInPage: Int) {
        coroutineScope.launch {

            Snackbar.make(binding.root, "Loading", Snackbar.LENGTH_SHORT).show()
            val response: Response<List<ApiUser>> = withContext(Dispatchers.Default) {
                githubUsersRepository.lastActiveQuestions(page, itemsInPage)
            }
            if (response.isSuccessful) {
                Snackbar.make(binding.root, "LOADED USERS", Snackbar.LENGTH_SHORT).show()

                response.body()?.also {
                    val userDataWrappers: ArrayList<UserDataWrapper> = arrayListOf()
                    it.forEach { apiUsert ->
                        userDataWrappers.add(UserDataWrapper(apiUsert))
                    }
                    (binding.rcyGithubUsers.adapter as GithubUsersAdapter).addData(userDataWrappers)

                }
//                response.body()?.forEach {
//
//                    binding.tvMain.text.toString() + "\n USER -> " + it.username
//                }
            } else {
                Snackbar.make(binding.root, "ERROR...", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onUserSelected(apiUser: ApiUser) {
        Snackbar.make(
            binding.root,
            "USER -> " + apiUser.username + " Selected!",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun loadMoreUsers(page: Int) {
        requestUsersData(page, 20)
    }
}