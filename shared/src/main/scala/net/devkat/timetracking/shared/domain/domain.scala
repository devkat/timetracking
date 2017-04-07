package net.devkat.timetracking.shared.domain

import java.time.Instant

case class Client(id: String, name: String)

case class Project(clientId: String, name: String)

case class TimeEntry(projectId: String, start: Instant, end: Option[Instant])
