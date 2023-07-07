package edu.msudenver.cs3013.project01

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType

class StandardTuner : Fragment() {
    private lateinit var noteTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    private val audioBufferSize = 8192
    private val audioSampleRate = 44100
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    private val audioChannelConfig = AudioFormat.CHANNEL_IN_MONO
    private val audioRecord: AudioRecord by lazy {
        AudioRecord(
            MediaRecorder.AudioSource.DEFAULT,
            audioSampleRate,
            audioChannelConfig,
            audioFormat,
            audioBufferSize
        )
    }

    private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 200

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_standard_tuner, container, false)

        noteTextView = rootView.findViewById(R.id.standardTunerNote)

        startButton = rootView.findViewById(R.id.startButton)
        stopButton = rootView.findViewById(R.id.stopButton)

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
        stopButton.setOnClickListener {
            audioRecord.stop()

        }
        return rootView
    }

    @SuppressLint("MissingPermission")

    private fun startTuner() {

        audioRecord.startRecording()

        val buffer = ShortArray(audioBufferSize)
        val transformer = FastFourierTransformer(DftNormalization.STANDARD)

        GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                val bytesRead = audioRecord.read(buffer, 0, audioBufferSize)
                val doubleBuffer = buffer.map { it.toDouble() }.toDoubleArray()
                val magnitude = transformer.transform(doubleBuffer, TransformType.FORWARD)

                var maxIndex = -1
                var maxMagnitude = Double.NEGATIVE_INFINITY

                for (i in magnitude.indices) {
                    val currentMagnitude = magnitude[i].abs()
                    if (currentMagnitude > maxMagnitude) {
                        maxMagnitude = currentMagnitude
                        maxIndex = i
                    }
                }

                // Calculate the frequency using the index and sample rate
                val sampleRate = 44100.0 // Sample rate of the audio
                val frequency = maxIndex * sampleRate / (magnitude.size)

                val frequencyString: String = "%.2f Hz".format(frequency)
                activity?.runOnUiThread {
                    noteTextView.text = frequencyString
                }
                Log.d("Tuner frequency", frequencyString)

                // Stop processing if the audioRecord has stopped
                if (bytesRead <= 0) {
                    break
                }
            }

            audioRecord.stop()
            audioRecord.release()
        }
    }}
