package edu.msudenver.cs3013.project01
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
const val TABS_ID = "TABS_ID"
interface TabsListener {
    fun onSelected(id: Int)
}
class TabsActivity : AppCompatActivity(), TabsListener {
    var isDualPane: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        isDualPane = findViewById<View>(R.id.tabs_list) != null
    }

    override fun onSelected(id: Int) {
        if (isDualPane) {
            val detailFragment = supportFragmentManager.findFragmentById(R.id.tabs_detail)
            if (detailFragment is TabsDetailsFragment) {
                detailFragment.setTabData(id)
            } else {
                val detailIntent = Intent(this, TabsDetailActivity::class.java)
                    .apply {
                        putExtra(TABS_ID, id)
                    }
                startActivity(detailIntent)

            }
        } else {
            val detailIntent = Intent(this, TabsDetailActivity::class.java)
                .apply {
                    putExtra(TABS_ID, id)
                }
            startActivity(detailIntent)
        }
    }

}