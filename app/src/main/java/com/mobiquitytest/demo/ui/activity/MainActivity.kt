package com.mobiquitytest.demo.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.mobiquitytest.demo.R
import com.mobiquitytest.demo.ui.fragment.HelpFragment
import com.mobiquitytest.demo.ui.fragment.HomeFragment
import com.mobiquitytest.demo.ui.fragment.MapFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var toggle: ActionBarDrawerToggle

    //private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        loadFragment(HomeFragment());
        nav_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.nav_home ->
                    loadFragment(HomeFragment())
                R.id.nav_help -> {
                    replaceFragment(HelpFragment())
                    hideFabAndEnableBackButton()
                }
                R.id.nav_setting -> {
                    //   replaceFragment(CityDetailFragment())
                }
            }
            return@setNavigationItemSelectedListener true
        }

        fab.setOnClickListener {
            replaceFragment(MapFragment())
            hideFabAndEnableBackButton()
        }
    }

    fun hideFabAndEnableBackButton() {
        fab.visibility = View.GONE
        toggle.isDrawerIndicatorEnabled = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.setToolbarNavigationClickListener {
            onBackPressed()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        } else if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    fun loadFragment(homeFragment: Fragment) {
        homeFragment.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.contain_main, it)
                .addToBackStack("Home")
                .commit()
        }
    }

    fun replaceFragment(fragment: Fragment) {
        fragment.let {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.contain_main, it)
                .addToBackStack("Home")
                .commit()
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.contain_main) is HomeFragment) {
            finish()
        } else {
            super.onBackPressed()
            fab.visibility = View.VISIBLE
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            toggle.isDrawerIndicatorEnabled = true
            toggle.toolbarNavigationClickListener = null
        }
    }
}
