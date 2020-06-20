package com.example.mviexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mviexample.databinding.ActivityMainBinding
import com.example.mviexample.model.PostUi
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var dataBinding: ActivityMainBinding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)
        setContentView(dataBinding.root)

        setSupportActionBar(dataBinding.toolbar)
        navController = Navigation.findNavController(this, R.id.navigation)
        navController.addOnDestinationChangedListener(fragmentNavigationListener)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private val fragmentNavigationListener =
        NavController.OnDestinationChangedListener { _, destination, arguments ->
            val menuItem = menu?.findItem(R.id.action_settings)
            dataBinding.appBar.setExpanded(true)
            when (destination.id) {
                R.id.detailFragment -> {
                    menuItem?.isVisible = false
                    dataBinding.backdrop.visibility = VISIBLE
                    val post: PostUi = arguments?.get("post") as PostUi
                    dataBinding.imageUrl = post.imageUrl
                    dataBinding.toolbarLayout.title = post.title
                    dataBinding.fab.setImageResource(R.drawable.ic_discuss)
                }
                R.id.homeFragment -> {
                    menuItem?.isVisible = true
                    dataBinding.backdrop.visibility = GONE
                    dataBinding.toolbarLayout.title = getString(R.string.posts)
                    dataBinding.fab.setImageResource(android.R.drawable.ic_dialog_email)
                }
            }
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