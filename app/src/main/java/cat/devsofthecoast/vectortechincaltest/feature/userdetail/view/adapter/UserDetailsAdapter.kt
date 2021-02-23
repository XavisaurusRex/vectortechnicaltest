package cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter

import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.vh.UserDetailRowViewHolder
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.BaseAdapter
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder

class UserDetailsAdapter :
    BaseAdapter<UserDetailRowDataWrapper, BaseViewHolder<UserDetailRowDataWrapper,
            BaseAdapterListener<UserDetailRowDataWrapper>>, BaseAdapterListener<UserDetailRowDataWrapper>>() {

    override val data: ArrayList<UserDetailRowDataWrapper> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseViewHolder<UserDetailRowDataWrapper, BaseAdapterListener<UserDetailRowDataWrapper>> {
        return if (viewType == VIEW_TYPE_SIMPLE_ROW) UserDetailRowViewHolder(parent) else throw IllegalStateException()
    }

    fun setData(newData: List<UserDetailRowDataWrapper>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_SIMPLE_ROW: Int = 1
    }
}