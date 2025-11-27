import org.jreleaser.model.Active

plugins {
    id("java")
    id("maven-publish")
    id("org.jreleaser") version "1.20.0"
}

group = "dev.neovoxel.nsapi"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20250517")
    implementation("com.zaxxer:HikariCP:4.0.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.mysql:mysql-connector-j:8.2.0")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            pom {
                name = project.name
                description = "A library used to manage databases quickly"
                url = "https://github.com/NeoVoxelDev/NeoStorageAPI"
                inceptionYear = "2025"
                licenses {
                    license {
                        name = "LGPL-3.0-or-later"
                        url = "https://spdx.org/licenses/LGPL-3.0-or-later.html"
                    }
                }
                developers {
                    developer {
                        id = "aurelian2842"
                        name = "Aurelian2842"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/NeoVoxelDev/NeoStorageAPI.git"
                    developerConnection = "scm:git:ssh://github.com/NeoVoxelDev/NeoStorageAPI.git"
                    url = "http://github.com/NeoVoxelDev/NeoStorageAPI"
                }
            }
        }
    }

    repositories {
        maven {
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}

tasks.publish {
    dependsOn(tasks.named("publishMavenPublicationToMavenLocal"))
}

jreleaser {
    signing {
        active = Active.ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}