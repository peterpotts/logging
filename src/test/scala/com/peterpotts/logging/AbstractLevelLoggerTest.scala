package com.peterpotts.logging

import org.junit.runner.RunWith
import org.mockito.Mockito.{never, verify, when}
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class AbstractLevelLoggerTest extends WordSpec with Matchers with MockitoSugar {

  class Target {
    val logger = mock[org.slf4j.Logger]
    val target = new AbstractLevelLogger(logger) with SLF4JInfoLogger
  }

  "An abstract level logger" should {
    "log disabled" in {
      new Target {
        val body = Random.nextString(4)
        when(logger.isInfoEnabled) thenReturn false
        target(body)
        verify(logger, never()).info(body)
      }
    }

    "log enabled" in {
      new Target {
        val body = Random.nextString(4)
        when(logger.isInfoEnabled) thenReturn true
        target(body)
        verify(logger).info(body)
      }
    }

    "log exception enabled" in {
      new Target {
        val message = Random.nextString(4)
        val exception = new RuntimeException(Random.nextString(4))
        when(logger.isInfoEnabled) thenReturn true
        target(message, exception)
        verify(logger).info(message, exception)
      }
    }
  }
}
