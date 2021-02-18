package cat.devsofthecoast.vectortechincaltest.screen.adapter.vh

import android.view.View
import android.widget.TextView
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

class UserDataViewHolder(itemView: View) : BaseViewHolder<BaseDataWrapper>(itemView) {

     val tvMainContent: TextView by lazy {
         itemView.findViewById(R.id.tvMainContent);
    }


    override fun bindViewHolder(dataWrapper: BaseDataWrapper) {
        (dataWrapper as UserDataWrapper)
        tvMainContent.text = dataWrapper.apiUser.username
    }
}