package cat.devsofthecoast.vectortechincaltest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.devsofthecoast.vectortechincaltest.userlist.domain.usecases.GetPaginatedUsersUseCase
import cat.devsofthecoast.vectortechincaltest.userlist.presentation.viewmodel.UserListViewModel

class VTTViewModelFactory(
    private val getPaginatedUsersUseCase: GetPaginatedUsersUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(
                getPaginatedUsersUseCase
            ) as T
        }

        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(
                getPaginatedUsersUseCase
            ) as T
        }

        throw IllegalArgumentException("ViewModel Not Found")
    }
}