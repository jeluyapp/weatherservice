package weatherservice.entities

case class WeatherData(
    val weatherCondition: WeatherData.WeatherCondition,
    val tempAesthetic: WeatherData.TempAesthetic,
    val alerts: WeatherData.Alerts
):

  def cachedForm: (
      WeatherData.WeatherCondition,
      WeatherData.TempAesthetic,
      WeatherData.Alerts
  ) =
    WeatherData.cachedForm(weatherCondition, tempAesthetic, alerts)

object WeatherData:

  type WeatherCondition = String
  type TempAesthetic = String
  type Alerts = List[String]

  def cachedForm(
      weatherCondition: WeatherData.WeatherCondition,
      tempAesthetic: WeatherData.TempAesthetic,
      alerts: WeatherData.Alerts
  ): (
      WeatherData.WeatherCondition,
      WeatherData.TempAesthetic,
      WeatherData.Alerts
  ) =
    (weatherCondition, tempAesthetic, alerts)