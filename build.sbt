ThisBuild / tlBaseVersion := "0.0"

ThisBuild / organization := "com.armanbilge"
ThisBuild / organizationName := "Arman Bilge"
ThisBuild / developers += tlGitHubDev("armanbilge", "Arman Bilge")
ThisBuild / startYear := Some(2023)

ThisBuild / crossScalaVersions := Seq("3.3.1")

ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.temurin("21"))
ThisBuild / tlJdkRelease := Some(11)

ThisBuild / scalacOptions ++= Seq("-new-syntax", "-no-indent", "-source:future")

lazy val root = tlCrossRootProject.aggregate(core, compilerPlugin)

lazy val compilerPlugin = project
  .in(file("compiler-plugin"))
  .settings(
    name := "meep-compiler-plugin",
    libraryDependencies ++= Seq(
      "org.scala-lang" %% "scala3-compiler" % scalaVersion.value
    )
  )

lazy val enableCompilerPlugin =
  scalacOptions += s"-Xplugin:${(compilerPlugin / Compile / packageBin).value}"

lazy val core = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .settings(
    name := "meep",
    enableCompilerPlugin
  )

lazy val tests = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .enablePlugins(NoPublishPlugin)
  .dependsOn(core)
  .settings(enableCompilerPlugin)
