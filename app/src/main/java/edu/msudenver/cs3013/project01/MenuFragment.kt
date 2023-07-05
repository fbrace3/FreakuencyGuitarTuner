package edu.msudenver.cs3013.project01
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("username")
        val loggedInMessage = getString(R.string.constant_log_in, username)
        loggedInText = view.findViewById(R.id.logged_in_text)
        loggedInText.text = loggedInMessage

        standardTunerButton = view.findViewById(R.id.btnStandardTuner)
        chromaticTunerButton = view.findViewById(R.id.btnChromaticTuner)
        metronomeButton = view.findViewById(R.id.btnMetronome)
        tabsButton = view.findViewById(R.id.btnTabs)

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
            findNavController().navigate(R.id.nav_home_to_tabs)
        }
    }
    companion object {
        const val USER_NAME_KEY = "username"
    }

}

