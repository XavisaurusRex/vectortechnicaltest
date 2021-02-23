package cat.devsofthecoast.vectortechincaltest.common.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.devsofthecoast.vectortechincaltest.feature.userlist.domain.usecase.GetPaginatedUsersUseCase
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.domain.usecase.GetUserDetailsUseCase
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.viewmodel.UserDetailsViewModel
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.viewmodel.UserListViewModel

class VTTViewModelFactory(
    private val getPaginatedUsersUseCase: GetPaginatedUsersUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(
                getPaginatedUsersUseCase
            ) as T
        }

        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(
                getUserDetailsUseCase
            ) as T
        }

        throw IllegalArgumentException("ViewModel Not Found")
    }
}