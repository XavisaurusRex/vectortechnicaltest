package cat.devsofthecoast.vectortechincaltest.screen.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

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