package weatherservice.entities

case class Wind(
    val speed: Wind.Speed,
    val degrees: Wind.Degrees,
    val gust: Wind.Gust
):

  def cachedForm: (Wind.Speed, Wind.Degrees, Wind.Gust) =
    Wind.cachedForm(speed, degrees, gust)

object Wind:

  type Speed = Double
  type Degrees = Int
  type Gust = Int

  def cachedForm(
      speed: Speed,
      degrees: Degrees,
      gust: Gust
  ): (Wind.Speed, Wind.Degrees, Wind.Gust) =
    (speed, degrees, gust)


