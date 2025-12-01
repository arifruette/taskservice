package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    val id: Long,
    val name: String,
    val description: String
)