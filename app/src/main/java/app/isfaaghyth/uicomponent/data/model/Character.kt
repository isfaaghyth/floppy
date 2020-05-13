package app.isfaaghyth.uicomponent.data.model

data class Character(
    val id: Int,
    val name: String,
    val age: Int,
    val avatar: String
) {

    fun ageFormat(): String {
        return "$age years old"
    }

}