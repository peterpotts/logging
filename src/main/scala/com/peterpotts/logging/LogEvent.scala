package com.peterpotts.logging

sealed trait LogEvent {
  val name: String
  val message: String
  val error: Option[Throwable]
}

case class TraceLogEvent(name: String, message: String, error: Option[Throwable]) extends LogEvent

case class DebugLogEvent(name: String, message: String, error: Option[Throwable]) extends LogEvent

case class InfoLogEvent(name: String, message: String, error: Option[Throwable]) extends LogEvent

case class WarnLogEvent(name: String, message: String, error: Option[Throwable]) extends LogEvent

case class ErrorLogEvent(name: String, message: String, error: Option[Throwable]) extends LogEvent
