package edu.msudenver.cs3013.project01;
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var userName = intent.getStringExtra(USER_NAME_KEY)
        val bundle = Bundle()
        bundle.putString(USER_NAME_KEY, userName)

        if (userName != null) {
            userData.username = userName
        }

        setSupportActionBar(findViewById(R.id.toolbar))
        drawerLayout = findViewById(R.id.drawer_layout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController // Obtain the navController instance


        // Check if there is an incoming intent
        val fromFragment = intent.getStringExtra("FROM_TABS")

        if (fromFragment == "TabsListFragment") {
            // The intent came from TabsListFragment
            Toast.makeText(this, "ListFragment", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.nav_menu, bundle)
        } else {
            // The intent came from a different fragment
            Toast.makeText(this, "NotTabListFragment", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.nav_menu, bundle)
        }


        //navController.setGraph(navController.graph, bundle) // Set the bundle to the navController's graph



        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_menu, R.id.nav_chromatic_tuner, R.id.nav_metronome, R.id.tab_activity, R.id.nav_standard_tuner, R.id.nav_resources, R.id.nav_shopping, R.id.nav_synth
            ),
            drawerLayout
        )

        val settingFragment = navController.graph.findNode(R.id.nav_settings)
        settingFragment?.addArgument("username", NavArgument.Builder().setDefaultValue(userName).build())


        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navController)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setupWithNavController(navController)



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
