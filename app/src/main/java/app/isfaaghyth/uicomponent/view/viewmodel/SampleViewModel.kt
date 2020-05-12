package app.isfaaghyth.uicomponent.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.isfaaghyth.uicomponent.dataview.PersonDetail
import app.isfaaghyth.uicomponent.view.uimodel.MockUIModel
import app.isfaaghyth.uicomponent.view.uimodel.PersonUIModel

class SampleViewModel: ViewModel() {

    private val _person = MutableLiveData<List<PersonUIModel>>()
    val person: LiveData<List<PersonUIModel>> get() = _person

    private val _personDetail = MutableLiveData<PersonDetail>()
    val personDetail: LiveData<PersonDetail> get() = _personDetail

    fun onShowPerson() {
        _person.value = MockUIModel.samplePersons()
    }

    fun onShowPersonDetail(person: PersonUIModel) {
        _personDetail.value = MockUIModel.personDetail(person)
    }

}