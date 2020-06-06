package com.zaphod

import zio.kafka.consumer.{Consumer, ConsumerSettings, Subscription}
import zio.kafka.serde.Serde
import zio.{ExitCode, ZIO, console}

class Kafka(val topic: String) {
  // consumer-ID  ??
  // client-ID    ??

  val settings: ConsumerSettings =
    ConsumerSettings(List("localhost:9092"))
      .withGroupId("group")
  //      .withClientId("client")
  //      .withCloseTimeout(30.seconds) // default

  val subscription = Subscription.topics("topic")
  Consumer.consumeWith(settings, subscription, Serde.string, Serde.string) { case (key, value) =>
    console.putStrLn(s"Received message ${key}: ${value}")
    // Perform an effect with the received message
  }
}
