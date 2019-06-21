package io.github.aaguys.hackhathonapp

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.features.schedule.EventDetailsFragment
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment
import io.github.aaguys.hackhathonapp.helpers.inTransaction

class MainActivity : AppCompatActivity(), ScheduleFragment.OnEventClickListener {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.root_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        fun createFragment(): Fragment = ScheduleFragment.newInstance(1)

        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment, "eventList") }
        }
    }

    override fun onEventClickListener(eventId: String) {
        val eventDetailsFragment = EventDetailsFragment.newInstance(eventId)
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, eventDetailsFragment, "eventDetails")
            addToBackStack("eventDetails")
        }

    }
}
