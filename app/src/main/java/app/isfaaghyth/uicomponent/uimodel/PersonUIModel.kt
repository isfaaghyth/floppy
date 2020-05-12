package app.isfaaghyth.uicomponent.uimodel

import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.dataview.Person
import app.isfaaghyth.uicomponent.dataview.PersonDetail

object PersonUIModel {

    fun samplePerson(): Person {
        return Person(
            name = "Muh Isfhani Ghiath",
            age = 22,
            avatar = R.mipmap.ic_launcher
        )
    }

    fun personDetail(person: Person): PersonDetail {
        return PersonDetail(
            hobby = "${person.name}'s hobby is football",
            phone = "085123456789"
        )
    }

}