ThisBuild / tlBaseVersion := "0.0"

ThisBuild / organization := "com.armanbilge"
ThisBuild / organizationName := "Arman Bilge"
ThisBuild / developers += tlGitHubDev("armanbilge", "Arman Bilge")
ThisBuild / startYear := Some(2024)

ThisBuild / crossScalaVersions := Seq("3.3.4")

ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.temurin("21"))
ThisBuild / tlJdkRelease := Some(11)

ThisBuild / scalacOptions ++= Seq("-new-syntax", "-no-indent", "-source:future")

lazy val root = tlCrossRootProject.aggregate(runtime)

lazy val runtime = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .settings(
    name := "meep-meep-runtime"
  )
