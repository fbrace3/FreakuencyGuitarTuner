package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import edu.msudenver.cs3013.project01.ResourceListener

interface ResourceListener {
    fun onSelected(id: Int)
}

class ResourcesFragment : Fragment(), ResourceListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get references to the buttons in the layout
        val scalesButton = view.findViewById<Button>(R.id.scales_resources)
        val chordsButton = view.findViewById<Button>(R.id.chords_resources)
        val arpeggiosButton = view.findViewById<Button>(R.id.arpeggios_resources)
        val modesButton = view.findViewById<Button>(R.id.modes_resources)

        // Set click listeners for the buttons
        scalesButton.setOnClickListener {
            onSelected(scalesButton.id) // Call the method directly
        }
        chordsButton.setOnClickListener {
            onSelected(chordsButton.id) // Call the method directly
        }
        arpeggiosButton.setOnClickListener {
            onSelected(arpeggiosButton.id) // Call the method directly
        }
        modesButton.setOnClickListener {
            onSelected(modesButton.id) // Call the method directly
        }
    }

    // Call this method when a resource is selected
    override fun onSelected(id: Int) {
        val navController = findNavController()
        when (id) {
            R.id.scales_resources -> navController.navigate(R.id.nav_resources_scales)
            R.id.chords_resources -> navController.navigate(R.id.nav_resources_chords)
            R.id.arpeggios_resources -> navController.navigate(R.id.nav_resources_arpeggios)
            R.id.modes_resources -> navController.navigate(R.id.nav_resources_modes)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResourcesFragment().apply {
                arguments = Bundle().apply {
                    // Set arguments if needed
                }
            }
    }
}
