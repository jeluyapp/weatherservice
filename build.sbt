import Dependencies._

ThisBuild / scalaVersion     := "3.1.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "weatherservice",
    libraryDependencies ++= Seq(
      org.scalameta.munit % Test,
      org.scalameta.`munit-scalacheck` % Test,

      com.softwaremill.sttp.client3.`armeria-backend-cats`,
      com.softwaremill.sttp.tapir.`tapir-armeria-server-cats`,
      com.softwaremill.sttp.tapir.`tapir-json-circe`,
      com.softwaremill.sttp.tapir.`tapir-sttp-client`,
      org.typelevel`cats-effect`,
      Dependencies.io.circe.`circe-core`
    )
  )