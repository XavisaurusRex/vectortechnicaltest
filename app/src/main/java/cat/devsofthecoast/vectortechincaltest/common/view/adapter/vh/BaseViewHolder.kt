package cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.listener.BaseAdapterListener

abstract class BaseViewHolder<DW : BaseDataWrapper, LT : BaseAdapterListener<DW>>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bindViewHolder(dataWrapper: DW, listener: LT?, position: Int)

    protected fun getString(@StringRes stringId: Int): String = itemView.context.getString(stringId)

}