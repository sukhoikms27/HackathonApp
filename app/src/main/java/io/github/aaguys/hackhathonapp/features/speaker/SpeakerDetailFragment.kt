package io.github.aaguys.hackhathonapp.features.speaker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.aaguys.hackhathonapp.R
import kotlinx.android.synthetic.main.fragment_speaker_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SpeakerDetailFragment : Fragment() {

    lateinit var speakerId: String

    companion object {
        private const val SPEAKER = "speaker"
        fun newInstance(speakerId: String): SpeakerDetailFragment {
            val args = Bundle().apply { putString(SPEAKER, speakerId) }
            return SpeakerDetailFragment().apply { arguments = args }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        speakerId = arguments!!.getString(SPEAKER) as String

        return inflater.inflate(R.layout.fragment_speaker_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        speaker.text = "HELLO"
        biography.text = "HELLO WORDL"

        super.onViewCreated(view, savedInstanceState)
    }

}
