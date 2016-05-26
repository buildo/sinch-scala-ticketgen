name := "SinchPOC"

scalaVersion := "2.11.8"

val circeVersion = "0.4.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic"
).map(_ % circeVersion)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
)

tutSettings

tutTargetDirectory := new java.io.File("docs")
