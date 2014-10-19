package com.peterpotts.logging

trait SLF4JLevelLogger {
  val logger: org.slf4j.Logger

  def enabled: Boolean

  def log(message: String)

  def log(message: String, error: Throwable)
}

trait SLF4JTraceLogger extends SLF4JLevelLogger {
  def enabled = logger.isTraceEnabled

  def log(message: String) = logger.trace(message)

  def log(message: String, error: Throwable) = logger.trace(message, error)
}

trait SLF4JDebugLogger extends SLF4JLevelLogger {
  def enabled = logger.isDebugEnabled

  def log(message: String) = logger.debug(message)

  def log(message: String, error: Throwable) = logger.debug(message, error)
}

trait SLF4JInfoLogger extends SLF4JLevelLogger {
  def enabled = logger.isInfoEnabled

  def log(message: String) = logger.info(message)

  def log(message: String, error: Throwable) = logger.info(message, error)
}

trait SLF4JWarnLogger extends SLF4JLevelLogger {
  def enabled = logger.isWarnEnabled

  def log(message: String) = logger.warn(message)

  def log(message: String, error: Throwable) = logger.warn(message, error)
}

trait SLF4JErrorLogger extends SLF4JLevelLogger {
  def enabled = logger.isErrorEnabled

  def log(message: String) = logger.error(message)

  def log(message: String, error: Throwable) = logger.error(message, error)
}
