package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.BaseAdapter
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.LoadingDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.vh.LoadingViewHolder
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.vh.UserDataViewHolder


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
        val positionStart: Int
        val itemCount: Int
        if (data.isEmpty()) {
            data.addAll(userDataViewHolders)
            positionStart = 0
            // +1 for loading wrapper
            itemCount = userDataViewHolders.size + 1
        } else {
            data.removeLast()
            val fromIndex = data.size
            val toIndex = userDataViewHolders.size
            data.addAll(userDataViewHolders.subList(fromIndex, toIndex))
            positionStart = fromIndex
            itemCount = toIndex - fromIndex
        }
        data.add(LoadingDataWrapper())
        notifyItemRangeInserted(positionStart, itemCount)
    }

    companion object {
        const val VIEW_TYPE_LOADING: Int = 1
        const val VIEW_TYPE_USERDATA: Int = 2
    }
}
