package vn.hannhb.message.core.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import vn.hannhb.message.R
import vn.hannhb.message.core.helpers.EventObserver
import vn.hannhb.message.core.ui.BaseViewModel

abstract class NavigationFragment<M : BaseViewModel> : Fragment() {
    private var isArgumentsLoaded = false

    abstract val viewModel: M

    open fun loadArguments() {}

    open fun initViewModelObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, EventObserver(this::onErrorMessageEvent))
    }

    open fun onErrorMessageEvent(message: String) {}

    protected fun exists(): Boolean {
        return activity != null && this.isAdded
    }

    protected fun setSupportActionBar(actionBar: Toolbar?, navigationIcon: Int? = R.drawable.ic_toolbar) {
        val activity = activity as? NavigationActivity ?: return
        activity.setSupportActionBar(actionBar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        val unwrappedNavigationIcon = navigationIcon ?: return
        context?.let {
            actionBar?.navigationIcon = ContextCompat.getDrawable(it, unwrappedNavigationIcon)
        }
    }

    protected fun navigate(action: NavDirections, navOptions: NavOptions? = null) {
        val options = navOptions ?: NavOptions.Builder().setEnterAnim(R.anim.slide_in_from_right)
            .setExitAnim(R.anim.slide_out_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left).setPopExitAnim(R.anim.slide_out_to_right)
            .build()
        findNavController().navigate(action, options)
    }

    protected fun dismissAll() {
        activity?.finish()
    }

    protected fun dismiss() {
        if (!findNavController().navigateUp()) {
            activity?.finish()
            return
        }
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isArgumentsLoaded) {
            loadArguments()
            isArgumentsLoaded = true
        }

        initViewModelObservers()
    }
}
