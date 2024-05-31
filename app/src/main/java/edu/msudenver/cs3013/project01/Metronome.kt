package edu.msudenver.cs3013.project01
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class MetronomeFragment : Fragment() {

    private lateinit var bpmEditText: EditText
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var backButton: Button
    private lateinit var light1: View
    private lateinit var light2: View
    private lateinit var mediaPlayer: MediaPlayer

    private var isPlaying = false
    private var bpm: Int = 0
    private var clickerHandler: Handler = Handler(Looper.getMainLooper())
    private var lightState = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_metronome, container, false)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.metronome)
        bpmEditText = rootView.findViewById(R.id.editTextBpm)
        startButton = rootView.findViewById(R.id.startButton)
        stopButton = rootView.findViewById(R.id.stopButton)
        backButton = rootView.findViewById(R.id.backButton)
        light1 = rootView.findViewById(R.id.light1)
        light2 = rootView.findViewById(R.id.light2)

        startButton.setOnClickListener {
            startClicker()
        }

        stopButton.setOnClickListener {
            stopClicker()
        }

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            //navigate to menu fragment using jetpack navigation

        }

        return rootView
    }

    private fun startClicker() {
        if (isPlaying) return

        val bpmStr = bpmEditText.text.toString()
        if (bpmStr.isNotEmpty()) {
            bpm = bpmStr.toInt()
            if (bpm > 0) {
                isPlaying = true
                clickerHandler.postDelayed(clickerRunnable, 60000 / bpm.toLong())
            }
        }
    }

    private fun stopClicker() {
        isPlaying = false
        clickerHandler.removeCallbacks(clickerRunnable)
        resetLights()
    }

    private fun resetLights() {
        light1.setBackgroundColor(Color.RED)
        light2.setBackgroundColor(Color.BLUE)
        lightState = false
    }

    private val clickerRunnable = object : Runnable {
        override fun run() {
            toggleLights()
            if (isPlaying) {
                clickerHandler.postDelayed(this, 60000 / bpm.toLong())
            } else {
                resetLights()
            }
        }
    }

    private fun toggleLights() {
        if (lightState) {
            light1.setBackgroundColor(Color.RED)
            light2.setBackgroundColor(Color.BLUE)
            mediaPlayer.start()
        } else {
            light1.setBackgroundColor(Color.BLUE)
            light2.setBackgroundColor(Color.RED)
            mediaPlayer.start()
        }
        lightState = !lightState
    }
}
