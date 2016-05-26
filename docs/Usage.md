# Usage

Here's an example of how to generate a ticket

First get an instance of `TicketGenerator`

```scala
import io.buildo.sinch.{ TicketGenerator, SignedTicket }
// import io.buildo.sinch.{TicketGenerator, SignedTicket}

val generator = TicketGenerator(
  applicationKey = "YOUR_APPLICATION_KEY",
  applicationSecret = "WU9VUl9BUFBMSUNBVElPTl9TRUNSRVQ="
)
// generator: io.buildo.sinch.TicketGenerator = TicketGenerator(YOUR_APPLICATION_KEY,WU9VUl9BUFBMSUNBVElPTl9TRUNSRVQ=)
```

then use the generator to generate a `SignedTicket`

```scala
val ticket = generator.generateTicket(username = "buildo")
// ticket: scala.util.Try[io.buildo.sinch.SignedTicket] = Success(SignedTicket(eyJhcHBsaWNhdGlvbktleSI6IllPVVJfQVBQTElDQVRJT05fS0VZIiwiaWRlbnRpdHkiOnsidHlwZSI6InVzZXJuYW1lIiwiZW5kcG9pbnQiOiJidWlsZG8ifSwiY3JlYXRlZCI6IjIwMTYtMDUtMjZUMTU6MTg6MTQuNzYyWiIsImV4cGlyZXNJbiI6ODY0MDB9:t6N5/EehSfAmbVMN1mo3AbgkWtMtL/rkbTSR+W4GmqE=))

ticket.map(_.userTicket)
// res0: scala.util.Try[String] = Success(eyJhcHBsaWNhdGlvbktleSI6IllPVVJfQVBQTElDQVRJT05fS0VZIiwiaWRlbnRpdHkiOnsidHlwZSI6InVzZXJuYW1lIiwiZW5kcG9pbnQiOiJidWlsZG8ifSwiY3JlYXRlZCI6IjIwMTYtMDUtMjZUMTU6MTg6MTQuNzYyWiIsImV4cGlyZXNJbiI6ODY0MDB9:t6N5/EehSfAmbVMN1mo3AbgkWtMtL/rkbTSR+W4GmqE=)
```
