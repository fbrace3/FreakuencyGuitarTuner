package edu.msudenver.cs3013.project01

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.media.MediaPlayer
import java.util.Locale

// Constants for fragment arguments
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChromaticTunerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var soundPool: SoundPool
    private lateinit var backButton: Button
    private var soundMap: Map<String, Int> = HashMap()

    // Fragment's onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Fragment's onCreateView method
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chromatic_tuner, container, false)
        backButton = view.findViewById(R.id.btnBack)

        // Set a click listener for the back button
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }

    // Fragment's onViewCreated method
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load the sound files and setup buttons
        loadSounds()
        setupButtons()
    }

    // Load the sound files into the SoundPool
    private fun loadSounds() {
        // Set audio attributes for the SoundPool
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_GAME)
            .build()

        // Create the SoundPool
        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(audioAttributes)
            .build()

        // Add the sound files to the sound map
        soundMap = mapOf(
            "C" to soundPool.load(requireContext(), R.raw.c_note, 1),
            "C#" to soundPool.load(requireContext(), R.raw.c_sharp_note, 1),
            "D" to soundPool.load(requireContext(), R.raw.d_note, 1),
            "D#" to soundPool.load(requireContext(), R.raw.d_sharp_note, 1),
            "E" to soundPool.load(requireContext(), R.raw.e_note, 1),
            "F" to soundPool.load(requireContext(), R.raw.f_note, 1),
            "F#" to soundPool.load(requireContext(), R.raw.f_sharp_note, 1),
            "G" to soundPool.load(requireContext(), R.raw.g_note, 1),
            "G#" to soundPool.load(requireContext(), R.raw.g_sharp_note, 1),
            "A" to soundPool.load(requireContext(), R.raw.a_note, 1),
            "A#" to soundPool.load(requireContext(), R.raw.a_sharp_note, 1),
            "B" to soundPool.load(requireContext(), R.raw.b_note, 1)
        )
    }

    // Setup click listeners for the note buttons
    private fun setupButtons() {
        // Set click listeners for each note button
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

        // Set a click listener for the back button
        view?.findViewById<Button>(R.id.btnBack)?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    // Play the sound corresponding to the given note
    private fun playSound(note: String) {
        val soundId = soundMap[note]
        soundId?.let {
            soundPool.play(it, 1f, 1f, 0, 0, 1f)
        }
    }
}
