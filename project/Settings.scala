import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

/**
 * Application settings. Configure the build for your application here.
 * You normally don't have to touch the actual build definition after this.
 */
object Settings {
  val organization = "net.devkat"
  val name = "timetracking"
  val version = "0.0.1-SNAPSHOT"

  /** Options for the scala compiler */
  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object versions {
    val scala = "2.11.8"
    val scalaDom = "0.9.1"
    val scalajsReact = "0.11.1"
    val scalajsReactBridge = "0.2.0-SNAPSHOT"
    val scalaJsScripts = "1.1.0"
    val scalaCSS = "0.4.1"
    val log4js = "1.4.10"
    val autowire = "0.2.5"
    val booPickle = "1.2.4"
    val diode = "1.1.1"
    val uTest = "0.4.3"
    val uPickle = "0.4.1"
    val akkaHttp = "10.0.0"

    val react = "15.1.0"
    val jQuery = "3.1.1"
    val bootstrap = "4.0.0-alpha.6-1"
    val tether = "1.4.0"
  }

  object dependencies {
    val bootstrap = "org.webjars" % "bootstrap" % versions.bootstrap
  }

  /**
   * These dependencies are shared between JS and JVM projects
   * the special %%% function selects the correct version for each project
   */
  val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "autowire" % versions.autowire,
    "me.chrons" %%% "boopickle" % versions.booPickle
  ))

  /** Dependencies only used by the JVM project */
  val serverDependencies = Def.setting(Seq(
    "com.typesafe.akka" %% "akka-http" % versions.akkaHttp,
    "com.vmunier" %% "scalajs-scripts" % versions.scalaJsScripts,
    "com.lihaoyi" %% "utest" % versions.uTest % Test
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val clientDependencies = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
    "com.github.japgolly.scalacss" %%% "ext-react" % versions.scalaCSS,
    "io.suzaku" %%% "diode" % versions.diode,
    "io.suzaku" %%% "diode-react" % versions.diode,
    "org.scala-js" %%% "scalajs-dom" % versions.scalaDom,
    "com.lihaoyi" %%% "utest" % versions.uTest % Test,
    "com.lihaoyi" %%% "upickle" % versions.uPickle,
    dependencies.bootstrap exclude("org.webjars", "jquery")
  ))

  /** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
    "org.webjars.bower" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    "org.webjars.bower" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
    "org.webjars" % "jquery" % versions.jQuery / s"${versions.jQuery}/jquery.js" minified "jquery.min.js",
    "org.webjars.bower" % "tether" % versions.tether / "dist/js/tether.js" minified "dist/js/tether.min.js",
    dependencies.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn(s"${versions.jQuery}/jquery.js", "dist/js/tether.js"),
    "org.webjars" % "log4javascript" % versions.log4js / "js/log4javascript_uncompressed.js" minified "js/log4javascript.js"
  ))
}
