package app.isfaaghyth.uicomponent.ui.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.UIView
import app.isfaaghyth.uicomponent.dataview.Person
import app.isfaaghyth.uicomponent.util.hide
import app.isfaaghyth.uicomponent.util.show

class PersonUIView(
    viewGroup: ViewGroup,
    private val listener: Listener
): UIView(viewGroup) {

    private val view: View = LayoutInflater
        .from(container.context)
        .inflate(R.layout.view_person, container, true)
        .findViewById(R.id.containerPerson)

    override val containerId: Int = view.id

    private val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val txtAge: TextView = view.findViewById(R.id.txtAge)

    fun setPersonInfo(person: Person) {
        imgAvatar.setBackgroundResource(person.avatar)
        txtName.text = person.name
        txtAge.text = person.ageFormat()
        view.setOnClickListener {
            listener.onPersonInfoClicked(person)
        }
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
        fun onPersonInfoClicked(person: Person)
    }

}