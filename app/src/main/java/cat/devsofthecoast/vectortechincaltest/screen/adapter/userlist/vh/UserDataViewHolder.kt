package cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewUserdataBinding
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.dw.UserDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

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

        binding.tvUsername.text = dataWrapper.apiUser.username
        binding.tvUserUrl.text = dataWrapper.apiUser.html_url

        Glide.with(itemView)
            .load(dataWrapper.apiUser.avatar_url)
//            .placeholder(R.drawable.ic_profile_placeholder)
//            .error(R.drawable.ic_profile_placeholder)
            .transform(CircleCrop())
            .into(binding.ivUserAvatar)

        itemView.setOnClickListener {
            listener?.onUserSelected(dataWrapper.apiUser)
        }
    }
}