package ru.ari.domain.repository

import domain.model.Id
import domain.model.Task
import domain.model.TaskDescription
import domain.model.TaskName

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun taskById(id: Id): Task?
    suspend fun addTask(taskName: TaskName, taskDescription: TaskDescription): Task
    suspend fun removeTask(id: Id): Boolean
}