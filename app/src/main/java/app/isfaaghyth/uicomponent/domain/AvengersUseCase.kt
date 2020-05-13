package app.isfaaghyth.uicomponent.domain

import app.isfaaghyth.uicomponent.data.consts.MockResponse
import app.isfaaghyth.uicomponent.data.model.CharacterList
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel
import com.google.gson.Gson

internal class AvengersUseCase {

    operator fun invoke(): List<CharacterUIModel> {
        val data = Gson().fromJson<CharacterList>(
            MockResponse.avengersJson,
            CharacterList::class.java
        )
        return CharacterUIModel.mapToUIModel(
            data.avengers
        )
    }

}