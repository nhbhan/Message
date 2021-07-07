package vn.hannhb.message.core.ui.navigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.core_activity.*
import vn.hannhb.message.R

abstract class NavigationActivity : AppCompatActivity() {

    abstract fun getNavGraphId(): Int

    open fun startDestinationId(): Int? = null

    open fun startDestinationArgs(): Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.core_activity)
        setupNavGraph()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavGraph() {
        val navHostFragment = nav_host_fragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(getNavGraphId())

        startDestinationId()?.also { navGraph.startDestination = it }
        navHostFragment.navController.setGraph(navGraph, startDestinationArgs())
    }
}
