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
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView

//    var passedUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inside MainActivity's onCreate() or wherever
        // you navigate to ResourcesFragment


        var passedUser = intent.getSerializableExtra("myUser") as User?
        Log.d("Main Username", passedUser?.username.toString())
        //Debug logs
//        Log.d("username", myUser.username)
//        Log.d("password", myUser.password)

        val bundle = createBundle(passedUser) // key is "user" for this createBundle() method
        bundle.putSerializable("myUser", passedUser)

        val menuFragment = MenuFragment().apply {
            arguments = bundle

        }

        setSupportActionBar(findViewById(R.id.toolbar))
        drawerLayout = findViewById(R.id.drawer_layout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController // Obtain the navController instance


        // Check if there is an incoming intent
        val fromFragment = intent.getStringExtra("FROM_TABS")

        if (passedUser == null) {
            // The intent came from TabsListFragment
            passedUser = User()
            passedUser?.username = userData.username
            passedUser?.password = userData.password
            passedUser?.emailAddress = userData.emailAddress
            passedUser?.firstName = userData.firstName
            passedUser?.lastName = userData.lastName
            passedUser?.age = userData.age
            Toast.makeText(this, "ListFragment", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.nav_menu, createBundle(passedUser))
        } else {
            // The intent came from a different fragment
            Toast.makeText(this, "NotTabListFragment", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.nav_menu, createBundle(passedUser))
        }


        //navController.setGraph(navController.graph, bundle) // Set the bundle to the navController's graph



        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_menu, R.id.nav_chromatic_tuner, R.id.nav_metronome, R.id.tab_activity, R.id.nav_standard_tuner, R.id.nav_resources, R.id.nav_shopping, R.id.nav_synth
            ),
            drawerLayout
        )

        val settingFragment = navController.graph.findNode(R.id.nav_settings)
        settingFragment?.addArgument("myUser", NavArgument.Builder().setDefaultValue(passedUser!!).build())

        val intent = Intent(this, TabsActivity::class.java)
        intent.putExtra("myUser", passedUser!!)


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

    private fun createBundle(user: User?): Bundle {
        val bundle = Bundle()

        // Put the user name into the bundle with a predefined key
        bundle.putSerializable("myUser", user)

        // Log the user name value for debugging purposes
        //Log.d(TAG, "SendingMainActivity USER_NAME_KEY value: $USER_NAME_KEY")
        //Log.d(TAG, "SendingMainActivity Received userName: $userName")

        //TODO: Already stored in the global variable: passedUser
        // Store the user name in the global userData object for access throughout the app
//        if (user != null) {
//            passedUser? ?: userName
//        }

        return bundle
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

        // Log the clicked toolbar item ID for debugging purposes
        Log.d(TAG, "Toolbar item clicked: ${item.itemId}")

        // Let the Navigation component handle
        // the selected item as a navigation action
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }

    // TODO: Appears to be unused
//    companion object {
//        const val USER_NAME_KEY = "username"
//    }
    companion object {
        val USER_NAME_KEY: String? = null
    }
}
