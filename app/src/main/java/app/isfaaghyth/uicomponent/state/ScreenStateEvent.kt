package app.isfaaghyth.uicomponent.state

import app.isfaaghyth.uicomponent.component.ComponentEvent
import app.isfaaghyth.uicomponent.dataview.Person
import app.isfaaghyth.uicomponent.dataview.PersonDetail

sealed class ScreenStateEvent: ComponentEvent {
    object Init : ScreenStateEvent()
    data class SetPersonInfo(val person: Person): ScreenStateEvent()
    data class SetPersonDetail(val personDetail: PersonDetail): ScreenStateEvent()
}