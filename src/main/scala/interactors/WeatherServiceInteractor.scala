package weatherservice.interactors

import io.circe.*, io.circe.parser.*
import weatherservice.entities.*

class WeatherServiceRequest(latitude: Double, longitude: Double):
  def lat: Double = latitude
  def lon: Double = longitude

class UniversalRequest(latitude: Double, longitude: Double)
    extends WeatherServiceRequest(latitude, longitude)

class WeatherServiceRequestResult(req: String)

class UniversalRequestResult(req: String)
    extends WeatherServiceRequestResult(req):

  private def tempToAesthetic(temp: Double): String =
    if (temp > 299.8167) "HOT!!!"
    else if (temp < 283.15) "COLD!!!"
    else "PERFECT!!!"

  def getWeatherData: Option[weatherservice.entities.WeatherData] =
    parse(req).toOption.flatMap { json =>
      val weatherCondition =
        json.hcursor
          .downField("weather")
          .downArray
          .get[String]("description")
          .toOption
      val temp =
        json.hcursor.downField("main").get[Double]("feels_like").toOption

      scala.util.Try {
        WeatherData(
          weatherCondition.get,
          tempToAesthetic(temp.get),
          if (temp.get > 310) List("Heat Warning")
          else List.empty
        )
      }.toOption
    }

class WeatherServiceRequestError(req: String)

class UniversalRequestError(req: String) extends WeatherServiceRequestError(req)