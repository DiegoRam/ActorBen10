package com.ben

import java.util.concurrent.TimeUnit
import akka.actor.{Props, ActorSystem}
import com.ben.actors.{MainActor, DefaultTimeOut, User, KainGreenActor}
import akka.pattern.ask
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

/**
  * Created by diegoram on 5/12/16.
  */
object BenApp extends App with DefaultTimeOut {


  override def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("benApp")
    implicit val ec = actorSystem.dispatcher

    //val myBenMailBox = actorSystem.actorOf(Props(new KainGreenActor()), "benActor")

    val mainActor = actorSystem.actorOf(Props(new MainActor()), "mainActor")
    mainActor ! MainActor.StartUp
    val myBenMailBox2 = actorSystem.actorSelection("user/mainActor/kainGreenChild")

    import KainGreenActor._

    val fheavyComp = (myBenMailBox2 ? HeavyLifting("Sebas")).mapTo[Option[Int]]
    val fSaveUser = myBenMailBox2 ? SaveUser(User("Sebas", 1L))

    fheavyComp.onComplete{
      case Success(optInt) => println("result: " + optInt.get)
      case Failure(ex) => println("failed!",ex)
    }

    fSaveUser.onComplete {
      case Success(isSaved) => println("Save User: Ok")
      case Failure(ex) => println("failed!",ex)
    }

    /*actorSystem.scheduler.schedule(
      Duration.Zero,
      Duration.create(2, TimeUnit.SECONDS),
      myBenMailBox,
      SaveUser(User("BENITEZ", 2L))
    )*/
  }
}
