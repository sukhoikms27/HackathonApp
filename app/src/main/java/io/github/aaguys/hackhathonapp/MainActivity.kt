package io.github.aaguys.hackhathonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.features.schedule.ScheduleFragment
import io.github.aaguys.hackhathonapp.helpers.inTransaction

class MainActivity : AppCompatActivity(), ScheduleFragment.OnListFragmentInteractionListener{
    override fun onListFragmentInteraction(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment, "lecturesList") }
        }
    }
}
