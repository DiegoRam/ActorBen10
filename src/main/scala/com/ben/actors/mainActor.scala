package com.ben.actors

import akka.actor.{Props, Actor}


import scala.concurrent.ExecutionContext

class MainActor(implicit ec: ExecutionContext) extends Actor {
  import MainActor._

  override def receive: Receive = {
    case StartUp => {
      context.actorOf(Props(new KainGreenActor()), "kainGreenChild")
    }
  }
}


object MainActor {
  case object StartUp
}
