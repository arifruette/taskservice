package ru.ari.infra.repository

import domain.model.Id
import domain.model.Task
import domain.model.TaskDescription
import domain.model.TaskName
import infra.mapper.daoToModel
import infra.mapper.toDomain
import infra.persistence.TaskDAO
import infra.persistence.TaskTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import ru.ari.domain.repository.TaskRepository

class PostgresTaskRepository : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskDAO.all().map(::daoToModel)
    }

    override suspend fun taskById(id: Id): Task? = suspendTransaction {
        TaskDAO
            .find { (TaskTable.id eq id.value) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun addTask(taskName: TaskName, taskDescription: TaskDescription): Task = suspendTransaction {
        TaskDAO.new {
            this.name = taskName.value
            this.description = taskDescription.value
        }.toDomain()
    }

    override suspend fun removeTask(id: Id): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere {
            TaskTable.name eq name
        }
        rowsDeleted == 1
    }
}