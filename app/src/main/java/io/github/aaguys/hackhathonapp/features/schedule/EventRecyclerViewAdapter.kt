package io.github.aaguys.hackhathonapp.features.schedule


import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.alpha
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.ColorProvider
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.dummy.DummyContent.DummyItem
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment.OnEventClickListener
import kotlinx.android.synthetic.main.fragment_event.view.*
import org.threeten.bp.format.DateTimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnEventClickListener].
 * TODO: Replace the implementation with code for your data type.
 */
class EventRecyclerViewAdapter(
    mListener: OnEventClickListener?
) : RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

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
        }

        fun bind(event: Event) {
            itemView.apply {
                event_time.text = event.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                event_title.text = event.title
                event_speaker.text = event.speakers.first().name
                event.tags.forEach {
                    val iconChip = getDrawable(context, R.drawable.shape_oval)
                    iconChip!!.mutate().setColorFilter(ColorProvider.getColor(it.name), PorterDuff.Mode.SRC_IN)

                    val chip = Chip(context).apply {
                        text = it.name.capitalize()
                        chipIcon = iconChip
                        setChipIconSizeResource(R.dimen.text_margin)
                        setIconStartPaddingResource(R.dimen.small_margin_size)

                    }
                    event_tags.addView(chip)
                }
            }
        }
    }
}
