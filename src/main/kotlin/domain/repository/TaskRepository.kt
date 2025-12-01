package ru.ari.domain.repository

import domain.model.Id
import domain.model.Task

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun taskById(id: Id): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(id: Id): Boolean
}