package app.isfaaghyth.uicomponent.ui.person

import app.isfaaghyth.uicomponent.component.ComponentEvent
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel

sealed class CharInteractionEvent: ComponentEvent {
    data class CharInfoClicked(var character: CharacterUIModel) : CharInteractionEvent()
}