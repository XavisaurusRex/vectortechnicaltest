package cat.devsofthecoast.vectortechincaltest.userlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.userlist.domain.usecases.GetPaginatedUsersUseCase
import cat.devsofthecoast.vectortechincaltest.userlist.domain.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getPaginatedUsersUseCase: GetPaginatedUsersUseCase
) : ViewModel() {

    private var currentPage: Int = 0

    private var _usersListLoaded: Boolean = false
    val usersListLoaded: Boolean
        get() = _usersListLoaded

    private var _userDataList = MutableLiveData<List<UserDataWrapper>>()
    val userDataList: LiveData<List<UserDataWrapper>>
        get() = _userDataList

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun changeUserListStateToLoaded() {
        _usersListLoaded = true
    }

    fun updateUsersList(showLoading: Boolean = true) {
        viewModelScope.launch(Dispatchers.IO) {
            if (showLoading) {
                _loading.postValue(showLoading)
            }
            _userDataList.postValue(requestGithubUsers())

            if (showLoading) {
                _loading.postValue(false)
            }
        }
    }

    private suspend fun requestGithubUsers(): List<UserDataWrapper> {
        val paginatedUsers =
            getPaginatedUsersUseCase.execute(currentPage, DEFAULT_USERS_ENTRY_COUNT)
        return if (paginatedUsers is ResponseWrapper.Success) {
            currentPage++
            val list = paginatedUsers.data.toMutableList()

            _userDataList.value?.let {
                list.addAll(0, it)
            }

            list
        } else {
            // TODO: 19/02/2021 Should revisit this, I don't have error handling
            arrayListOf()
        }
    }

    companion object {
        private const val DEFAULT_USERS_ENTRY_COUNT: Int = 20
    }
}