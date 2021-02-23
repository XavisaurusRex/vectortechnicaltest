package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.vectortechincaltest.common.view.viewmodel.Resource
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.model.UserDetails
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.usecase.GetUserDetailsUseCase
import cat.devsofthecoast.vectortechincaltest.common.domain.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : ViewModel() {

    private var _userDetails = MutableLiveData<Resource<UserDetails>>()
    val userDetails: LiveData<Resource<UserDetails>>
        get() = _userDetails

    fun updateUserDetails(userId: String, showLoading: Boolean = true) {
        viewModelScope.launch(Dispatchers.IO) {
            if (showLoading) {
                _userDetails.postValue(Resource.Loading)
            }
            _userDetails.postValue(requestUserDetails(userId))
        }
    }

    private suspend fun requestUserDetails(userId: String): Resource<UserDetails> {
        val paginatedUsers = getUserDetailsUseCase.execute(userId)
        return if (paginatedUsers is ResponseWrapper.Success) {
            Resource.Success(paginatedUsers.data)
        } else {
            Resource.UnknownError()
        }
    }
}