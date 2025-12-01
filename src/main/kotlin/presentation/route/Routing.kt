package ru.ari

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("HELLO MY FRIENDS!", contentType = ContentType.Text.Plain)
        }
        get("/omg") {
            call.respondText("Hello fd!", contentType = ContentType.Text.Plain)
        }
    }
}
