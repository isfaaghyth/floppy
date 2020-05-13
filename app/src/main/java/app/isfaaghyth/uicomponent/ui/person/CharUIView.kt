package app.isfaaghyth.uicomponent.ui.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.UIView
import app.isfaaghyth.uicomponent.ui.person.adapter.CharacterAdapter
import app.isfaaghyth.uicomponent.util.hide
import app.isfaaghyth.uicomponent.util.show
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel

class CharUIView(
    viewGroup: ViewGroup,
    private val listener: Listener
): UIView(viewGroup) {

    private val view: View = LayoutInflater
        .from(container.context)
        .inflate(R.layout.view_char_list, container, true)
        .findViewById(R.id.containerCharList)

    override val containerId: Int = view.id

    private val lstPerson: RecyclerView = view.findViewById(R.id.lstPerson)

    fun bind(characters: List<CharacterUIModel>) {
        lstPerson.adapter = CharacterAdapter(characters, listener)
    }

    override fun show() {
        view.show()
    }

    override fun hide() {
        view.hide()
    }

    fun onDestroy() {
        //TODO
    }

    interface Listener {
        fun onPersonInfoClicked(character: CharacterUIModel)
    }

}