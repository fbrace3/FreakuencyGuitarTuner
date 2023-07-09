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

        setSupportActionBar(findViewById(R.id.toolbar))
        drawerLayout = findViewById(R.id.drawer_layout)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Check if the intent contains information from a specific fragment
        val fromFragment = intent.getStringExtra("FROM_TABS")
        val userName = intent.getStringExtra(USER_NAME_KEY)

        // Navigate to the appropriate destination based on the source fragment
        if (fromFragment == "TabsListFragment") {
            navController.navigate(R.id.nav_menu, createBundle(userName))
        } else {
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

        // Log the clicked toolbar item ID for debugging purposes
        Log.d(TAG, "Toolbar item clicked: ${item.itemId}")

        // Let the Navigation component handle the selected item as a navigation action
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }

    private fun createBundle(userName: String?): Bundle {
        val bundle = Bundle()

        // Put the user name into the bundle with a predefined key
        bundle.putString(USER_NAME_KEY, userName)

        // Log the user name value for debugging purposes
        Log.d(TAG, "SendingMainActivity USER_NAME_KEY value: $USER_NAME_KEY")
        Log.d(TAG, "SendingMainActivity Received userName: $userName")

        // Store the user name in the global userData object for access throughout the app
        if (userName != null) {
            userData.username = userName
        }

        return bundle
    }
}
