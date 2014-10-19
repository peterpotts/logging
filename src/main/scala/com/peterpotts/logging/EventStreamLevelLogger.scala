package com.peterpotts.logging

import akka.event.EventStream

trait EventStreamLevelLogger extends LevelLogger {
  def enabled: Boolean

  def apply(message: => String) {
    if (enabled) log(message, None)
  }

  def apply(message: => String, error: Throwable) {
    if (enabled) log(message, Some(error))
  }

  def log(message: String, error: Option[Throwable])
}

class EventStreamTraceLogger(eventStream: EventStream, logger: org.slf4j.Logger) extends EventStreamLevelLogger {

  def enabled = logger.isTraceEnabled

  def log(message: String, error: Option[Throwable]) =
    eventStream.publish(TraceLogEvent(logger.getName, message, error))
}

class EventStreamDebugLogger(eventStream: EventStream, logger: org.slf4j.Logger) extends EventStreamLevelLogger {
  def enabled = logger.isDebugEnabled

  def log(message: String, error: Option[Throwable]) =
    eventStream.publish(DebugLogEvent(logger.getName, message, error))
}

class EventStreamInfoLogger(eventStream: EventStream, logger: org.slf4j.Logger) extends EventStreamLevelLogger {
  def enabled = logger.isInfoEnabled

  def log(message: String, error: Option[Throwable]) =
    eventStream.publish(InfoLogEvent(logger.getName, message, error))
}

class EventStreamWarnLogger(eventStream: EventStream, logger: org.slf4j.Logger) extends EventStreamLevelLogger {
  def enabled = logger.isWarnEnabled

  def log(message: String, error: Option[Throwable]) =
    eventStream.publish(WarnLogEvent(logger.getName, message, error))
}

class EventStreamErrorLogger(eventStream: EventStream, logger: org.slf4j.Logger) extends EventStreamLevelLogger {
  def enabled = logger.isErrorEnabled

  def log(message: String, error: Option[Throwable]) =
    eventStream.publish(ErrorLogEvent(logger.getName, message, error))
}
