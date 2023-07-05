package edu.msudenver.cs3013.project01;
import android.os.Bundle;
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.*;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = intent
        //val userName = intent.getStringExtra(USER_NAME_KEY)
        val  userName = "fredo"
        val bundle = Bundle()
        bundle.putString(USER_NAME_KEY, userName)



        setSupportActionBar(findViewById(R.id.toolbar))
        drawerLayout = findViewById(R.id.drawer_layout)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navHostFragment.navController.setGraph(navHostFragment.navController.graph, bundle)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_recent, R.id.nav_favorites,
                R.id.nav_archive, R.id.nav_bin, R.id.nav_menu, R.id.nav_chromatic_tuner, R.id.nav_metronome, R.id.tab_activity, R.id.nav_standard_tuner
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
                R.id.nav_archive -> navController.navigate(R.id.nav_archive)
                R.id.nav_bin -> navController.navigate(R.id.nav_bin)
                R.id.tab_activity -> navController.navigate(R.id.tab_activity)
                // Add more cases for other menu items
            }

            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment);
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp();
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }

    companion object {
        const val USER_NAME_KEY = "username"
    }
}
