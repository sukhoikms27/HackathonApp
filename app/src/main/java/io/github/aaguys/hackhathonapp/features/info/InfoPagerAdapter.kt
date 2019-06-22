package io.github.aaguys.hackhathonapp.features.info

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import io.github.aaguys.hackhathonapp.common.AboutConfData

class InfoPagerAdapter(fragmentManager: FragmentManager, val aboutConfData: AboutConfData): FragmentPagerAdapter(fragmentManager) {
    private val PAGE_COUNT = 3
    private val tabTitles = arrayOf("Общее", "Правила", "Контакты")

    override fun getItem(position: Int): Fragment {
        val result =
        when (position) {
            1 -> InfoRulesFragment()
            2 -> InfoContactsFragment()
            else -> InfoAboutFragment()
        }
        return result
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}