package cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewUserDetailRowBinding
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder

class UserDetailRowViewHolder(parent: ViewGroup) :
    BaseViewHolder<UserDetailRowDataWrapper, BaseAdapterListener<UserDetailRowDataWrapper>>(
        ItemviewUserDetailRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    ) {

    val binding: ItemviewUserDetailRowBinding by lazy {
        ItemviewUserDetailRowBinding.bind(itemView)
    }

    override fun bindViewHolder(
        dataWrapper: UserDetailRowDataWrapper,
        listener: BaseAdapterListener<UserDetailRowDataWrapper>?,
        position: Int
    ) {
        binding.tvTitle.text = dataWrapper.title
        binding.tvSubtitle.text = dataWrapper.subtitle

    }
}