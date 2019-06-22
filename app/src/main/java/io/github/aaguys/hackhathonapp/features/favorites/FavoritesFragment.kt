package io.github.aaguys.hackhathonapp.features.favorites

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.features.schedule.EventRecyclerViewAdapter
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment
import io.github.aaguys.hackhathonapp.features.schedule.SchedulerViewModel
import kotlinx.android.synthetic.main.fragment_event_list.*


class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var eventsAdapter: EventRecyclerViewAdapter
    private var listener: ScheduleFragment.OnEventClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = false
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        viewModel.scheduler.observe(
            this, Observer { list -> eventsAdapter.updateData(list.filter { it.isFavorite != null && it.isFavorite!! }) })
        viewModel.reload()
        events_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            eventsAdapter = EventRecyclerViewAdapter(listener)
            adapter = eventsAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        super.onAttach(context)
        if (context is ScheduleFragment.OnEventClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnEventClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
