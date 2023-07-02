/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.6.1/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */
group = "io.github.castmart"
version = "${System.getenv("JCOUNTRY_VERSION")}"

plugins {
    // Apply the java-library plugin for API and implementation separation.
    signing
    `java-library`
    `maven-publish`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    // api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    // implementation("com.google.guava:guava:31.1-jre")
    implementation("org.json:json:20230618")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
        showStandardStreams = true
    }
}

//////////////////////////////////////////
// Publish and signature configuration. //
//////////////////////////////////////////

java {
    withJavadocJar()
    withSourcesJar()
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("jcountry") {
                signing {
                    val signingKey: String = "${System.getenv("GPG_SIGNING_KEY")}"
                    val signingPassword: String = "${System.getenv("GPG_SIGNING_KEY_PASSWORD")}"
                    useInMemoryPgpKeys(signingKey, signingPassword)
                    sign(publishing.publications["jcountry"])
                }
                artifactId = "jcountry"
                from(components["java"])
                pom {
                    name.set("jcountry")
                    description.set("A java wrapper for the ISO country codes and translations inspired in pycountry")
                    url.set("https://github.com/castmart/jcountry")
                    licenses {
                        license {
                            name.set("GNU LESSER GENERAL PUBLIC LICENSE, Version 2.1")
                            url.set("https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html")
                        }
                    }
                    developers {
                        developer {
                            id.set("castmart")
                            name.set("Juan Carlos Castaneda Martinez")
                            email.set("jc.castmart@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/castmart/jcountry.git")
                        developerConnection.set("scm:git:https://github.com/castmart/jcountry.git")
                        url.set("https://github.com/castmart/jcountry")
                    }
                }
            }
        }

        repositories {
            maven {
//                url = uri(layout.buildDirectory.dir("repo"))
                name = "OSSRH"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = "${System.getenv("OSSRH_USER")}"
                    password = "${System.getenv("OSSRH_PASS")}"
                }
            }
        }
    }
}
