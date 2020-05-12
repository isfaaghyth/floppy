package app.isfaaghyth.uicomponent.ui.person.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.ui.person.PersonUIView
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

class PersonViewHolder(
    view: View,
    private val listener: PersonUIView.Listener
): RecyclerView.ViewHolder(view) {

    private val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val txtAge: TextView = view.findViewById(R.id.txtAge)

    fun bind(person: PersonUIModel) {
        imgAvatar.setBackgroundResource(person.avatar)
        txtName.text = person.name
        txtAge.text = person.age

        itemView.setOnClickListener {
            listener.onPersonInfoClicked(person)
        }
    }

    companion object {
        @LayoutRes const val LAYOUT = R.layout.item_person

        fun create(
            container: ViewGroup,
            listener: PersonUIView.Listener
        ): PersonViewHolder {
            val containerLayout = LayoutInflater
                .from(container.context)
                .inflate(LAYOUT, container, false)
            return PersonViewHolder(containerLayout, listener)
        }
    }

}