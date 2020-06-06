package com.zaphod

import zio.kafka.consumer._
import zio.kafka.serde._
import zio._
import zio.duration._
import zio.console._
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import zio.blocking.Blocking
import zio.clock.Clock

object Deletion {
  def handle: (String, String) => ZIO[ZEnv, Nothing, Unit] = { case (k, v) =>
    putStrLn(s"Received message $k: $v")
  }
}

object Main extends App {
  val settings: ConsumerSettings =
    ConsumerSettings(List("localhost:9092"))
      .withGroupId("group")
      .withClientId("client")
      .withCloseTimeout(30.seconds) // default

  private val subscription = Subscription.topics("helloworld")

  private val consume = Consumer.consumeWith(settings, subscription, Serde.string, Serde.string) {
    Deletion.handle
  }

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    consume.foldM(
      e => console.putStrLn(s"Execution failed with: $e") as ExitCode.failure,
      _ => ZIO.succeed(ExitCode.success)
    )
}
