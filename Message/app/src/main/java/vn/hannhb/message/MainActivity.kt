package vn.hannhb.message
import vn.hannhb.message.core.ui.navigation.NavigationActivity

class MainActivity : NavigationActivity() {
    override fun getNavGraphId(): Int {
        return R.navigation.message_nav_graph
    }
}
