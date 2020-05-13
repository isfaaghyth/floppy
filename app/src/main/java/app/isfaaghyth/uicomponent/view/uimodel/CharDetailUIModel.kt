package app.isfaaghyth.uicomponent.view.uimodel

import app.isfaaghyth.uicomponent.data.model.CharDetail

data class CharDetailUIModel(
    val heroName: String,
    val location: String
) {
    companion object {
        fun mapToUIModel(detail: CharDetail): CharDetailUIModel {
            return CharDetailUIModel(
                heroName = detail.heroName,
                location = detail.location
            )
        }
    }
}