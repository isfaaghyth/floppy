package app.isfaaghyth.uicomponent.state

import app.isfaaghyth.uicomponent.component.ComponentEvent
import app.isfaaghyth.uicomponent.dataview.PersonDetail
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

sealed class ScreenStateEvent: ComponentEvent {
    object Init : ScreenStateEvent()
    data class SetPersonInfo(val persons: List<PersonUIModel>): ScreenStateEvent()
    data class SetPersonDetail(val personDetail: PersonDetail): ScreenStateEvent()
}