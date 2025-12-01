package ru.ari

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.koin.ktor.plugin.Koin
import presentation.factory.DatabaseFactory
import ru.ari.di.taskModule

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init(environment.config)

    install(ContentNegotiation) {
        json()
    }

    install(Koin) {
        modules(taskModule)
    }

    configureRouting()
}
