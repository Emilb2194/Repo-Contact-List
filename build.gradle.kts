plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.1")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.12.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.19.0")
    implementation("io.cucumber:cucumber-java:7.19.0")
    implementation("net.masterthought:cucumber-reporting:5.8.1")
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")
    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("com.github.ozlerhakan:poiji:4.7.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("org.seleniumhq.selenium:selenium-java:4.34.0")
    implementation("com.sun.mail:javax.mail:1.6.2")
}

tasks.test {
    useJUnitPlatform()
}