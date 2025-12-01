package domain.usecase

import domain.model.Task
import ru.ari.domain.repository.TaskRepository

class GetAllTasksUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(): List<Task> =
        repository.allTasks()
}