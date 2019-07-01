package io.github.aaguys.hackhathonapp.features.schedule

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.data.Repo
import io.github.aaguys.hackhathonapp.use_cases.GetEventByIdUseCase
import kotlinx.android.synthetic.main.event_details_fragment.*
import kotlinx.android.synthetic.main.event_details_fragment.event_speaker
import kotlinx.android.synthetic.main.event_details_fragment.event_tags
import kotlinx.android.synthetic.main.event_details_fragment.event_time
import kotlinx.android.synthetic.main.event_details_fragment.event_title
import kotlinx.android.synthetic.main.fragment_event.*
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class EventDetailsFragment : Fragment() {

    lateinit var eventId: String
    private var listenerSpeakerDetails: OnSpeakerClickListener? = null


    companion object {
        private const val EVENT = "event"


        fun newInstance(eventId: String): EventDetailsFragment {
            val args = Bundle().apply { putString(EVENT, eventId) }
            return EventDetailsFragment().apply { arguments = args }
        }
    }

    private lateinit var viewModel: EventDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventId = arguments!!.getString(EVENT) as String
        return inflater.inflate(R.layout.event_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventDetailsViewModel::class.java)
        viewModel.reload()
        displayEventInfo(eventId)
        viewModel.scheduler.observe(this, Observer { displayEventInfo(eventId) })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSpeakerClickListener) {
            listenerSpeakerDetails = context
        } else {
            throw RuntimeException("$context must implement OnEventClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerSpeakerDetails = null
    }

    fun displayEventInfo(eventId: String) {

        val ev = viewModel.scheduler.value?.find { it.id == eventId }

        ev?.let {
            val speakersUrl = it.speakers[0].photoUrl.toUri()
            Glide
                .with(this)
                .load(speakersUrl)
                //.apply(RequestOptions.)
                .into(author_photo)

            event_title.text = it.title
            event_speaker.text = it.speakers.first().name
            event_time.text = it.time.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm", Locale.getDefault())).toString()
            event_room.text = "Зал ${it.room}"
            event_description.text = it.about
            event_tags.text = it.tags.first().name
            //tags.ser

            author_photo.setOnClickListener {
                listenerSpeakerDetails?.onSpeakerClickListener(eventId)
            }

        }

    }

    interface OnSpeakerClickListener {
        fun onSpeakerClickListener(speakerId: String)
    }
}
