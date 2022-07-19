package weatherservice.entities

case class Coordinates(
    val latitude: Coordinates.Latitude,
    val longitude: Coordinates.Longitude
):

  def cachedForm: (String, String) = Coordinates.cachedForm(latitude, longitude)

object Coordinates:

  type Latitude = Double
  type Longitude = Double

  def cachedForm(latitude: Latitude, longitude: Longitude): (String, String) =
    (String.format("%.4f", latitude), String.format("%.4f", longitude))

