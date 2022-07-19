import sbt._

object Dependencies {

  val munitVersion = "1.0.0-M6"
  val armeriaClientVersion = "3.7.0"
  val tapirVersion = "1.0.1"
  val circeVersion = "0.14.2"
  val catsVersion = "3.3.14"

  object org {
    object scalameta {
      lazy val munit = "org.scalameta" %% "munit" % munitVersion
      lazy val `munit-scalacheck` = "org.scalameta" %% "munit-scalacheck" % munitVersion
    }

    object typelevel {
      lazy val `cats-effect` = "org.typelevel" %% "cats-effect" % catsVersion
    }
  } 

  object com {
    object softwaremill {
      object sttp {
        object client3 {
          lazy val `armeria-backend-cats` = "com.softwaremill.sttp.client3" %% "armeria-backend-cats" % armeriaClientVersion
        }

        object tapir {
          lazy val `tapir-armeria-server-cats` = "com.softwaremill.sttp.tapir" %% "tapir-armeria-server-cats" % tapirVersion
          lazy val `tapir-json-circe` = "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion
          lazy val `tapir-sttp-client` = "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % tapirVersion
        }
      }
    }
  }

  object io {
    object circe {
      lazy val `circe-core` = "io.circe" %% "circe-core" % circeVersion
    }
  }
}

