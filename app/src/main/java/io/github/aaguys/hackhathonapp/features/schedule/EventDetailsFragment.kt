package io.github.aaguys.hackhathonapp.features.schedule

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.data.Repo
import io.github.aaguys.hackhathonapp.use_cases.GetEventByIdUseCase
import kotlinx.android.synthetic.main.event_details_fragment.*

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
        viewModel.scheduler.observe(this, Observer { displayEventInfo(eventId) })

    }

    fun displayEventInfo(eventId: String) {
        val event = GetEventByIdUseCase(Repo, eventId).event
//        val event = ScheduleFragment().eventsMock.find { it.id == eventId }

        event?.let {
            event_title.text = it.title
            event_speaker.text = it.speakers.first().name
            event_time.text = it.time.toString()
            event_room.text = it.room
            event_description.text = it.about
            event_tags.text = it.tags.first().name
        }

        author_photo.setOnClickListener {
            //val speakerId =  event?.speakers?.first()?.id //Получить рабочий ID
            listenerSpeakerDetails?.onSpeakerClickListener("speakerID")
        }

    }


    interface OnSpeakerClickListener {
        fun onSpeakerClickListener(speakerId: String)
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
}
