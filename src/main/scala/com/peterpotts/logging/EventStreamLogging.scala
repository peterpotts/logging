package com.peterpotts.loggin

import akka.event.EventStream

/**
 * A logging trait that dispatches log events to an event stream.
 */
trait EventStreamLogging {
  val eventStream: EventStream
  lazy val log: Logger = new EventStreamLogger(eventStream, org.slf4j.LoggerFactory.getLogger(getClass))
}
