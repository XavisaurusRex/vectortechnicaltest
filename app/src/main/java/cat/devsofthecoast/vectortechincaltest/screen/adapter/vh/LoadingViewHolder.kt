package cat.devsofthecoast.vectortechincaltest.screen.adapter.vh

import android.view.View
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.LoadingDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

class LoadingViewHolder(itemView: View) : BaseViewHolder<BaseDataWrapper>(itemView) {

    override fun bindViewHolder(dataWrapper: BaseDataWrapper) {
        (dataWrapper as LoadingDataWrapper)
    }
}