package com.example.mviexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    var menu: Menu? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.navigation)
        navController.addOnDestinationChangedListener(fragmentNavigationListener)
        NavigationUI.setupActionBarWithNavController(this, navController)
        supportActionBar?.title = ""
    }

    private val fragmentNavigationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val menuItem = menu?.findItem(R.id.action_settings)
            when (destination.id) {
                R.id.detailFragment -> {
                    menuItem?.isVisible = false
                }
                R.id.homeFragment -> {
                    menuItem?.isVisible = true
                }
            }
        }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}