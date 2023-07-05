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
class TabsDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private val tabName: TextView?
        get() = view?.findViewById(R.id.tab_name)
    private val pdfView: PDFView?
        get() = view?.findViewById(R.id.pdf_viewer)

    private val closeButton: View?
        get() = view?.findViewById(R.id.btnClose)


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
        return inflater.inflate(R.layout.fragment_tabs_details, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the tab data based on the selected tab ID
        setTabData(arguments?.getInt("tabId") ?: 0)


        closeButton?.setOnClickListener {
            val intent = Intent(requireContext(), TabsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


    }

        fun setTabData(tabId: Int) {
            //Some text below should in production be string resources, done as hardcoded text here for simplicity
            when (tabId) {
                R.id.RockyTop -> {
                    tabName?.text = getString(R.string.tab1)
                    val pdfFileName = "rockytop.pdf"
                    pdfView?.fromAsset(pdfFileName)
                        ?.defaultPage(0)
                        ?.onError { throwable ->
                            Toast.makeText(
                                context,
                                "Error loading PDF: ${throwable.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        ?.load()

                }

                R.id.SweetHomeAlabama -> {
                    tabName?.text = getString(R.string.tab2)
                val pdfFileName = "sweethomealabama.pdf"
                    pdfView?.fromAsset(pdfFileName)
                        ?.defaultPage(0)
                        ?.onError { throwable ->
                            Toast.makeText(
                                context,
                                "Error loading PDF: ${throwable.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        ?.load()
                }

                R.id.WagonWheel -> {
                    tabName?.text = getString(R.string.tab3)
                    val pdfFileName = "wagonwheel.pdf"
                    pdfView?.fromAsset(pdfFileName)
                        ?.defaultPage(0)
                        ?.onError { throwable ->
                            Toast.makeText(
                                context,
                                "Error loading PDF: ${throwable.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        ?.load()

                }

                R.id.SaltCreek -> {
                    tabName?.text = getString(R.string.tab4)
                    val pdfFileName = "saltcreek.pdf"
                    pdfView?.fromAsset(pdfFileName)
                        ?.defaultPage(0)
                        ?.onError { throwable ->
                            Toast.makeText(
                                context,
                                "Error loading PDF: ${throwable.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        ?.load()
                }

//                else -> {
//                    Toast.makeText(context, getString(R.string.unknown_tab), Toast.LENGTH_LONG)
//                        .show()
//                }
            }
        }
    }