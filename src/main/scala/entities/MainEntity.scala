package weatherservice.entities

case class Main(
    temp: Main.Temp,
    feelsLike: Main.FeelsLike,
    tempMin: Main.TempMin,
    tempMax: Main.TempMax,
    pressure: Main.Pressure,
    humidity: Main.Humidity
):

  def cachedForm: (
      Main.Temp,
      Main.FeelsLike,
      Main.TempMin,
      Main.TempMin,
      Main.Pressure,
      Main.Humidity
  ) =
    Main.cachedForm(temp, feelsLike, tempMin, tempMax, pressure, humidity)

object Main:

  type Temp = Double
  type FeelsLike = Double
  type TempMin = Double
  type TempMax = Double
  type Pressure = Int
  type Humidity = Int

  def cachedForm(
      temp: Main.Temp,
      feelsLike: Main.FeelsLike,
      tempMin: Main.TempMin,
      tempMax: Main.TempMax,
      pressure: Main.Pressure,
      humidity: Main.Humidity
  ): (
      Main.Temp,
      Main.FeelsLike,
      Main.TempMin,
      Main.TempMin,
      Main.Pressure,
      Main.Humidity
  ) =
    (temp, feelsLike, tempMin, tempMax, pressure, humidity)

