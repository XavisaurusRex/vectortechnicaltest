package cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewLoadingBinding
import cat.devsofthecoast.vectortechincaltest.screen.adapter.userlist.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.vh.BaseViewHolder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class LoadingViewHolder(parent: ViewGroup) : BaseViewHolder<BaseDataWrapper, GithubUsersListener>(
    ItemviewLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    val binding: ItemviewLoadingBinding by lazy {
        ItemviewLoadingBinding.bind(itemView);
    }

    override fun bindViewHolder(
        dataWrapper: BaseDataWrapper,
        listener: GithubUsersListener?,
        position: Int
    ) {
        listener?.loadMoreUsers(position / 20)
    }
}