package ru.ari

import domain.model.Id
import domain.model.TaskName
import domain.model.TaskDescription
import domain.usecase.AddTaskUseCase
import domain.usecase.GetAllTasksUseCase
import domain.usecase.GetTaskByIdUseCase
import domain.usecase.RemoveTaskUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import presentation.mapper.toResponse
import presentation.model.CreateTaskRequest

fun Application.configureRouting() {
    routing {
        taskRoutes()
    }
}

fun Route.taskRoutes() {

    val getAllTasks: GetAllTasksUseCase by inject()
    val getTaskById: GetTaskByIdUseCase by inject()
    val addTask: AddTaskUseCase by inject()
    val removeTask: RemoveTaskUseCase by inject()

    route("/tasks") {

        get {
            val tasks = getAllTasks()
            call.respond(tasks.map { it.toResponse() })
        }

        get("{id}") {
            val idParam = call.parameters["id"]
            if (idParam == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing 'id' parameter")
                return@get
            }

            val idLong = idParam.toLongOrNull()
            if (idLong == null || idLong < 0) {
                call.respond(HttpStatusCode.BadRequest, "'id' is invalid")
                return@get
            }

            val task = getTaskById(Id(idLong))
            if (task == null) {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            } else {
                call.respond(task.toResponse())
            }
        }

        post {
            val request = call.receive<CreateTaskRequest>()

            try {
                val name = TaskName(request.name)
                val description = TaskDescription(request.description)

                val created = addTask(name, description)
                call.respond(HttpStatusCode.Created, created.toResponse())
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid task data")
            } catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.Conflict, e.message ?: "Task already exists")
            }
        }

        delete("{id}") {
            val idParam = call.parameters["id"]
            if (idParam == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing 'id' parameter")
                return@delete
            }

            val idLong = idParam.toLongOrNull()
            if (idLong == null || idLong < 0) {
                call.respond(HttpStatusCode.BadRequest, "'id' is invalid")
                return@delete
            }

            val removed = try {
                removeTask(Id(idLong))
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid id")
                return@delete
            }

            if (removed) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }
    }
}
