import AssemblyKeys._ // put this at the top of the file
name := "Tutorial"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.0.0"

libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-core" % sparkVersion,
  "org.apache.spark"  %% "spark-streaming" % sparkVersion,
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.0"
)

libraryDependencies := libraryDependencies.value.map(_.exclude("org.glassfish.hk2", "hk2-locator"))

libraryDependencies := libraryDependencies.value.map(_.exclude("javax.validation", "validation-api"))


resourceDirectory in Compile := baseDirectory.value / "resources"

assemblySettings

resolvers ++= Seq(
  "JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
  "Spray Repository" at "http://repo.spray.cc/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Twitter4J Repository" at "http://twitter4j.org/maven2/",
  "Apache HBase" at "https://repository.apache.org/content/repositories/releases",
  "Twitter Maven Repo" at "http://maven.twttr.com/",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  Resolver.sonatypeRepo("public")
)

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}

mainClass in (Compile, run) := Some("Tutorial")
