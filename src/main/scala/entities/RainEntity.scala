package weatherservice.entities

case class Rain(
    val `1h`: Rain.`1H`
):

  def cachedForm: Rain.`1H` = Rain.cachedForm(`1h`)

object Rain:

  type `1H` = Double

  def cachedForm(`1h`: Rain.`1H`): Rain.`1H` =
    `1h`
