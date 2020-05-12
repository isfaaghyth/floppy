package app.isfaaghyth.uicomponent.ui.person

import app.isfaaghyth.uicomponent.component.ComponentEvent
import app.isfaaghyth.uicomponent.dataview.Person

sealed class PersonInteractionEvent: ComponentEvent {
    data class PersonInfoClicked(var person: Person) : PersonInteractionEvent()
}