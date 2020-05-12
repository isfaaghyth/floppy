package app.isfaaghyth.uicomponent.ui.person

import app.isfaaghyth.uicomponent.component.ComponentEvent
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

sealed class PersonInteractionEvent: ComponentEvent {
    data class PersonInfoClicked(var person: PersonUIModel) : PersonInteractionEvent()
}