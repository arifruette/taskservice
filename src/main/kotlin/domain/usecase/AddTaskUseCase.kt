package domain.usecase

import domain.model.Task
import ru.ari.domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = repository.addTask(task)
}