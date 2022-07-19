package weatherservice.interactors.endpoints

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._

import weatherservice.entities.*

case class ErrorInfo(message: String)

class WeatherEndpoints(base: String = "weather-api"):

  val baseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    endpoint
      .in("api" / "v0.1.0")
      .errorOut(jsonBody[ErrorInfo])

  val weatherServiceEndpoint: PublicEndpoint[
    (Coordinates.Latitude, Coordinates.Longitude),
    ErrorInfo,
    Option[WeatherData],
    Any
  ] =
    baseEndpoint
      .in(
        base / "latitude" / path[Coordinates.Latitude](
          "latitude"
        ) / "longitude" / path[Coordinates.Longitude]("longitude")
      )
      .out(jsonBody[Option[WeatherData]])
      .description("Retrieve Weather Data w/ Latitude & Longitude")