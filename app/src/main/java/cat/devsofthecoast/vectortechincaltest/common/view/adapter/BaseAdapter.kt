package cat.devsofthecoast.vectortechincaltest.common.view.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder

abstract class BaseAdapter<DW, VH, LT> : RecyclerView.Adapter<VH>()
        where DW : BaseDataWrapper, VH : BaseViewHolder<DW, LT>, LT: BaseAdapterListener<DW> {

    protected abstract val data: List<DW>
    var listener: LT? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindViewHolder(data[position], listener, position)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].viewType
}