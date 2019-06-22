package io.github.aaguys.hackhathonapp.features.info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.AboutConfData
import kotlinx.android.synthetic.main.info_about_fragment.*
import kotlinx.android.synthetic.main.info_rules_fragment.*

class InfoRulesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_rules_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillData()
    }

    private fun fillData() {
        val aboutConfData = getTestData()
        info_rules.text = aboutConfData.rules
    }

}
