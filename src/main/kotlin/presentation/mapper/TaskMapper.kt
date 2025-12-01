package presentation.mapper

import domain.model.Task
import presentation.model.TaskResponse

fun Task.toResponse() = TaskResponse(
    id = id.value,
    name = name.value,
    description = description.value
)