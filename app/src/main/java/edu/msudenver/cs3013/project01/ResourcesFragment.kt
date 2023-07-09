package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class ResourcesFragment : Fragment() {
    lateinit var listener: ResourceListener // Declare the listener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resources = listOf<View>(
            view.findViewById(R.id.scales_resources),
            view.findViewById(R.id.chords_resources),
            view.findViewById(R.id.arpeggios_resources),
            view.findViewById(R.id.modes_resources)
        )
        resources.forEach { button ->
            button.setOnClickListener {
                onSelected(button.id) // Call the method directly
            }
        }
    }

    // Call this method when a resource is selected
    private fun onSelected(id: Int) {
        listener?.onSelected(id) // Call the listener, if it's not null
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
