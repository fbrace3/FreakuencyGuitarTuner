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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChromaticTunerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_chromatic_tuner, container, false)
        backButton = view.findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
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
            soundPool.play(it, 1f, 1f, 0, 0, 1f)
        }
    }


}
