package cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.listener.BaseAdapterListener

abstract class BaseViewHolder<DW : BaseDataWrapper, LT: BaseAdapterListener<DW>>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bindViewHolder(dataWrapper: DW, listener: LT?, position: Int);
}