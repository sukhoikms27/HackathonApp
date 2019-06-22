package io.github.aaguys.hackhathonapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.aaguys.hackhathonapp.features.info.InfoFragment
import io.github.aaguys.hackhathonapp.features.schedule.EventDetailsFragment
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment
import io.github.aaguys.hackhathonapp.helpers.inTransaction
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ScheduleFragment.OnEventClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_view.apply {
            selectedItemId = R.id.action_schedules
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }

        openFragment(ScheduleFragment())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.action_information -> {
                openFragment(InfoFragment())
                true
            }
            R.id.action_schedules -> {
                openFragment(ScheduleFragment())
                true
            }
            R.id.action_favorites -> {
                openFragment(ScheduleFragment())
                true
            }
            else -> false
        }

    }


    override fun onEventClickListener(eventId: String) {
        val eventDetailsFragment = EventDetailsFragment.newInstance(eventId)
        supportFragmentManager.inTransaction {
            replace(R.id.container, eventDetailsFragment, "eventDetails")
            addToBackStack("eventDetails")
        }
    }

    private fun openFragment(fragment: Fragment) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            if (fragment !is ScheduleFragment)
                supportFragmentManager.inTransaction {
                    replace(R.id.container, fragment)
                }
        } else
            supportFragmentManager.inTransaction {
                replace(R.id.container, fragment)
            }
    }
}




