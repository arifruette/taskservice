package infra.mapper

import domain.model.Id
import domain.model.Task
import domain.model.TaskDescription
import domain.model.TaskName
import infra.persistence.TaskDAO

fun TaskDAO.toDomain(): Task =
    Task(
        id = Id(this.id.value),
        name = TaskName(this.name),
        description = TaskDescription(this.description)
    )