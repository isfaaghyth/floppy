package app.isfaaghyth.uicomponent.view.uimodel

import app.isfaaghyth.uicomponent.dataview.Person

data class PersonUIModel(
    val name: String,
    val age: String,
    val avatar: Int
) {

    companion object {
        fun mapToUIModel(person: Person): PersonUIModel {
            return PersonUIModel(
                name = person.name,
                age = person.ageFormat(),
                avatar = person.avatar
            )
        }
    }

}