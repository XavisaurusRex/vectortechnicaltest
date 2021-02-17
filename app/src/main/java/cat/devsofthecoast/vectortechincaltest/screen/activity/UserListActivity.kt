package cat.devsofthecoast.vectortechincaltest.screen.activity

import android.os.Bundle
import cat.devsofthecoast.vectortechincaltest.common.di.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.ActivityUserListBinding
import cat.devsofthecoast.vectortechincaltest.networking.GithubUsersRepository
import cat.devsofthecoast.vectortechincaltest.networking.api.ApiUser
import cat.devsofthecoast.vectortechincaltest.screen.activity.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserListActivity : BaseActivity() {
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


        coroutineScope.launch {

            binding.tvMain.text = "LOADING"
            val response: Response<List<ApiUser>> = withContext(Dispatchers.Default) {
                githubUsersRepository.lastActiveQuestions()
            }
            if (response.isSuccessful) {
                response.body()?.forEach {
                    binding.tvMain.text = binding.tvMain.text.toString() + "\n USER -> " + it.username
                }
            } else {
                binding.tvMain.text = "ERROR... " + response.errorBody().toString()
            }
        }
        binding.tvMain.text
    }
}