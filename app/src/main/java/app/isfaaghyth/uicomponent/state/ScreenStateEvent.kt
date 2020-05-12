package app.isfaaghyth.uicomponent.state

import app.isfaaghyth.uicomponent.component.ComponentEvent

sealed class ScreenStateEvent: ComponentEvent {
    object Init : ScreenStateEvent()
    data class SetButtonTitle(val title: String): ScreenStateEvent()
}