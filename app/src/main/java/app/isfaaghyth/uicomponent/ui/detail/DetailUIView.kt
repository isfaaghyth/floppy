package app.isfaaghyth.uicomponent.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.UIView
import app.isfaaghyth.uicomponent.view.uimodel.CharDetailUIModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DetailUIView(
    container: ViewGroup
): UIView(container) {

    private val view: View = LayoutInflater
        .from(container.context)
        .inflate(R.layout.view_char_detail, container, true)
        .findViewById(R.id.containerCharDetail)

    override val containerId: Int = view.id

    private val txtHeroName: TextView = view.findViewById(R.id.txtHeroName)
    private val txtLocation: TextView = view.findViewById(R.id.txtLocation)
    private val bottomSheetBehavior = BottomSheetBehavior.from(view)

    fun bind(detail: CharDetailUIModel) {
        txtHeroName.text = detail.heroName
        txtLocation.text = detail.location
    }

    override fun show() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun hide() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    internal fun showWithHeight(height: Int = 800) {
        if (view.height != height) {
            val layoutParams = view.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.height = height
            view.layoutParams = layoutParams
        }
        show()
    }

}