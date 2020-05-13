package app.isfaaghyth.uicomponent.state

import app.isfaaghyth.uicomponent.component.ComponentEvent
import app.isfaaghyth.uicomponent.view.uimodel.CharDetailUIModel
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel

sealed class ScreenStateEvent: ComponentEvent {
    object Init : ScreenStateEvent()
    data class SetPersonInfo(val characters: List<CharacterUIModel>): ScreenStateEvent()
    data class SetPersonDetail(val charDetail: CharDetailUIModel): ScreenStateEvent()
}