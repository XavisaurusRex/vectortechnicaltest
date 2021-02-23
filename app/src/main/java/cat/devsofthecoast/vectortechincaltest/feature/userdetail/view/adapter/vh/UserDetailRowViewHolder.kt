package cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewUserDetailRowBinding
import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.dw.UserDetailRowDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder

class UserDetailRowViewHolder(parent: ViewGroup) :
    BaseViewHolder<UserDetailRowDataWrapper, BaseAdapterListener<UserDetailRowDataWrapper>>(
        ItemviewUserDetailRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    ) {

    private val binding: ItemviewUserDetailRowBinding by lazy {
        ItemviewUserDetailRowBinding.bind(itemView)
    }

    override fun bindViewHolder(
        dataWrapper: UserDetailRowDataWrapper,
        listener: BaseAdapterListener<UserDetailRowDataWrapper>?,
        position: Int
    ) {
        binding.tvTitle.text = dataWrapper.title
        if (dataWrapper.subtitle.isEmpty()) {
            binding.tvSubtitle.text =
                getString(R.string.itemview_userdetails_row_default_value_empty)
        } else {
            binding.tvSubtitle.text = dataWrapper.subtitle
        }

    }
}