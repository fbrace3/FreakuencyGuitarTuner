package edu.msudenver.cs3013.project01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.barteksc.pdfviewer.PDFView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResourcesChoiceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmen


        return inflater.inflate(R.layout.fragment_resouces_choice, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the tab data based on the selected tab ID
        //setResourceData(arguments?.getInt("ressourceId") ?: 0)
        setResourceData(arguments?.getInt("resourceId") ?: 0)



    }

    fun setResourceData(resourceId: Int) {
        //Some text below should in production be string resources, done as hardcoded text here for simplicity
        when (resourceId) {
            R.id.Scales -> {
                findNavController().navigate(R.id.nav_resources_scales)
            }

            R.id.Chords -> {
                findNavController().navigate(R.id.nav_resources_chords)
            }

            R.id.arpeggios -> {
               findNavController().navigate(R.id.nav_resources_arpeggios)

            }
            R.id.modes -> {
                findNavController().navigate(R.id.nav_resources_modes)
            }

//                else -> {
//                    Toast.makeText(context, getString(R.string.unknown_tab), Toast.LENGTH_LONG)
//                        .show()
//                }
        }
    }
}