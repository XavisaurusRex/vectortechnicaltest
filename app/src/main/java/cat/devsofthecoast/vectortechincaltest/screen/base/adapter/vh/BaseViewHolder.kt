package cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper

abstract class BaseViewHolder<DW : BaseDataWrapper>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bindViewHolder(dataWrapper: DW);
}