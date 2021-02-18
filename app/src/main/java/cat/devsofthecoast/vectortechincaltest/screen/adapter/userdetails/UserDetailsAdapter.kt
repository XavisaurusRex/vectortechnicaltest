package cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails

import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.vh.UserDetailRowViewHolder
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.BaseAdapter
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

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