package app.isfaaghyth.uicomponent.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.isfaaghyth.uicomponent.domain.AvengersDetailUseCase
import app.isfaaghyth.uicomponent.domain.AvengersUseCase
import app.isfaaghyth.uicomponent.util.SingleLiveData
import app.isfaaghyth.uicomponent.view.uimodel.CharDetailUIModel
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel

class SampleViewModel: ViewModel() {

    private val _person = SingleLiveData<List<CharacterUIModel>>()
    val character: LiveData<List<CharacterUIModel>> get() = _person

    private val _personDetail = SingleLiveData<CharDetailUIModel>()
    val personDetail: LiveData<CharDetailUIModel> get() = _personDetail

    private val avengersUseCase = AvengersUseCase()
    private val avengersDetailUseCase = AvengersDetailUseCase()

    fun onShowPerson() {
        _person.setValue(avengersUseCase())
    }

    fun onShowPersonDetail(character: CharacterUIModel) {
        val execute = avengersDetailUseCase(character.id)
        _personDetail.setValue(execute)
    }

}