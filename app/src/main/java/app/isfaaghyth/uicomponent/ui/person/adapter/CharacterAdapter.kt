package app.isfaaghyth.uicomponent.ui.person.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.uicomponent.ui.person.CharUIView
import app.isfaaghyth.uicomponent.ui.person.viewholder.CharViewHolder
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel

class CharacterAdapter(
    private val characters: List<CharacterUIModel>,
    private val listener: CharUIView.Listener
): RecyclerView.Adapter<CharViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        return CharViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size
}