import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import edu.msudenver.cs3013.project01.R
import edu.msudenver.cs3013.project01.TabsDetailsFragment

class TabsListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tabs_list, container, false)

        listView = rootView.findViewById(R.id.listView)
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            getFileListTitles()
        )

        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val fileName = getFileList()[position]
            navigateToTabsDetails(fileName)
        }

        return rootView
    }

    private fun getFileListTitles(): List<String> {
        val fileListTitles = mutableListOf<String>()
        val resources = resources
        val packageName = requireContext().packageName

        try {
            val resourceFieldNames = resources.javaClass.fields
            for (fieldName in resourceFieldNames) {
                if (fieldName.name.startsWith("raw_")) {
                    val resourceId = resources.getIdentifier(fieldName.name, "raw", packageName)
                    val fileName = fieldName.name.substring(4) // Remove "raw_" prefix
                    fileListTitles.add(fileName)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return fileListTitles
    }

    private fun getFileList(): List<String> {
        val fileList = mutableListOf<String>()
        val resources = resources
        val packageName = requireContext().packageName

        try {
            val resourceFieldNames = resources.javaClass.fields
            for (fieldName in resourceFieldNames) {
                if (fieldName.name.startsWith("raw_")) {
                    val fileName = fieldName.name.substring(4) // Remove "raw_" prefix
                    fileList.add(fileName)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return fileList
    }


    private fun navigateToTabsDetails(fileName: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val detailsFragment = TabsDetailsFragment.newInstance(fileName)
        fragmentTransaction.replace(R.id.fragmentContainer, detailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
