package infra.persistence


import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass


class TaskDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TaskDAO>(TaskTable)

    var name by TaskTable.name
    var description by TaskTable.description
}