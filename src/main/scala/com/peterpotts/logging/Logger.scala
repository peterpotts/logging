package com.peterpotts.logging

import akka.event.EventStream

sealed trait Logger {
  val trace: LevelLogger
  val debug: LevelLogger
  val info: LevelLogger
  val warn: LevelLogger
  val error: LevelLogger
}

class SLF4JLogger(logger: org.slf4j.Logger) extends Logger {
  lazy val trace = new TraceLevelLogger(logger)
  lazy val debug = new DebugLevelLogger(logger)
  lazy val info = new InfoLevelLogger(logger)
  lazy val warn = new WarnLevelLogger(logger)
  lazy val error = new ErrorLevelLogger(logger)
}

class EventStreamLogger(eventStream: EventStream, logger: org.slf4j.Logger) extends Logger {
  lazy val trace = new EventStreamTraceLogger(eventStream, logger)
  lazy val debug = new EventStreamDebugLogger(eventStream, logger)
  lazy val info = new EventStreamInfoLogger(eventStream, logger)
  lazy val warn = new EventStreamWarnLogger(eventStream, logger)
  lazy val error = new EventStreamErrorLogger(eventStream, logger)
}
