package infra.persistence

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable

object TaskTable : LongIdTable("task") {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
}