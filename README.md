## Weatherservice

Interactors: Application Specific Business Rules and Entities

Entities: Application Independent Data Entities

Boundaries: Processes that may Involve Side-Effects

Improvement Ideas:

- Create cross-project for consistency on a scalajs project.
- Cache weather request (partially set up)
- Add more request types
- Fully utilize written entities
- Use defined tapir endpoint for http request
- Clean up build.sbt and versioning in project/Dependencies.scala
- Do better

After starting the server, type this in your browser: http://127.0.0.1:9000/api/v0.1.0/weather-api/latitude/36.0/longitude/139.0

I tried to use only libraries I was unfamiliar with. I cheated by using Circe, but whatever.