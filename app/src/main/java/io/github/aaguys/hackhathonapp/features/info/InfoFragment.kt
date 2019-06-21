package io.github.aaguys.hackhathonapp.features.info

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Contact
import kotlinx.android.synthetic.main.fragment_info_contact.view.*
import kotlinx.android.synthetic.main.info_fragment.*
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import io.github.aaguys.hackhathonapp.R
import androidx.core.content.ContextCompat.startActivity




class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel
    private lateinit var contactAdapter: InfoContactRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        info_contacts.apply {
            layoutManager = LinearLayoutManager(activity)
            contactAdapter = InfoContactRecyclerViewAdapter()
            adapter = contactAdapter
        }
        fillData()

        info_site.setOnClickListener {
            if (!info_site.text.isEmpty()) {
            val uri = Uri.parse(info_site.text.toString()) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
         }}
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fillData() {
        val aboutConfData = getTestData()
        info_title.text = aboutConfData.name
        info_about.text = aboutConfData.about
        info_rules.text = aboutConfData.rules
        info_site.text = aboutConfData.site
        contactAdapter.setItems(aboutConfData.contacts)
    }

    private fun getTestData(): AboutConfData{
        val result = AboutConfData(
            "TechTrain",
        2018,
        "https://2018.techtrain.ru/",
        "https://2018.techtrain.ru/assets/images/logo.svg",
        "TechTrain – большой фестиваль для разработчиков, инженеров и им сочувствующих. Фестиваль, который в одном месте собирает всех, кто любит разработку ПО так же, как мы. Неважно, есть у вас виртуальная машина или нет, топите вы за ООП или за функциональщину, за перформанс или за скорость разработки – на TechTrain вы найдете единомышленников, интересные проекты и технологические новинки (VR и AR, новые архитектуры и гаджеты, да кто знает, что еще).",
        "Задача TechTrain – создать свободный от любого вида дискриминации фестиваль для всех, независимо от пола, сексуальной ориентации, состояния здоровья, телосложения, расы или вероисповедания.\n\nОрганизаторы не будут мириться с какими бы то ни было формами ущемления, оскорбления или унижения со стороны участников фестиваля. Оскорбительный язык или образы не поддерживаются и не допускаются ни в какой форме на докладах, тренингах, вечеринках, стендах партнеров и спонсоров, в социальных сетях или любых других online-медиа.\n\nУчастники, нарушающие данные правила, могут быть подвергнуты соответствующим санкциям или отстранены от дальнейшего участия в фестивале без возмещения стоимости участия по усмотрению организаторов конференции.",
        "30.354530,59.762994",
            listOf(Contact("Билеты", "tickets@techtrain.ru"),
                Contact("Программа","program@techtrain.ru"),
                Contact("Партнеры", "partners@techtrain.ru"),
                Contact("Вопросы","questions@techtrain.ru"),
                Contact("Сайт", "site@techtrain.ru")))
        return result
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InfoViewModel::class.java)
        // TODO: Use the ViewModel
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
        holder.bind(contacts.get(position))
    }

    fun setItems(contacts: Collection<Contact>) {
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder : RecyclerView.ViewHolder{
        constructor(itemView: View) : super(itemView) {

        }
        fun bind(contact: Contact) {
            itemView.apply {
                contact_name.text = contact.name
                contact_link.text = contact.link
            }
        }

    }
}