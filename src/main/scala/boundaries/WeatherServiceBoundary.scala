package weatherservice.boundaries

import cats.effect.IO
import sttp.client3.*

import weatherservice.interactors.*

abstract class WeatherServiceEntity(
    requests: cats.data.NonEmptyList[WeatherServiceRequest]
):

  def call: IO[
    List[
      Response[Either[WeatherServiceRequestError, WeatherServiceRequestResult]]
    ]
  ]

  def addRequest(request: WeatherServiceRequest): WeatherServiceEntity

// Implementation for Openweather API
class OpenWeatherServiceEntity(
    requests: cats.data.NonEmptyList[WeatherServiceRequest]
) extends WeatherServiceEntity(requests):

  def call: IO[
    List[
      Response[Either[WeatherServiceRequestError, WeatherServiceRequestResult]]
    ]
  ] = ???

  def basicCall: IO[Response[Either[String, String]]] =
    val request = this.requests.head
    basicRequest
      .get(
        uri"/data/2.5/weather?lat=${request.lat}&lon=${request.lon}&appid=d9771dc0e8b230f917684a23339132e8"
      )
      .send(OpenWeatherServiceEntity.backend)

  def addRequest(request: WeatherServiceRequest): WeatherServiceEntity =
    OpenWeatherServiceEntity(this.requests :+ request)

object OpenWeatherServiceEntity:

  import com.linecorp.armeria.client.WebClient
  import sttp.tapir.*, sttp.tapir.generic.auto.*, sttp.tapir.json.circe.*
  import io.circe.generic.auto.*

  case class ErrorInfo(message: String)
  case class WeatherServiceQuery(
    @EndpointIO.annotations.query lat: Double,
    @EndpointIO.annotations.query lon: Double,
    @EndpointIO.annotations.apikey
    @EndpointIO.annotations.query appid: String,
    @EndpointIO.annotations.query exclude: List[String],
    @EndpointIO.annotations.query unit: Option[String],
    @EndpointIO.annotations.query lang: Option[String]
  )

  val weatherQuery: EndpointInput[WeatherServiceQuery] =
    query[Double]("lat")
      .and(query[Double]("lon"))
      .and(query[String]("appid"))
      .and(query[List[String]]("exclude"))
      .and(query[Option[String]]("unit"))
      .and(query[Option[String]]("lang"))
      .mapTo[WeatherServiceQuery]

  val baseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    endpoint
      .in("data" / "2.5")
      .errorOut(jsonBody[ErrorInfo])

  val weatherServiceEndpoint: PublicEndpoint[
    WeatherServiceQuery,
    ErrorInfo,
    String,
    Any
  ] =
    baseEndpoint
      .in("weather")
      .in(weatherQuery)
      .out(jsonBody[String])
      .description("Retrieve Weather Data w/ Latitude & Longitude")

  val client = WebClient
    .builder("https://api.openweathermap.org")
    .build()

  val backend = armeria.cats.ArmeriaCatsBackend.usingClient[IO](client)

