package io.buildo

import java.util.Date
import java.nio.charset.StandardCharsets.UTF_8

import scala.util.Try
import scala.concurrent.duration._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

package object sinch {

  private case class UserIdentity(
    `type`: String,
    endpoint: String
  )

  private case class UserTicket(
    applicationKey: String,
    identity: UserIdentity,
    created: String,
    expiresIn: Long
  )

  private object Base64 {
    def encode(x: Array[Byte]): String = new String(java.util.Base64.getEncoder.encode(x), UTF_8)
    def encode(x: String): String = encode(x.getBytes(UTF_8))
    def decode(x: String): Array[Byte] = java.util.Base64.getDecoder.decode(x.getBytes(UTF_8))
  }

  private def toISOString(date: Date): String = {
    val utc = java.util.TimeZone.getTimeZone("UTC");
    val df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    df.setTimeZone(utc);
    df.format(date)
  }

	private def sign(secret: String, message: String): String = {
     val sha256HMAC = javax.crypto.Mac.getInstance("HmacSHA256")
     val secretKey = new javax.crypto.spec.SecretKeySpec(Base64.decode(secret), "HmacSHA256")
     sha256HMAC.init(secretKey);
     Base64.encode(sha256HMAC.doFinal(message.getBytes(UTF_8)))
	}

  case class SignedTicket(userTicket: String)

  case class TicketGenerator private (applicationKey: String, applicationSecret: String) {

   /** Generates a SignedTicket valid for authenticating with a Sinch app
     *
     * @param username any unique identified of a user, custom to your application
     * @param creationDate the ticket creation date. Defaults to the current date
     * @param expiresIn the duration of ticket validity. Defaults to 1 day
     *
     * @return a new SignedTicket instance, wrapped in a Try
     */
    def generateTicket(username: String, creationDate: Date = new Date, expiresIn: Duration = 1.day): Try[SignedTicket] = {
      Try {
        val userTicket = UserTicket(
          applicationKey = applicationKey,
          identity = UserIdentity(`type` = "username", endpoint = username),
          created = toISOString(creationDate),
          expiresIn = expiresIn.toSeconds
        )
        val userTicketJSON = userTicket.asJson.noSpaces
        val userTicketBase64 = Base64.encode(userTicketJSON)
        val signature = sign(applicationSecret, userTicketJSON)

        SignedTicket(userTicket = s"$userTicketBase64:$signature")
      }
    }
  }

}
