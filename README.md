Logging
=======

SLF4J Scala logging library.

An Scala logging library that uses the SLF4J API.

The library utilizes lazy vals and call-by-name to maximize efficiency.

For normal logging:

    class Service extends Logging {
        val greeting = "hello"
        log.info(s"greeting = $greeting")
        log.error("error", new RuntimeException("test"))
    }

For logging from an actor:

    class ServiceActor extends EventStreamLogging {
        val eventStream = context.system.eventStream
        log.info(s"greeting = $greeting")
        log.error("error", new RuntimeException("test"))
    }
