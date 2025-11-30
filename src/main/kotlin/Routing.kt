package ru.ari

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.*

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
