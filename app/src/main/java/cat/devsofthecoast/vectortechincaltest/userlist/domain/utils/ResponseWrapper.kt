package cat.devsofthecoast.vectortechincaltest.userlist.domain.utils

import java.lang.Exception

sealed class ResponseWrapper<out R> {
    data class Success<out S>(val data: S) : ResponseWrapper<S>()
    data class Error(val throwable: Throwable = Exception()) : ResponseWrapper<Nothing>()
}