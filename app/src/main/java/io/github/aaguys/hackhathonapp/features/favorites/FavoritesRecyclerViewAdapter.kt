package io.github.aaguys.hackhathonapp.features.favorites


import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.dummy.DummyContent.DummyItem
import io.github.aaguys.hackhathonapp.features.schedule.ItemDiffCallback
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment.OnEventClickListener
import kotlinx.android.synthetic.main.event_details_fragment.view.event_tags
import kotlinx.android.synthetic.main.fragment_event.view.*
import org.threeten.bp.format.DateTimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnEventClickListener].
 * TODO: Replace the implementation with code for your data type.
 */
class FavoritesRecyclerViewAdapter(
    mListener: OnEventClickListener?
) : RecyclerView.Adapter<FavoritesRecyclerViewAdapter.EventViewHolder>() {

    private val eventsList = mutableListOf<Event>()
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

    fun setItems(events: List<Event>) {
        eventsList.addAll(events)
        notifyDataSetChanged()
    }

    fun getItems(): List<Event> {
        return eventsList
    }

    fun updateData(newItems: List<Event>) {
        val diffCallback = ItemDiffCallback(eventsList, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        eventsList.clear()
        eventsList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            itemView.setOnClickListener {
                onEventClickListener?.onEventClickListener(eventsList[layoutPosition].id)
            }
            itemView.favorites.setOnClickListener{
                eventsList[layoutPosition].isFavorite = if (eventsList[layoutPosition].isFavorite == null || !eventsList[layoutPosition].isFavorite!!) true else false
                notifyItemChanged(layoutPosition)
            }
        }

        fun bind(event: Event) {
            itemView.apply {
                event_time.text = event.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                event_title.text = event.title
                event_speaker.text = event.speakers.first().name
                event_tags.text = event.tags.first().name
                favorites.drawable.setColorFilter(if (event.isFavorite == null || !event.isFavorite!!) 0x000000 else 0xFF0000, PorterDuff.Mode.SRC_ATOP)
                //(favorites.background.setColorFilter() as GradientDrawable).setColor(if (event.isFavorite!!) 0xFF0000 else 0xFF000000)
            }
        }
    }
}
