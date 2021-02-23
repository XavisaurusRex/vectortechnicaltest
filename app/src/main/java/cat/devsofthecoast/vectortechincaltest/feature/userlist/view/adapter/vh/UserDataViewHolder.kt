package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.R
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewUserdataBinding
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class UserDataViewHolder(parent: ViewGroup) :
    BaseViewHolder<BaseDataWrapper, GithubUsersListener>(
        ItemviewUserdataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).root
    ) {

    private val binding: ItemviewUserdataBinding by lazy {
        ItemviewUserdataBinding.bind(itemView)
    }

    override fun bindViewHolder(
        dataWrapper: BaseDataWrapper,
        listener: GithubUsersListener?,
        position: Int
    ) {
        (dataWrapper as UserDataWrapper)

        binding.tvUsername.text = dataWrapper.apiUser.username
        binding.tvUserUrl.text = dataWrapper.apiUser.html_url

        Glide.with(itemView)
            .load(dataWrapper.apiUser.avatar_url)
            .transform(CircleCrop())
            .placeholder(R.drawable.progress_animation)
            .into(binding.ivUserAvatar)

        itemView.setOnClickListener {
            listener?.onUserSelected(dataWrapper.apiUser)
        }
    }
}