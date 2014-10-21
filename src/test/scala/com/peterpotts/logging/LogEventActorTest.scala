package com.peterpotts.logging

import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, TestKit}
import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class LogEventActorTest extends WordSpec with Matchers with MockitoSugar {

  class Target extends TestKit(ActorSystem("LogEventActorTest")) {
    val logger = mock[org.slf4j.Logger]
    val actor = TestActorRef(new LogEventActor {
      override protected[this] def factory(name: String) = logger
    })

    val logging = new EventStreamLogging {
      override protected[this] def factory(name: String) = logger

      val eventStream = system.eventStream
    }
  }

  def withFixture(test: Target => Any) {
    val target = new Target
    try test(target) finally TestKit.shutdownActorSystem(target.system)
  }

  "A log event actor" should {
    "trace message" in withFixture {
      target =>
        val message = Random.nextString(4)
        when(target.logger.isTraceEnabled) thenReturn true
        target.logging.log.trace(message)
        verify(target.logger).trace(message)
    }

    "trace message and exception" in withFixture {
      target =>
        val message = Random.nextString(4)
        val exception = new RuntimeException(Random.nextString(4))
        when(target.logger.isTraceEnabled) thenReturn true
        target.logging.log.trace(message, exception)
        verify(target.logger).trace(message, exception)
    }

    "debug message" in withFixture {
      target =>
        val message = Random.nextString(4)
        when(target.logger.isDebugEnabled) thenReturn true
        target.logging.log.debug(message)
        verify(target.logger).debug(message)
    }

    "debug message and exception" in withFixture {
      target =>
        val message = Random.nextString(4)
        val exception = new RuntimeException(Random.nextString(4))
        when(target.logger.isDebugEnabled) thenReturn true
        target.logging.log.debug(message, exception)
        verify(target.logger).debug(message, exception)
    }

    "info message" in withFixture {
      target =>
        val message = Random.nextString(4)
        when(target.logger.isInfoEnabled) thenReturn true
        target.logging.log.info(message)
        verify(target.logger).info(message)
    }

    "info message and exception" in withFixture {
      target =>
        val message = Random.nextString(4)
        val exception = new RuntimeException(Random.nextString(4))
        when(target.logger.isInfoEnabled) thenReturn true
        target.logging.log.info(message, exception)
        verify(target.logger).info(message, exception)
    }

    "warn message" in withFixture {
      target =>
        val message = Random.nextString(4)
        when(target.logger.isWarnEnabled) thenReturn true
        target.logging.log.warn(message)
        verify(target.logger).warn(message)
    }

    "warn message and exception" in withFixture {
      target =>
        val message = Random.nextString(4)
        val exception = new RuntimeException(Random.nextString(4))
        when(target.logger.isWarnEnabled) thenReturn true
        target.logging.log.warn(message, exception)
        verify(target.logger).warn(message, exception)
    }

    "error message" in withFixture {
      target =>
        val message = Random.nextString(4)
        when(target.logger.isErrorEnabled) thenReturn true
        target.logging.log.error(message)
        verify(target.logger).error(message)
    }

    "error message and exception" in withFixture {
      target =>
        val message = Random.nextString(4)
        val exception = new RuntimeException(Random.nextString(4))
        when(target.logger.isErrorEnabled) thenReturn true
        target.logging.log.error(message, exception)
        verify(target.logger).error(message, exception)
    }
  }
}

