package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter

import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.LoadingDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.vh.LoadingViewHolder
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.vh.UserDataViewHolder
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.BaseAdapter
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder

class GithubUsersAdapter() :
    BaseAdapter<BaseDataWrapper, BaseViewHolder<BaseDataWrapper, GithubUsersListener>, GithubUsersListener>() {

    constructor(listener: GithubUsersListener) : this() {
        this.listener = listener
    }

    override val data: ArrayList<BaseDataWrapper> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseDataWrapper, GithubUsersListener> {
        return when (viewType) {
            VIEW_TYPE_LOADING -> LoadingViewHolder(parent)
            else -> UserDataViewHolder(parent)
        }

    }

    fun addData(userDataViewHolders: List<UserDataWrapper>) {
        if (data.size > 0) {
            data.removeLast()
        }
        data.addAll(userDataViewHolders)
        data.add(LoadingDataWrapper())
        notifyItemRangeInserted(
            data.size - userDataViewHolders.size + 1,
            userDataViewHolders.size + 1
        )
    }

    companion object {
        const val VIEW_TYPE_LOADING: Int = 1
        const val VIEW_TYPE_USERDATA: Int = 2
    }
}
