package app.isfaaghyth.uicomponent.view.uimodel

import app.isfaaghyth.uicomponent.data.model.Character

data class CharacterUIModel(
    val id: Int,
    val name: String,
    val age: String,
    val avatar: String
) {

    companion object {
        fun mapToUIModel(characters: List<Character>): List<CharacterUIModel> {
            val characterList = arrayListOf<CharacterUIModel>()
            characters.forEach {
                characterList.add(CharacterUIModel(
                    id = it.id,
                    name = it.name,
                    age = it.ageFormat(),
                    avatar = it.avatar
                ))
            }
            return characterList
        }
    }

}