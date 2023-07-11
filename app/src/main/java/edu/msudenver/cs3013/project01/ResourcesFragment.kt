package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController



class ResourcesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get references to the buttons in the layout
        val scalesButton = view.findViewById<View>(R.id.scales_resources)
        val chordsButton = view.findViewById<View>(R.id.chords_resources)
        val arpeggiosButton = view.findViewById<View>(R.id.arpeggios_resources)
        val modesButton = view.findViewById<View>(R.id.modes_resources)
        scalesButton.setOnClickListener {
            findNavController().navigate(R.id.nav_resources_scales)
        }
        chordsButton.setOnClickListener {
            findNavController().navigate(R.id.nav_resources_chords)
        }
        arpeggiosButton.setOnClickListener {
            findNavController().navigate(R.id.nav_resources_arpeggios)
        }
        modesButton.setOnClickListener {
            findNavController().navigate(R.id.nav_resources_modes)
        }
    }

    // Call this method when a resource is selected
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ResourcesFragment().apply {
//                arguments = Bundle().apply {
//                    // Set arguments if needed
//                }
//            }
//    }
}
