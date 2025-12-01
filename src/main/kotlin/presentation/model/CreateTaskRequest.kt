package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskRequest(
    val name: String,
    val description: String
)