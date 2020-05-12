package app.isfaaghyth.uicomponent.dataview

data class Person(
    val name: String,
    val age: Int,
    val avatar: Int
) {

    fun ageFormat(): String {
        return "$age years old"
    }

}