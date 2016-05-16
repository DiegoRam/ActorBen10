package com.ben.actors

import java.util.Date

import akka.actor.{PoisonPill, ActorLogging, Actor}
import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.blocking
import akka.pattern.pipe
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by diegoram on 5/12/16.
  *
  */

case class User(name: String, accountId: Long)

object utils {
  def saveUser(user: User): Future[Boolean] = Future.successful(true)
  def heavyLifting(task: String): Future[Option[Int]] =
    Future {
      blocking {
        Thread.sleep(5000)
        Some(new java.util.Random().nextInt())
      }
    }
}

object KainGreenActor {
  case class SaveUser(user: User)
  case object KillMe
  case class HeavyLifting(task: String)
}


class KainGreenActor(implicit ec: ExecutionContext) extends Actor with ActorLogging{

  import KainGreenActor._

  override def receive: Receive = {
    case SaveUser(user) => {
      println("Begin save" +  new Date)
      utils.saveUser(user) pipeTo sender
    }
    case KillMe => self ! PoisonPill
    case HeavyLifting(name) => utils.heavyLifting(name) pipeTo sender
  }
}
