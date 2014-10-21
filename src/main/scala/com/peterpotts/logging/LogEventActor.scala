package com.peterpotts.logging

import akka.actor.Actor

trait LogEventActor extends Actor {
  protected[this] def factory(name: String): org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(name)

  override def preStart() {
    context.system.eventStream.subscribe(context.self, classOf[LogEvent])
  }

  def receive = {
    case logEvent: LogEvent =>
      val logger = factory(logEvent.name)

      logEvent match {
        case TraceLogEvent(_, message, None) => new TraceLevelLogger(logger).apply(message)
        case TraceLogEvent(_, message, Some(error)) => new TraceLevelLogger(logger).apply(message, error)

        case DebugLogEvent(_, message, None) => new DebugLevelLogger(logger).apply(message)
        case DebugLogEvent(_, message, Some(error)) => new DebugLevelLogger(logger).apply(message, error)

        case InfoLogEvent(_, message, None) => new InfoLevelLogger(logger).apply(message)
        case InfoLogEvent(_, message, Some(error)) => new InfoLevelLogger(logger).apply(message, error)

        case WarnLogEvent(_, message, None) => new WarnLevelLogger(logger).apply(message)
        case WarnLogEvent(_, message, Some(error)) => new WarnLevelLogger(logger).apply(message, error)

        case ErrorLogEvent(_, message, None) => new ErrorLevelLogger(logger).apply(message)
        case ErrorLogEvent(_, message, Some(error)) => new ErrorLevelLogger(logger).apply(message, error)
      }
  }


}
