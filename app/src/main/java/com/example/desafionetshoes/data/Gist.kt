package com.example.desafionetshoes.data

/*data class Gist(
    val id: String,
    val files: FilesList,
    val owner: OwnerGit
)*/

data class Gist(
    val id: String,
    val files: Map<String, File>,
    val owner: OwnerGit
)
data class OwnerGit(
    val login: String,
    val avatar_url: String
)
data class File(
    val filename: String,
    val type: String
)
/*data class FilesList(
    val file1: File,
    val file2: File,
    val file3: File,
    val file4: File,
    val file5: File
)*/


