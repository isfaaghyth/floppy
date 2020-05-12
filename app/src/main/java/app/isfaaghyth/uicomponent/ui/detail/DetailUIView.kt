package app.isfaaghyth.uicomponent.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.UIView
import app.isfaaghyth.uicomponent.dataview.PersonDetail
import app.isfaaghyth.uicomponent.util.hide
import app.isfaaghyth.uicomponent.util.show

class DetailUIView(
    container: ViewGroup
): UIView(container) {

    private val view: View = LayoutInflater
        .from(container.context)
        .inflate(R.layout.view_person_detail, container, true)
        .findViewById(R.id.containerPersonDetail)

    private val txtHobby: TextView = view.findViewById(R.id.txtHobby)
    private val txtPhoneNumber: TextView = view.findViewById(R.id.txtPhoneNumber)

    override val containerId: Int = view.id

    fun setPersonDetail(personDetail: PersonDetail) {
        txtHobby.text = personDetail.hobby
        txtPhoneNumber.text = personDetail.phone
    }

    fun stateVisibility(): Boolean {
        return view.visibility == View.VISIBLE
    }

    override fun show() {
        view.show()
    }

    override fun hide() {
        view.hide()
    }

}