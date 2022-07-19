package weatherservice.entities

case class Cloud(
    val all: Cloud.All
):

  def cachedForm: Cloud.All = Cloud.cachedForm(all)

object Cloud:

  type All = Int

  def cachedForm(all: Cloud.All): All =
    all
