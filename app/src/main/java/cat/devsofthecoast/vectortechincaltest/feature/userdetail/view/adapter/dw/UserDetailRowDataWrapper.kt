package cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.dw

import cat.devsofthecoast.vectortechincaltest.feature.userdetail.view.adapter.UserDetailsAdapter
import cat.devsofthecoast.vectortechincaltest.common.view.adapter.dw.BaseDataWrapper

class UserDetailRowDataWrapper(
    val title: String,
    val subtitle: String
) : BaseDataWrapper() {
    override val viewType: Int = UserDetailsAdapter.VIEW_TYPE_SIMPLE_ROW
}