package edu.msudenver.cs3013.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val MENU_ITEM = "MENU_ITEM"

interface MenuListener {
    fun onSelected(menuId: Int)
}
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}