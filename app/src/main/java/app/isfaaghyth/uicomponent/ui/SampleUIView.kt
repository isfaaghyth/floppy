package app.isfaaghyth.uicomponent.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.UIView
import app.isfaaghyth.uicomponent.util.hide
import app.isfaaghyth.uicomponent.util.show

class SampleUIView(
    viewGroup: ViewGroup,
    private val listener: Listener
): UIView(viewGroup) {

    private val view: View = LayoutInflater
        .from(container.context)
        .inflate(R.layout.view_sample, container, true)
        .findViewById(R.id.containerViewSample)

    override val containerId: Int = view.id

    private val btnTest: Button = view.findViewById(R.id.btnTest)

    fun setButtonTitle(title: String) {
        btnTest.text = title
        btnTest.setOnClickListener {
            listener.onTestClicked()
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
        fun onTestClicked()
    }

}