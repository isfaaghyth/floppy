package app.isfaaghyth.uicomponent.ui.person.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.uicomponent.ui.person.PersonUIView
import app.isfaaghyth.uicomponent.ui.person.viewholder.PersonViewHolder
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

class PersonAdapter(
    private val persons: List<PersonUIModel>,
    private val listener: PersonUIView.Listener
): RecyclerView.Adapter<PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int = persons.size
}