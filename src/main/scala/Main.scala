package weatherservice

import sttp.tapir.server.armeria.cats.ArmeriaCatsServerInterpreter
import com.linecorp.armeria.server.Server
import cats.effect.*

import weatherservice.interactors.endpoints.*
import weatherservice.interactors.*
import weatherservice.boundaries.*
import weatherservice.entities.*

object Main extends IOApp:

  private val endpoints = WeatherEndpoints("weather-api")
  private val port = 9000

  override def run(args: List[String]): IO[ExitCode] =
    val _ = args

    def weatherServiceEndpoint(
        req: (
            Coordinates.Latitude,
            Coordinates.Longitude
        )
    ): IO[Either[ErrorInfo, Option[WeatherData]]] =
      OpenWeatherServiceEntity(
        cats.data.NonEmptyList.one(UniversalRequest(req._1, req._2))
      ).basicCall.map(_.body match {
        case Left(error) => Left(ErrorInfo(error))
        case Right(data) => Right(UniversalRequestResult(data).getWeatherData)
      })

    std.Dispatcher[IO]
      .flatMap { dispatcher =>
        Resource
          .make(
            IO.async_[Server] { cb =>
              val weatherService = ArmeriaCatsServerInterpreter[IO](dispatcher)
                .toService(
                  endpoints.weatherServiceEndpoint
                    .serverLogic(weatherServiceEndpoint)
                )

              val server = Server
                .builder()
                .http(9000)
                .service(weatherService)
                .build()

              server.start().handle[Unit] {
                case (_, null)  => cb(Right(server))
                case (_, cause) => cb(Left(cause))
              }
            }
          )(server => IO.fromCompletableFuture(IO(server.closeAsync())).void)
      }
      .use(_ => IO.never)
