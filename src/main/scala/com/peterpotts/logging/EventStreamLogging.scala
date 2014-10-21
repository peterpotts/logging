package com.peterpotts.logging

import akka.event.EventStream

/**
 * A logging trait that dispatches log events to an event stream.
 */
trait EventStreamLogging {
  val eventStream: EventStream
  lazy val log: Logger = new EventStreamLogger(eventStream, factory(getClass.getName))

  protected[this] def factory(name: String): org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(name)
}
