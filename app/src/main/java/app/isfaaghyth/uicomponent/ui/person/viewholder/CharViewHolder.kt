package app.isfaaghyth.uicomponent.ui.person.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.ui.person.CharUIView
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel
import coil.api.load

class CharViewHolder(
    view: View,
    private val listener: CharUIView.Listener
): RecyclerView.ViewHolder(view) {

    private val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val txtAge: TextView = view.findViewById(R.id.txtAge)

    fun bind(character: CharacterUIModel) {
        imgAvatar.load(character.avatar)
        txtName.text = character.name
        txtAge.text = character.age

        itemView.setOnClickListener {
            listener.onPersonInfoClicked(character)
        }
    }

    companion object {
        @LayoutRes const val LAYOUT = R.layout.item_character

        fun create(
            container: ViewGroup,
            listener: CharUIView.Listener
        ): CharViewHolder {
            val containerLayout = LayoutInflater
                .from(container.context)
                .inflate(LAYOUT, container, false)
            return CharViewHolder(containerLayout, listener)
        }
    }

}