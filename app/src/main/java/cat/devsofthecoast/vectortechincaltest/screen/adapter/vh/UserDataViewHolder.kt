package cat.devsofthecoast.vectortechincaltest.screen.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewUserdataBinding
import cat.devsofthecoast.vectortechincaltest.screen.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

class UserDataViewHolder(parent: ViewGroup) :
    BaseViewHolder<BaseDataWrapper, GithubUsersListener>(
        ItemviewUserdataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).root
    ) {

    val binding: ItemviewUserdataBinding by lazy {
        ItemviewUserdataBinding.bind(itemView)
    }

    override fun bindViewHolder(
        dataWrapper: BaseDataWrapper,
        listener: GithubUsersListener?,
        position: Int
    ) {
        (dataWrapper as UserDataWrapper)
        binding.tvMainContent.text = dataWrapper.apiUser.username
        itemView.setOnClickListener {
            listener?.onUserSelected(dataWrapper.apiUser)
        }
    }
}