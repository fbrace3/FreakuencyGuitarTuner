package edu.msudenver.cs3013.project01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import android.util.Log



class MenuFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var chromaticTunerButton: Button
    private lateinit var metronomeButton: Button
    private lateinit var tabsButton: Button

    private lateinit var loggedInText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(USER_NAME_KEY)
        //val username = "fredo"

        val loggedInMessage = getString(R.string.constant_log_in, username)
        loggedInText = view.findViewById(R.id.logged_in_text)
        loggedInText.text = loggedInMessage

        // Initialize the navigation controller
        navController = Navigation.findNavController(view)

        // Initialize the buttons
        chromaticTunerButton = view.findViewById(R.id.btnChromaticTuner)
        metronomeButton = view.findViewById(R.id.btnMetronome)
        tabsButton = view.findViewById(R.id.btnTabs)

        // Set click listeners for the buttons
        chromaticTunerButton.setOnClickListener {
            navController.navigate(R.id.nav_chromatic_tuner)
        }

        metronomeButton.setOnClickListener {
            navController.navigate(R.id.nav_metronome)
        }

        tabsButton.setOnClickListener {
            val intent = Intent(activity, TabsActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        const val USER_NAME_KEY = "username"
    }
}

