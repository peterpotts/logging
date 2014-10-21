package com.peterpotts.logging

/**
 * A logging trait that uses the name of the underlying class as the name of the SLF4J log.
 */
trait Logging {
  lazy val log: Logger = new SLF4JLogger(factory(getClass.getName))

  protected[this] def factory(name: String): org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(name)
}
