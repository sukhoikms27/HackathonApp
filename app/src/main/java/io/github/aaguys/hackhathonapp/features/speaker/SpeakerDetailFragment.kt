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
import java.util.*

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
            val speakersUrl = it.speakers[0].photoUrl.toUri()
            Glide
                .with(this)
                .load(speakersUrl)
                //.apply(RequestOptions.)
                .into(profile_image)

//            event_title.text = it.title
//            event_speaker.text = it.speakers.first().name
//            event_time.text = it.time.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm", Locale.getDefault())).toString()
//            event_room.text = "Зал ${it.room}"
//            event_description.text = it.about
//            event_tags.text = it.tags.first().name
            //tags.ser


        }

    }

}
