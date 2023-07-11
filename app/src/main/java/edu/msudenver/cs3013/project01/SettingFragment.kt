package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SettingFragment : Fragment() {
    private lateinit var settingText: TextView
    private lateinit var preference1: TextView
    private lateinit var preference2: TextView
    private lateinit var preference3: TextView
    private lateinit var preference4: TextView
    private lateinit var preference5: TextView
    private lateinit var preference6: TextView
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Retrieve arguments if needed
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // Retrieve the user object from the arguments
        user = arguments?.getSerializable("myUser") as User?
        val username = user?.username
        val settingMessage = getString(R.string.settings_fragment, username)

        // Set the settingText TextView with the setting message
        settingText = view.findViewById(R.id.setting_text)
        settingText.text = settingMessage

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize and set the preference TextViews with user information
        preference1 = view.findViewById(R.id.preference_header)
        preference2 = view.findViewById(R.id.preference_header2)
        preference3 = view.findViewById(R.id.preference_header3)
        preference4 = view.findViewById(R.id.preference_header4)
        preference5 = view.findViewById(R.id.preference_header5)
        preference6 = view.findViewById(R.id.preference_header6)

        preference3.text = "Username: " + user?.username.toString()
        preference1.text = "First Name: " + user?.firstName.toString()
        preference2.text = "Last Name: " + user?.lastName.toString()
        preference4.text = "Age: " + user?.age.toString()
        preference5.text = "Favorite Instrument: " + user?.favoriteInstrument.toString()
        preference6.text = "Email Address: " + user?.emailAddress.toString()
    }

    companion object {
        // Unused function, can be removed if not needed
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    // Set arguments if needed
                }
            }
    }
}
