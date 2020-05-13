package app.isfaaghyth.uicomponent.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterList(
    @Expose
    @SerializedName("avengers")
    val avengers: List<Character>
)