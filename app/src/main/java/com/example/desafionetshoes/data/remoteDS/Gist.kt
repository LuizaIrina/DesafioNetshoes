package com.example.desafionetshoes.data.remoteDS

data class Gist(
    val id: String,
    val files: Map<String, File>,
    val owner: OwnerGit,
    //val fav: Boolean
)
data class OwnerGit(
    val login: String,
    val avatar_url: String
)
data class File(
    val filename: String,
    val type: String
)

/*data class Gist(
    val id: String,
    val files: FilesList,
    val owner: OwnerGit
)*/