package akka

import akka.actor._
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.util.Timeout

object Akka extends App {

  val system = ActorSystem("mysystem")
  val admin = system.actorOf(Props[Admin], "admin")

  implicit val timeout = Timeout(5 seconds)
  val future = admin ? "start"
  val result = Await.result(future, 3 minutes).asInstanceOf[String]

  result match {
    case "started" => println("Admin actor started")
  }

  class Admin extends Actor {

    def receive = {
      case "start" => {

        val receiver = context.actorOf(Props[Receiver], "receiver")
        val localSender = context.actorOf(Props(new Sender(receiver)), "sender")

        sender ! "started"
        localSender ! "msg"
      }
    }
  }

  class Sender(val receiver: ActorRef) extends Actor {
    def receive = {
      case "ack" => println("Received ack")
    }
  }

  class Receiver extends Actor {
    def receive = {
      case "msg" => sender ! "ack"
    }
  }

}
