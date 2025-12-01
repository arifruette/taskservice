package ru.ari.di

import domain.usecase.AddTaskUseCase
import domain.usecase.GetAllTasksUseCase
import domain.usecase.GetTaskByIdUseCase
import domain.usecase.RemoveTaskUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import ru.ari.domain.repository.TaskRepository
import ru.ari.infra.repository.PostgresTaskRepository

val taskModule = module {

    single { Dispatchers.IO }

    single<TaskRepository> { PostgresTaskRepository() }

    single { GetAllTasksUseCase(get()) }
    single { GetTaskByIdUseCase(get()) }
    single { AddTaskUseCase(get()) }
    single { RemoveTaskUseCase(get()) }
}