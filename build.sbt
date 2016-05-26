name := "sinch-ticketgen"
organization  := "io.buildo"
version := "0.1.0"
scalaVersion := "2.11.8"
bintrayOrganization := Some("buildo")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic"
).map(_ % "0.4.1")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

tutSettings

tutTargetDirectory := new java.io.File("docs")
