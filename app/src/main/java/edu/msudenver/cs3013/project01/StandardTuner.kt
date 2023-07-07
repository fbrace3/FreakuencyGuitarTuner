package edu.msudenver.cs3013.project01
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioRecord
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import be.tarsos.dsp.AudioProcessor
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import be.tarsos.dsp.pitch.PitchDetectionHandler
import be.tarsos.dsp.pitch.PitchProcessor
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType


class StandardTuner : Fragment() {
    private lateinit var noteTextView: TextView
    private lateinit var startButton: Button
    private lateinit var audioRecord: AudioRecord

    private val audioBufferSize = 2048
    private val audioSampleRate = 44100
    private val audioBufferOverlap = 0
    private var audioBuffer = ShortArray(audioBufferSize)

    private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_standard_tuner, container, false)

        noteTextView = rootView.findViewById(R.id.standardTunerNote)

        startButton = rootView.findViewById(R.id.startButton)

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
        AudioRecord(
            0,
            audioSampleRate,
            16,
            2,
            audioBufferSize * 2
        ).apply {
            audioRecord = this
            startRecording()
        }
        var maxIndex = -1
        var maxMagnitude = Double.NEGATIVE_INFINITY

        while (audioRecord.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
            audioRecord.read(audioBuffer, 0, audioBufferSize)
            val transformer = FastFourierTransformer(DftNormalization.STANDARD)
            val doubleBuffer: DoubleArray = audioBuffer.map { it.toDouble() }.toDoubleArray()
            val magnitude = transformer.transform(doubleBuffer, TransformType.FORWARD)

            for (i in magnitude.indices) {
                val currentMagnitude = magnitude[i].abs()
                if (currentMagnitude > maxMagnitude) {
                    maxMagnitude = currentMagnitude
                    maxIndex = i
                }
            }

// Calculate the frequency using the index and sample rate
            val sampleRate = 44100.0 // Sample rate of the audio
            val frequency = maxIndex * sampleRate / magnitude.size

            val frequencyString: String = "%.2f Hz".format(frequency)
            noteTextView.text = frequencyString
            Log.d("Tuner", frequencyString)
        }
    }
}