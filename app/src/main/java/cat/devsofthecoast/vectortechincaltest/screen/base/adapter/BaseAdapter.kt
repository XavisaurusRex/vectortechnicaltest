package cat.devsofthecoast.vectortechincaltest.screen.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

abstract class BaseAdapter<DW, VH> : RecyclerView.Adapter<VH>()
        where DW : BaseDataWrapper, VH : BaseViewHolder<DW> {

    protected abstract val data: List<DW>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindViewHolder(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].viewType
}