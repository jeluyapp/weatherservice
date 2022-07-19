package weatherservice.entities

case class Weather(
    id: Weather.Id,
    main: Weather.Condition,
    description: Weather.Description,
    icon: Weather.Icon
):

  def cachedForm
      : (Weather.Id, Weather.Condition, Weather.Description, Weather.Icon) =
    Weather.cachedForm(id, main, description, icon)

object Weather:
  type Id = Int
  type Condition = String
  type Description = String
  type Icon = String

  def cachedForm(
      id: Weather.Id,
      main: Weather.Condition,
      description: Weather.Description,
      icon: Icon
  ): (Weather.Id, Weather.Condition, Weather.Description, Weather.Icon) =
    (id, main, description, icon)