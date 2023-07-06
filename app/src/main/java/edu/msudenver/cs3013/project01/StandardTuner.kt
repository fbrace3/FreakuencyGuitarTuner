package edu.msudenver.cs3013.project01

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import android.Manifest
import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.core.app.ActivityCompat
import be.tarsos.dsp.AudioEvent
import be.tarsos.dsp.AudioProcessor
import be.tarsos.dsp.pitch.PitchDetectionHandler
import be.tarsos.dsp.pitch.PitchDetectionResult
import be.tarsos.dsp.pitch.PitchProcessor

class StandardTuner : Fragment() {
    private lateinit var noteTextView: TextView
    private lateinit var startButton: Button
    private lateinit var audioRecord: AudioRecord

    private val audioBufferSize = 1024
    private val audioSampleRate = 44100
    private val audioBufferOverlap = 0

    private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_standard_tuner, container, false)

        noteTextView = rootView.findViewById(R.id.standardTunerNote)
        startButton = rootView.findViewById(R.id.start_button)

        startButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startTuner()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    RECORD_AUDIO_PERMISSION_REQUEST_CODE
                )
            }
        }

        return rootView
    }

    @SuppressLint("MissingPermission")
    private fun startTuner() {
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.DEFAULT,
            audioSampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_8BIT,
            audioBufferSize
        )
        audioRecord.startRecording()

        val pitchDetectionHandler = PitchDetectionHandler { result: PitchDetectionResult, _: AudioEvent ->
            val pitchInHz = result.pitch
            // Logic to determine the note based on the detected pitch
            //val detectedNote = detectNoteFromPitch(pitchInHz)
            activity?.runOnUiThread {
                //TODO
                noteTextView.text = pitchInHz.toString()
                Log.d("PITCH", pitchInHz.toString())
            }
        }

        val pitchProcessor = PitchProcessor(
            PitchProcessor.PitchEstimationAlgorithm.FFT_YIN,
            audioSampleRate.toFloat(),
            audioBufferSize,
            pitchDetectionHandler
        )
    }
}
//private fun detectNoteFromPitch(pitchInHz: Float): String {
//    val noteFrequencies = hashMapOf(
//        "E2" to 82.41f,
//        "A2" to 110.0f,
//        "D3" to 146.83f,
//        "G3" to 196.0f,
//        "B3" to 246.94f,
//        "E4" to 329.63f
//    )
//
//    var closestNote = ""
//    var closestDifference = Float.MAX_VALUE
//
//    for ((note, frequency) in noteFrequencies) {
//        val difference = Math.abs(pitchInHz - frequency)
//        if (difference < closestDifference) {
//            closestDifference = difference
//            closestNote = note
//        }
//    }
//
//    return closestNote
//}