package domain.usecase

import domain.model.TaskDescription
import domain.model.TaskName
import ru.ari.domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(name: TaskName, description: TaskDescription) =
        repository.addTask(taskName = name, taskDescription = description)
}