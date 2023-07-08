package edu.msudenver.cs3013.project01


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val resource: TextView?
        get() = view?.findViewById(R.id.resource_name)
//    private val symbol: TextView?
//        get() = view?.findViewById(R.id.symbol)
//    private val dateRange: TextView?
//        get() = view?.findViewById(R.id.date_range)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resources_details, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resourceId = arguments?.getInt(RESOURCE_ID, 0) ?: 0
        setResourceData(resourceId)
    }

    fun setResourceData(starSignId: Int) {
        //Some text below should in production be string resources, done as hardcoded text here for simplicity
        when (starSignId) {
            R.id.MajorScale -> {
                resource?.text = getString(R.string.majorScales)
            }
            R.id.MinorScale -> {
                resource?.text = getString(R.string.minorScales)

            }
            R.id.MajorPentatonicScale -> {
                resource?.text = getString(R.string.majorPentatonic)
            }
            R.id.MinorPentatonicScale -> {
                resource?.text = getString(R.string.minorPentatonic)

            }

            else -> {
                Toast.makeText(context, getString(R.string.unknown_resource), Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val RESOURCE_ID = "RESOURCE_ID"
        fun newInstance(resourceId: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(RESOURCE_ID, resourceId)
            }
        }
    }
}