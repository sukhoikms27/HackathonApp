package io.github.aaguys.hackhathonapp.features.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.common.Speaker
import io.github.aaguys.hackhathonapp.common.Tag
import io.github.aaguys.hackhathonapp.use_cases.GetEventByIdUseCase
import kotlinx.android.synthetic.main.fragment_event_list.*
import org.threeten.bp.LocalTime

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ScheduleFragment.OnEventClickListener] interface.
 */
class ScheduleFragment : Fragment() {




    private lateinit var viewModel:SchedulerViewModel


    // TODO: Customize parameters
    private var columnCount = 1
    private lateinit var eventsAdapter: EventRecyclerViewAdapter
    private var listener: OnEventClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SchedulerViewModel::class.java)
        viewModel.scheduler.observe(
            this, Observer { list -> eventsAdapter.updateData(list) })
        viewModel.reload()
        events_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            eventsAdapter = EventRecyclerViewAdapter(listener)
            adapter = eventsAdapter
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEventClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnEventClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnEventClickListener {
        fun onEventClickListener(eventId: String)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
