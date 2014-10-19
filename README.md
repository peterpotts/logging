Sample
======

Sample is a monadic random data generator.

Description
-----------

In a typical application, there is a need to generator sample data for unit tests. Usually, the sample data is
arbitrary, simplistic and worse still it is usually brittle. For example, suppose that many tests rely on sample
instances of some case class. If the case class is ever modified, then many tests may have to be modified, even
though this change should have had no direct impact on most of the affected tests.

Usage
-----

Consider an example case class for a person entity:

    case class Person(
        id: UUID,
        name: String,
        age: Int,
        email: Option[String],
        friends: Seq[String])

A typical definition of a sample person generator is:

    val SamplePerson = for {
        id <- SampleUUID
        name <- SampleString(3 to 8).map("Mr. " + _)
        age <- SampleInt(50) if age > 18
        email <- SampleOption(SampleString)
        friends <- SampleSeq(SampleString, 0 to 10)
    } yield Person(id, name, age, email, friends)

A sample person is generated using the next method:

    val person: Person = SamplePerson.next()

A sample sequence of persons with distinct names is generated like this:

    val persons: Seq[Person] = SampleSeq(SamplePerson.distinctBy(_.name)).next()
