package com.peterpotts.logging

import java.io.PrintWriter

import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class JSONLevelLoggerTest extends WordSpec with Matchers with MockitoSugar {

  class Target {
    val logger = mock[org.slf4j.Logger]
    val target = new JSONLevelLogger(logger) with SLF4JInfoLogger
  }

  "A JSON level logger" should {
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
        val body = Random.nextString(4)
        val trace = Random.nextString(4)
        when(logger.isInfoEnabled) thenReturn true

        val exception = new RuntimeException("test") {
          override def printStackTrace(writer: PrintWriter) {
            writer.print(trace)
          }
        }

        target(body, exception)
        verify(logger).info(body, exception)
      }
    }
  }
}
