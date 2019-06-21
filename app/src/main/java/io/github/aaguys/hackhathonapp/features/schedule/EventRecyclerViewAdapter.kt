package io.github.aaguys.hackhathonapp.features.schedule


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.dummy.DummyContent.DummyItem
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.event_details_fragment.view.*
import kotlinx.android.synthetic.main.event_details_fragment.view.event_tags
import kotlinx.android.synthetic.main.fragment_event.view.*
import kotlinx.android.synthetic.main.fragment_event.view.event_speaker
import kotlinx.android.synthetic.main.fragment_event.view.event_time
import kotlinx.android.synthetic.main.fragment_event.view.event_title

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class EventRecyclerViewAdapter(
    mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

    private val eventsList = ArrayList<Event>()
    private val onEventClickListener = mListener

    init {
        this.onEventClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_event, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    fun setItems(events: Collection<Event>) {
        eventsList.addAll(events)
        notifyDataSetChanged()
    }

    fun getItems(): Collection<Event> {
        return eventsList
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            itemView.setOnClickListener {
                onEventClickListener?.onListFragmentInteraction(eventsList[layoutPosition])
            }
        }

        fun bind(event: Event) {
            itemView.apply {
                event_time.text = event.time.toString() //fixme
                event_title.text = event.title
                event_speaker.text = event.speakers.first().name
                event_tags.text = event.tags.first().name
            }
        }
    }
}
