package com.Application2.in

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class UserPerformance extends Simulation {

  // Define the base URL for the application
  val baseURL = "http://localhost:8080" // Modify to match your server's address

  // Define the HTTP protocol
  val httpProtocol = http
    .baseUrl(baseURL) // Base URL for the application
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  // Define the scenario for testing the REST Template endpoint
  val getUserByIdRestTemplateScenario = scenario("Get User By ID with REST Template")
    .exec(http("Get User By ID using REST Template")
      .get("/getUserRestTemplate/1") // Replace with an actual user ID that exists
      .check(status.is(200))
      .check(jsonPath("$.id").saveAs("userId")) // Save userId from response
    )
    .exec(session => {
      val userId = session("userId").as[String]
      println(s"User ID: $userId") // Log the user ID from the response
      session
    })

  // Define the scenario for testing the Feign endpoint
  val getUserByIdFeignScenario = scenario("Get User By ID with Feign")
    .exec(http("Get User By ID using Feign")
      .get("/getUserFeign/1") // Replace with an actual user ID that exists
      .check(status.is(200))
      .check(jsonPath("$.id").saveAs("userId")) // Save userId from response
    )
    .exec(session => {
      val userId = session("userId").as[String]
      println(s"User ID: $userId") // Log the user ID from the response
      session
    })

  // Set up the load simulation
  setUp(
    getUserByIdRestTemplateScenario.inject(atOnceUsers(10)), // Inject 10 users for the REST Template scenario
    getUserByIdFeignScenario.inject(atOnceUsers(10))         // Inject 10 users for the Feign scenario
  ).protocols(httpProtocol)

  // Optional: You can also define more advanced load strategies here (e.g., rampUsers, constantUsersPerSec, etc.)
}
