package app.isfaaghyth.uicomponent.data.consts

object MockResponse {

    val avengersJson = """
        {
            "avengers": [
                {
                    "id": 1,
                    "name": "Robert Downey Jr.",
                    "age": 50,
                    "avatar": "https://avatarfiles.alphacoders.com/139/thumb-139523.jpg"
                },
                {
                    "id": 2,
                    "name": "Dr. Strange",
                    "age": 53,
                    "avatar": "https://avatarfiles.alphacoders.com/646/64610.jpg"
                },
                {
                    "id": 3,
                    "name": "Steve Roger",
                    "age": 1000,
                    "avatar": "https://avatarfiles.alphacoders.com/130/thumb-130595.jpg"
                },
                {
                    "id": 4,
                    "name": "Thor",
                    "age": 1000,
                    "avatar": "https://avatarfiles.alphacoders.com/127/thumb-127575.jpg"
                }
            ]
        }
    """.trimIndent()

    val avengersDetailJson = """
        {
            "avengers": [
                {
                    "id": 1,
                    "heroName": "Iron Man",
                    "location": "New York"
                },
                {
                    "id": 2,
                    "heroName": "Dr.Strange",
                    "location": "California"
                },
                {
                    "id": 3,
                    "heroName": "Captain America",
                    "location": "Brooklyn"
                },
                {
                    "id": 4,
                    "heroName": "Thor",
                    "location": "Asgardian"
                }
            ]
        }
    """.trimIndent()

}