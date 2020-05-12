package app.isfaaghyth.uicomponent.component

import android.view.ViewGroup
import androidx.annotation.IdRes

abstract class UIView(val container: ViewGroup) {
    @get:IdRes abstract val containerId: Int
    abstract fun show()
    abstract fun hide()
}