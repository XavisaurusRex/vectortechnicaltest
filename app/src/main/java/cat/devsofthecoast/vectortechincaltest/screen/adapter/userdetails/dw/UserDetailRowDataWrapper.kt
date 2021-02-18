package cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.dw

import cat.devsofthecoast.vectortechincaltest.screen.adapter.userdetails.UserDetailsAdapter
import cat.devsofthecoast.vectortechincaltest.screen.base.adapter.dw.BaseDataWrapper

class UserDetailRowDataWrapper(
    val title:String,
    val subtitle: String?
): BaseDataWrapper() {
    override val viewType: Int = UserDetailsAdapter.VIEW_TYPE_SIMPLE_ROW
}