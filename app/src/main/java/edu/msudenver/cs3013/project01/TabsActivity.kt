package edu.msudenver.cs3013.project01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

const val TABS_ID = "TABS_ID"

// Define an interface for communicating tab selection
interface TabsListener {
    fun onSelected(id: Int)
}

class TabsActivity : AppCompatActivity(), TabsListener {
    private var passedUser: User? = null
    var isDualPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        // Retrieve the user data passed from the previous activity
        passedUser = intent.getSerializableExtra("myUser") as User?
        Log.d("Tabs Username", passedUser?.username.toString())

        // Check if the layout contains both tabs list and detail fragments
        isDualPane = findViewById<View>(R.id.tabs_list_fragment) != null
    }

    override fun onSelected(id: Int) {
        passedUser = intent.getSerializableExtra("myUser") as User?

        if (isDualPane) {
            // Dual-pane layout, display tab details fragment
            val detailFragment = supportFragmentManager.findFragmentById(R.id.tabs_detail)

            if (detailFragment is TabsDetailsFragment) {
                // Update the existing detail fragment with new tab data
                detailFragment.setTabData(id)
            } else {
                // Launch a new activity for displaying tab details
                val detailIntent = Intent(this, TabsDetailActivity::class.java).apply {
                    putExtra(TABS_ID, id)
                }
                detailIntent.putExtra("myUser", passedUser)
                startActivity(detailIntent)
            }
        } else {
            // Single-pane layout, launch activity for displaying tab details
            val detailIntent = Intent(this, TabsDetailActivity::class.java).apply {
                putExtra(TABS_ID, id)
            }
            detailIntent.putExtra("myUser", passedUser)
            startActivity(detailIntent)
        }
    }

    companion object {
        const val USER_NAME_KEY = "username"
    }
}
