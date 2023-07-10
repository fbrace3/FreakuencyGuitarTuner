package edu.msudenver.cs3013.project01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import edu.msudenver.cs3013.project01.userData.username

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TabsListFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var user: User? = null

    private lateinit var tabsListener: TabsListener
   // private var username: String? = null


    private val mainMenuButton: View?
        get() = view?.findViewById(R.id.btnMainMenu)
    private lateinit var loggedInText: TextView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TabsListener) {
            tabsListener = context
        } else {
            throw RuntimeException("Must implement TabsListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = arguments?.getSerializable("myUser") as User?
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tabs_list, container, false)

        //username = arguments?.getString(USER_NAME_KEY)
        //username = "jimmy"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState:Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = arguments?.getSerializable("myUser") as User?

        var username = user?.username
//        val bundle = Bundle()
//        bundle.putString(MainActivity.USER_NAME_KEY, userData.username)

        val loggedInMessage = getString(R.string.constant_log_in, username)
        loggedInText = view.findViewById(R.id.logged_in_text)
        loggedInText.text = loggedInMessage



        mainMenuButton?.setOnClickListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra(MainActivity.USER_NAME_KEY, username)
                //intent.putExtra(MainActivity.USER_NAME_KEY, username.toString())
                intent.putExtra("FROM_TABS", "TabsListFragment")
                startActivity(intent)


        }

        val tabs = listOf<View>(
            view.findViewById(R.id.RockyTop),
            view.findViewById(R.id.SweetHomeAlabama),
            view.findViewById(R.id.WagonWheel),
            view.findViewById(R.id.SaltCreek),

        )
        tabs.forEach {
            it.setOnClickListener(this)
        }
    }
    override fun onClick(v: View?) {
        v?.let { tabs ->
            tabsListener.onSelected(tabs.id)
        }
    }

    companion object {
        const val USER_NAME_KEY = "username"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}