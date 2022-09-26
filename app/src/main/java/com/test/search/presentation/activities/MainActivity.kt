package com.test.search.presentation.activities

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.color.MaterialColors
import com.test.search.R
import com.test.search.databinding.ActivityMainBinding
import com.test.search.presentation.fragments.ResultsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupNavigationListener()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavigationListener() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            setupSearchViewVisibility(destination)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        setupSearchView(menu)
        return true
    }

    private fun setupSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.apply {
            queryHint = getString(R.string.search_view_query_hint)
            setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    search(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.currentDestination?.let {
            setupSearchViewVisibility(it)
        }
    }


    private fun setupSearchViewVisibility(destination: NavDestination) {
        if (destination.id == R.id.DetailFragment) {
            binding.toolbar.collapseActionView()
            setSearchViewVisibility(false)
        } else {
            setSearchViewVisibility(true)
        }
    }

    private fun setSearchViewVisibility(b: Boolean) {
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        searchItem?.isVisible = b
    }


    private fun search(query: String?) {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val resultsFragment = supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.primaryNavigationFragment as? ResultsFragment?
        resultsFragment?.let {
            resultsFragment.search(query)
        } ?: run {
            navController.navigate(R.id.ResultsFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}