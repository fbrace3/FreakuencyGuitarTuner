package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

//// TODO: appears to be unused
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [SettingFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class SettingFragment : Fragment() {
    // TODO: appears to be unused
//    private var param1: String? = null
//    private var param2: String? = null
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
            //// TODO: appears to be unused
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        user = arguments?.getSerializable("myUser") as User?
        val username = user?.username
        val settingMessage = getString(R.string.settings_fragment, username)
        settingText = view.findViewById(R.id.setting_text)
        settingText.text = settingMessage

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    //// TODO: appears to be unused
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}