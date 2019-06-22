package io.github.aaguys.hackhathonapp.features.speaker


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import io.github.aaguys.hackhathonapp.R
import kotlinx.android.synthetic.main.fragment_speaker_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SpeakerDetailFragment : Fragment() {

    lateinit var eventId: String
    private lateinit var viewModel: SpeakerDetailViewModel

    companion object {
        private const val EVENT = "event"
        fun newInstance(eventId: String): SpeakerDetailFragment {
            val args = Bundle().apply { putString(EVENT, eventId) }
            return SpeakerDetailFragment().apply { arguments = args }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventId = arguments!!.getString(EVENT) as String
        return inflater.inflate(R.layout.fragment_speaker_detail, container, false)
    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(SpeakerDetailViewModel::class.java)
//        viewModel.reload()
//        displayEventInfo(eventId)
//        viewModel.scheduler.observe(this, androidx.lifecycle.Observer { displayEventInfo(eventId) })
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SpeakerDetailViewModel::class.java)
        viewModel.reload()
        displayEventInfo(eventId)
        viewModel.scheduler.observe(this, androidx.lifecycle.Observer { displayEventInfo(eventId) })
    }

    fun displayEventInfo(eventId: String) {

        Log.d("222333", " id $eventId  title ${viewModel.getEvent(eventId)?.title}  ${viewModel.scheduler.value?.size}")
        val ev = viewModel.scheduler.value?.find { it.id == eventId }
        Log.d("222333", " id ${ev?.id} ${ev?.speakers?.first()?.photoUrl}")

        ev?.let {
            val speakersUrl = it.speakers.first().photoUrl.toUri()
            Glide
                .with(this)
                .load(speakersUrl)
                //.apply(RequestOptions.)
                .into(profile_image)

            ev.speakers.first().let {
                speaker.text = it.name
                biography.text = it.info


            }


        }

    }

}
