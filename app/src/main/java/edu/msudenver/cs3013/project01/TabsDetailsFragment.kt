package edu.msudenver.cs3013.project01
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class TabsDetailsFragment : Fragment() {

    companion object {
        private const val ARG_FILE_NAME = "file_name"

        fun newInstance(fileName: String): TabsDetailsFragment {
            val fragment = TabsDetailsFragment()
            val args = Bundle()
            args.putString(ARG_FILE_NAME, fileName)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var fileName: String
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileName = requireArguments().getString(ARG_FILE_NAME, "") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tabs_details, container, false)

        textView = rootView.findViewById(R.id.textViewFileName)
        textView.text = fileName

        return rootView
    }
}
