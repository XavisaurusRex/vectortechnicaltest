package cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist

import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw.LoadingDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.vh.LoadingViewHolder
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.vh.UserDataViewHolder
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.BaseAdapter
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

class GithubUsersAdapter() :
    BaseAdapter<BaseDataWrapper, BaseViewHolder<BaseDataWrapper, GithubUsersListener>, GithubUsersListener>() {

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

    fun setListener(listener: GithubUsersListener) {
        this.listener = listener
    }

    companion object {
        const val VIEW_TYPE_LOADING: Int = 1
        const val VIEW_TYPE_USERDATA: Int = 2
    }
}
