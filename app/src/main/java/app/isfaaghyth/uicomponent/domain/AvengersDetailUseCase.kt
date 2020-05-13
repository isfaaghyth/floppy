package app.isfaaghyth.uicomponent.domain

import app.isfaaghyth.uicomponent.data.consts.MockResponse
import app.isfaaghyth.uicomponent.data.model.CharDetailList
import app.isfaaghyth.uicomponent.view.uimodel.CharDetailUIModel
import com.google.gson.Gson

class AvengersDetailUseCase {

    operator fun invoke(charId: Int): CharDetailUIModel {
        val data = Gson().fromJson<CharDetailList>(
            MockResponse.avengersDetailJson,
            CharDetailList::class.java
        )
        return CharDetailUIModel.mapToUIModel(
            data.avengers.first {
                it.id == charId
            }
        )
    }

}