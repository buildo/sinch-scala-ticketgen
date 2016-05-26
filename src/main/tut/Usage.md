# Usage

Here's an example of how to generate a ticket

First get an instance of `TicketGenerator`

```tut:book
import io.buildo.sinch.{ TicketGenerator, SignedTicket }

val generator = TicketGenerator(
  applicationKey = "YOUR_APPLICATION_KEY",
  applicationSecret = "WU9VUl9BUFBMSUNBVElPTl9TRUNSRVQ="
)
```

then use the generator to generate a `SignedTicket`

```tut:book
val ticket = generator.generateTicket(username = "buildo")

ticket.map(_.userTicket)

```
