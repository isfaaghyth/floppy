package app.isfaaghyth.uicomponent.ui

import app.isfaaghyth.uicomponent.component.ComponentEvent

sealed class PersonInteractionEvent: ComponentEvent {
    object TestClicked : PersonInteractionEvent()
}