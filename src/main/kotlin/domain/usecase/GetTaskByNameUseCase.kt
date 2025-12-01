package domain.usecase

import domain.model.Id
import domain.model.Task
import ru.ari.domain.repository.TaskRepository

class GetTaskByIdUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Id): Task? =
        repository.taskById(id)
}