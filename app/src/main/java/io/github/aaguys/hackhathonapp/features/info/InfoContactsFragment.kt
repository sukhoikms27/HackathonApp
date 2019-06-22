package io.github.aaguys.hackhathonapp.features.info

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.aaguys.hackhathonapp.R
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Contact
import kotlinx.android.synthetic.main.fragment_info_contact.view.*
import kotlinx.android.synthetic.main.info_about_fragment.*
import kotlinx.android.synthetic.main.info_contacts_fragment.*

class InfoContactsFragment : Fragment() {

    private lateinit var contactAdapter: InfoContactRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_contacts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info_contacts.apply {
            layoutManager = LinearLayoutManager(activity)
            contactAdapter = InfoContactRecyclerViewAdapter()
            adapter = contactAdapter
        }
        fillData()
        info_site.setOnClickListener {
            if (info_site.text.isNotEmpty()) {
                val uri = Uri.parse(info_site.text.toString()) // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }}
    }

    private fun fillData() {
        val aboutConfData = getTestData()
        info_site.text = aboutConfData.site
        contactAdapter.setItems(aboutConfData.contacts)
    }

}

class InfoContactRecyclerViewAdapter(): RecyclerView.Adapter<InfoContactRecyclerViewAdapter.ContactViewHolder>(){
    private val contacts = ArrayList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_info_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    fun setItems(contacts: Collection<Contact>) {
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact) {
            itemView.apply {
                contact_name.text = contact.name
                contact_link.text = contact.link
            }
        }

    }
}
