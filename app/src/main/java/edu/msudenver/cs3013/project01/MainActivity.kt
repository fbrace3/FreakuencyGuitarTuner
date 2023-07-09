package edu.msudenver.cs3013.project01

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

interface ResourceListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    companion object {
        const val USER_NAME_KEY = "username"
        const val TAG = "toolbar"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inside MainActivity's onCreate() or wherever you navigate to ResourcesFragment



        setSupportActionBar(findViewById(R.id.toolbar))
        drawerLayout = findViewById(R.id.drawer_layout)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController // Obtain the navController instance

        // Check if there is an incoming intent
        val fromFragment = intent.getStringExtra("FROM_TABS")
        val userName = intent.getStringExtra(USER_NAME_KEY)

        if (fromFragment == "TabsListFragment") {
            // The intent came from TabsListFragment
            navController.navigate(R.id.nav_menu, createBundle(userName))
        } else {
            // The intent came from a different fragment
            navController.navigate(R.id.nav_menu, createBundle(userName))
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_menu, R.id.nav_chromatic_tuner, R.id.nav_metronome,
                R.id.tab_activity, R.id.nav_standard_tuner, R.id.nav_shopping,
                R.id.nav_synth
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setupWithNavController(navController)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            // Navigate to the selected destination
            when (menuItem.itemId) {
                R.id.nav_menu -> navController.navigate(R.id.nav_menu)
                R.id.nav_standard_tuner -> navController.navigate(R.id.nav_standard_tuner)
                R.id.nav_chromatic_tuner -> navController.navigate(R.id.nav_chromatic_tuner)
                R.id.nav_metronome -> navController.navigate(R.id.nav_metronome)
                R.id.tab_activity -> navController.navigate(R.id.tab_activity)
                R.id.nav_shopping -> navController.navigate(R.id.nav_shopping)
                R.id.nav_resources -> navController.navigate(R.id.nav_resources)
                R.id.nav_synth -> navController.navigate(R.id.nav_synth)
                // Add more cases for other menu items
            }

            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        Log.d(TAG, "Toolbar item clicked: ${item.itemId}")
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }

    private fun createBundle(userName: String?): Bundle {
        val bundle = Bundle()
        bundle.putString(USER_NAME_KEY, userName)
        Log.d(TAG, "SendingMainActivity USER_NAME_KEY value: $USER_NAME_KEY")
        Log.d(TAG, "SendingMainActivity Received userName: $userName")
        if (userName != null) {
            userData.username = userName
        }
        return bundle
    }
    // Override the onSelected method from the ResourceListener interface
//    override fun onSelected(id: Int) {
//        val navController = findNavController(R.id.nav_host_fragment)
//        when (id) {
//            R.id.scales_resources -> {
//                navController.navigate(R.id.nav_resources_scales)
//            }
//            R.id.chords_resources -> {
//                navController.navigate(R.id.nav_resources_chords)
//            }
//            R.id.arpeggios_resources -> {
//                navController.navigate(R.id.nav_resources_arpeggios)
//            }
//            R.id.modes_resources -> {
//                navController.navigate(R.id.nav_resources_modes)
//            }
//            // Handle other resource IDs here
//        }
//    }
}
