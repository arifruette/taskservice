package presentation.factory

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import infra.persistence.TaskTable
import io.ktor.server.config.*
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseFactory {

    fun init(config: ApplicationConfig) {
        val dbConfig = config.config("db")

        val hikariConfig = HikariConfig().apply {
            driverClassName = dbConfig.property("driver").getString()
            jdbcUrl = dbConfig.property("jdbcUrl").getString()
            username = dbConfig.property("user").getString()
            password = dbConfig.property("password").getString()
            maximumPoolSize = dbConfig.propertyOrNull("maximumPoolSize")
                ?.getString()
                ?.toInt()
                ?: 10

            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        val dataSource = HikariDataSource(hikariConfig)

        Database.connect(dataSource)

        transaction {
            SchemaUtils.create(TaskTable)
        }
    }
}
