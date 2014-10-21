package com.peterpotts.logging

/**
 * Call-by-name is used for the message parameter to improve performance.
 */
trait LevelLogger {
  def enabled: Boolean

  def apply(message: => String)

  def apply(message: => String, error: Throwable)
}

abstract class AbstractLevelLogger(val logger: org.slf4j.Logger) extends LevelLogger {
  self: SLF4JLevelLogger =>

  def apply(message: => String) {
    if (enabled) log(message)
  }

  def apply(message: => String, error: Throwable) {
    if (enabled) log(message, error)
  }
}

class TraceLevelLogger(logger: org.slf4j.Logger)
  extends AbstractLevelLogger(logger) with SLF4JTraceLogger

class DebugLevelLogger(logger: org.slf4j.Logger)
  extends AbstractLevelLogger(logger) with SLF4JDebugLogger

class InfoLevelLogger(logger: org.slf4j.Logger)
  extends AbstractLevelLogger(logger) with SLF4JInfoLogger

class WarnLevelLogger(logger: org.slf4j.Logger)
  extends AbstractLevelLogger(logger) with SLF4JWarnLogger

class ErrorLevelLogger(logger: org.slf4j.Logger)
  extends AbstractLevelLogger(logger) with SLF4JErrorLogger
