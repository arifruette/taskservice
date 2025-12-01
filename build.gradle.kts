plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("com.gradleup.shadow") version "9.2.0"
}

group = "ru.ari"
version = "0.0.2"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.postgresql)
    implementation(libs.h2)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation(libs.hikkari)

    implementation(libs.koin)
    implementation(libs.koin.ktor)
}
