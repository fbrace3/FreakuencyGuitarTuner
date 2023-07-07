package edu.msudenver.cs3013.project01

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MenuFragment : Fragment() {
    private lateinit var chromaticTunerButton: Button
    private lateinit var metronomeButton: Button
    private lateinit var tabsButton: Button
    private lateinit var standardTunerButton: Button
    private lateinit var loggedInText: TextView
    private lateinit var shoppingButton: Button
    private lateinit var resourcesButton: Button
    private lateinit var synthButton: Button

    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Retrieve the username argument
        //username = arguments?.getString(USER_NAME_KEY)
        //username = "fredd"
        // Perform any necessary initialization or UI updates with the username
        username = userData.username
        val loggedInMessage = getString(R.string.constant_log_in, username)
        loggedInText = view.findViewById(R.id.logged_in_text)
        loggedInText.text = loggedInMessage
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the username argument
        //username = arguments?.getString(USER_NAME_KEY)



        standardTunerButton = view.findViewById(R.id.btnStandardTuner)
        chromaticTunerButton = view.findViewById(R.id.btnChromaticTuner)
        metronomeButton = view.findViewById(R.id.btnMetronome)
        tabsButton = view.findViewById(R.id.btnTabs)
        resourcesButton = view.findViewById(R.id.btnResources)
        shoppingButton = view.findViewById(R.id.btnShopping)
        synthButton = view.findViewById(R.id.btnSynth)

        standardTunerButton.setOnClickListener {
            findNavController().navigate(R.id.nav_standard_tuner)
        }

        chromaticTunerButton.setOnClickListener {
            findNavController().navigate(R.id.nav_chromatic_tuner)
        }

        metronomeButton.setOnClickListener {
            findNavController().navigate(R.id.nav_metronome)
        }

        tabsButton.setOnClickListener {
            val intent = Intent(requireActivity(), TabsActivity::class.java)
            intent.putExtra(MainActivity.USER_NAME_KEY, username)
            startActivity(intent)
        }
        resourcesButton.setOnClickListener {
            findNavController().navigate(R.id.nav_resources)
        }
        shoppingButton.setOnClickListener {
            findNavController().navigate(R.id.nav_shopping)
        }
        synthButton.setOnClickListener {
            findNavController().navigate(R.id.nav_synth)
        }
    }

    companion object {
        const val USER_NAME_KEY = "username"
    }
}
