package infra.mapper

import domain.model.Id
import domain.model.Task
import domain.model.TaskDescription
import domain.model.TaskName
import infra.persistence.TaskDAO

fun daoToModel(dao: TaskDAO) = Task(
    id = Id(dao.id.value),
    name = TaskName(value = dao.name),
    description = TaskDescription(value = dao.description),
)