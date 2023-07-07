package edu.msudenver.cs3013.project01

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SynthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SynthFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var textHeader: TextView
    private lateinit var soundPool: SoundPool
    private lateinit var backButton: Button
    private var soundMap: Map<String, Int> = HashMap()

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
        val view = inflater.inflate(R.layout.fragment_synth, container, false)
       // backButton = view.findViewById(R.id.btnBack)
//        backButton.setOnClickListener {
//            requireActivity().supportFragmentManager.popBackStack()
//        }
        return view


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadSounds()
        setupButtons()
    }

    private fun loadSounds() {
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_GAME)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(audioAttributes)
            .build()

        // Add the sound files to the sound pool
        soundMap = mapOf(
            "C" to soundPool.load(requireContext(), R.raw.csynth, 1),
            "C#" to soundPool.load(requireContext(), R.raw.csharpsynth, 1),
            "D" to soundPool.load(requireContext(), R.raw.dsynth, 1),
            "D#" to soundPool.load(requireContext(), R.raw.dsharpsynth, 1),
            "E" to soundPool.load(requireContext(), R.raw.esynth, 1),
            "F" to soundPool.load(requireContext(), R.raw.fsynth, 1),
            "F#" to soundPool.load(requireContext(), R.raw.fsharpsynth, 1),
            "G" to soundPool.load(requireContext(), R.raw.gsynth, 1),
            "G#" to soundPool.load(requireContext(), R.raw.gsharpsynth, 1),
            "A" to soundPool.load(requireContext(), R.raw.asynth, 1),
            "A#" to soundPool.load(requireContext(), R.raw.asharpsynth, 1),
            "B" to soundPool.load(requireContext(), R.raw.bsynth, 1)
        )

    }


    private fun setupButtons() {
        view?.findViewById<Button>(R.id.btnC)?.setOnClickListener {
            playSound("C")
        }

        view?.findViewById<Button>(R.id.btnCSharp)?.setOnClickListener {
            playSound("C#")
        }

        view?.findViewById<Button>(R.id.btnD)?.setOnClickListener {
            playSound("D")
        }

        view?.findViewById<Button>(R.id.btnDSharp)?.setOnClickListener {
            playSound("D#")
        }

        view?.findViewById<Button>(R.id.btnE)?.setOnClickListener {
            playSound("E")
        }

        view?.findViewById<Button>(R.id.btnF)?.setOnClickListener {
            playSound("F")
        }

        view?.findViewById<Button>(R.id.btnFSharp)?.setOnClickListener {
            playSound("F#")
        }

        view?.findViewById<Button>(R.id.btnG)?.setOnClickListener {
            playSound("G")
        }

        view?.findViewById<Button>(R.id.btnGSharp)?.setOnClickListener {
            playSound("G#")
        }

        view?.findViewById<Button>(R.id.btnA)?.setOnClickListener {
            playSound("A")
        }

        view?.findViewById<Button>(R.id.btnASharp)?.setOnClickListener {
            playSound("A#")
        }

        view?.findViewById<Button>(R.id.btnB)?.setOnClickListener {
            playSound("B")
        }
        view?.findViewById<Button>(R.id.btnBack)?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun playSound(note: String) {
        val soundId = soundMap[note]
        soundId?.let {
            soundPool.play(it, 6f, 6f, 0, 0, 1f)
        }
    }


}
