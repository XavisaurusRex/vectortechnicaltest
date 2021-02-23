package cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.Resource
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.domain.usecase.GetPaginatedUsersUseCase
import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getPaginatedUsersUseCase: GetPaginatedUsersUseCase
) : ViewModel() {

    private var currentPage: Int = 0
    private val userListLoaded: ArrayList<UserDataWrapper> = arrayListOf()

    private var _usersListLoaded: Boolean = false
    val usersListLoaded: Boolean
        get() = _usersListLoaded

    private var _userDataList = MutableLiveData<Resource<List<UserDataWrapper>>>()
    val userDataList: LiveData<Resource<List<UserDataWrapper>>>
        get() = _userDataList

    fun changeUserListStateToLoaded() {
        _usersListLoaded = true
    }

    fun updateUsersList(showLoading: Boolean = true) {
        viewModelScope.launch(Dispatchers.IO) {
            if (showLoading) {
                _userDataList.postValue(Resource.Loading)
            }
            _userDataList.postValue(requestGithubUsers())
        }
    }

    private suspend fun requestGithubUsers(): Resource<List<UserDataWrapper>> {
        val paginatedUsers =
            getPaginatedUsersUseCase.execute(currentPage, DEFAULT_USERS_ENTRY_COUNT)
        return if (paginatedUsers is ResponseWrapper.Success) {
            currentPage++
            userListLoaded.addAll(paginatedUsers.data)
            Resource.Success(userListLoaded)
        } else {
            Resource.UnknownError()
        }
    }

    companion object {
        private const val DEFAULT_USERS_ENTRY_COUNT: Int = 5
    }
}