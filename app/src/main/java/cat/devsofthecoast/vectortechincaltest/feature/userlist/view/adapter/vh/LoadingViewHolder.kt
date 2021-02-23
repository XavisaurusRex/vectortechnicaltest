package cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.databinding.ItemviewLoadingBinding
import cat.devsofthecoast.vectortechincaltest.feature.userlist.view.adapter.listener.GithubUsersListener
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.vh.BaseViewHolder

class LoadingViewHolder(parent: ViewGroup) : BaseViewHolder<BaseDataWrapper, GithubUsersListener>(
    ItemviewLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    override fun bindViewHolder(
        dataWrapper: BaseDataWrapper,
        listener: GithubUsersListener?,
        position: Int
    ) {
        listener?.loadMoreUsers(position / 20)
    }
}