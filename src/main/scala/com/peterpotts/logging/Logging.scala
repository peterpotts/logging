package com.peterpotts.logging

/**
 * A logging trait that uses the name of the underlying class as the name of the SLF4J log.
 */
trait Logging {
  lazy val log: Logger = new SLF4JLogger(org.slf4j.LoggerFactory.getLogger(getClass))
}
