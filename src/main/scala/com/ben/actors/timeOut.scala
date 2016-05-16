package com.ben.actors

import java.util.concurrent.TimeUnit

import akka.util.Timeout
import scala.concurrent.duration.FiniteDuration

/**
  * Created by diegoram on 5/12/16.
  */
trait DefaultTimeOut {

  implicit val timeOut: Timeout = FiniteDuration(5, TimeUnit.SECONDS)

}
