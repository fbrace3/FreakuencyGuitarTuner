package edu.msudenver.cs3013.project01
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
const val TABS_ID = "TABS_ID"

interface TabsListener {
    fun onSelected(id: Int)
}
class TabsActivity : AppCompatActivity(), TabsListener {
    private var passedUser: User? = null
    var isDualPane: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)



        passedUser = intent.getSerializableExtra("myUser") as User?
        Log.d("Tabs Username", passedUser?.username.toString())
//        val userName = intent.getStringExtra(MainActivity.USER_NAME_KEY)
//
//        val bundle = Bundle()
//        bundle.putString(USER_NAME_KEY, userName)

//        val tabsListFragment = TabsListFragment()
//        tabsListFragment.arguments = bundle



        isDualPane = findViewById<View>(R.id.tabs_list_fragment) != null
//        if (isDualPane) {
//
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.tabs_resources_fragment, tabsListFragment)
//                .commit()
//
//        }
    }

    override fun onSelected(id: Int) {
        passedUser = intent.getSerializableExtra("myUser") as User?
        if (isDualPane) {
            val detailFragment = supportFragmentManager.findFragmentById(R.id.tabs_detail)
            if (detailFragment is TabsDetailsFragment) {
                detailFragment.setTabData(id)
            } else {
                val detailIntent = Intent(this, TabsDetailActivity::class.java)
                    .apply {
                        putExtra(TABS_ID, id)
                    }
                //detailIntent.putExtra("myUser", passedUser)
                startActivity(detailIntent)

            }
        } else {
            val detailIntent = Intent(this, TabsDetailActivity::class.java)
                .apply {
                    putExtra(TABS_ID, id)
                }
            //detailIntent.putExtra("myUser", passedUser)
            startActivity(detailIntent)
        }
    }
    companion object {
        const val USER_NAME_KEY = "username"
    }
}