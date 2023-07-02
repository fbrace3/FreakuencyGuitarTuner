package edu.msudenver.cs3013.project01


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TabsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs_detail)
        val tabsId = intent.extras?.getInt(TABS_ID, 0) ?: 0
        val detailFragment = supportFragmentManager .findFragmentById(R.id.tabs_detail) as TabsDetailsFragment
        detailFragment.setTabData(tabsId)
    }

}