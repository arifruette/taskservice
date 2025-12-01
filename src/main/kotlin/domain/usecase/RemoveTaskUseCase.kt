    package domain.usecase

import domain.model.Id
import ru.ari.domain.repository.TaskRepository

class RemoveTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Id): Boolean =
        repository.removeTask(id)
}