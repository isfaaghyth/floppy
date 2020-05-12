package app.isfaaghyth.uicomponent.ui.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.UIView
import app.isfaaghyth.uicomponent.ui.person.adapter.PersonAdapter
import app.isfaaghyth.uicomponent.util.hide
import app.isfaaghyth.uicomponent.util.show
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

class PersonUIView(
    viewGroup: ViewGroup,
    private val listener: Listener
): UIView(viewGroup) {

    private val view: View = LayoutInflater
        .from(container.context)
        .inflate(R.layout.view_person, container, true)
        .findViewById(R.id.containerPerson)

    private val lstPerson: RecyclerView = view.findViewById(R.id.lstPerson)

    override val containerId: Int = view.id

    fun setPersonList(persons: List<PersonUIModel>) {
        lstPerson.adapter = PersonAdapter(persons, listener)
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
        fun onPersonInfoClicked(person: PersonUIModel)
    }

}