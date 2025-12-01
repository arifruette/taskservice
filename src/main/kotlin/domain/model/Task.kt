package domain.model

data class Task(
    val id: Id,
    val name: TaskName,
    val description: TaskDescription
)