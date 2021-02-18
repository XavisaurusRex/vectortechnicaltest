package cat.devsofthecoast.vectortechincaltest.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.LoadingDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.vh.LoadingViewHolder
import cat.devsofthecoast.vectortechincaltest.screen.adapter.vh.UserDataViewHolder
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.BaseAdapter
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

class GithubUsersAdapter() :
    BaseAdapter<BaseDataWrapper, BaseViewHolder<BaseDataWrapper>>() {

    override val data: ArrayList<BaseDataWrapper> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseDataWrapper> {
        return when (viewType) {
            VIEW_TYPE_LOADING -> LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.itemview_loading, null)
            )
            VIEW_TYPE_USERDATA -> UserDataViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.itemview_userdata, null)
            )
            else -> UserDataViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.itemview_userdata, null)
            )
        }

    }

    fun addData(userDataViewHolders: List<UserDataWrapper>) {
        if (data.size > 0) {
            data.removeLast()
        }
        data.addAll(userDataViewHolders)
        data.add(LoadingDataWrapper())
        notifyItemRangeInserted(data.size - userDataViewHolders.size+1, userDataViewHolders.size+1)
    }

    companion object {
        const val VIEW_TYPE_LOADING: Int = 1
        const val VIEW_TYPE_USERDATA: Int = 2
    }
}
