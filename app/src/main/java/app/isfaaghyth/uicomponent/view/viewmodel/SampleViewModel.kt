package app.isfaaghyth.uicomponent.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.isfaaghyth.uicomponent.dataview.Person
import app.isfaaghyth.uicomponent.dataview.PersonDetail
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

class SampleViewModel: ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    private val _personDetail = MutableLiveData<PersonDetail>()
    val personDetail: LiveData<PersonDetail> get() = _personDetail

    fun onShowPerson() {
        _person.value = PersonUIModel.samplePerson()
    }

    fun onShowPersonDetail(person: Person) {
        _personDetail.value = PersonUIModel.personDetail(person)
    }

}