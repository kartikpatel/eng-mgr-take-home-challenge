import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	kotlin("plugin.jpa") version "1.5.21"
}

group = "com.spothero"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["testcontainersVersion"] = "1.16.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-ui:1.5.10")
    runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("io.kotest:kotest-assertions-core-jvm:4.6.2")
	testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()

	testLogging {
		events(PASSED, FAILED, STANDARD_ERROR, SKIPPED)
		exceptionFormat = FULL
		showExceptions = true
		showCauses = true
		showStackTraces = true
	}
}

tasks.test {
	useJUnitPlatform {
		excludeTags("integration-test", "component-test")
	}
}

val integrationTest = tasks.register<Test>("integrationTest") {
	useJUnitPlatform {
		includeTags("integration-test")
		excludeTags("unit-test", "component-test")
	}
}

val componentTest = tasks.register<Test>("componentTest") {
	useJUnitPlatform {
		includeTags("component-test")
		excludeTags("unit-test", "integration-test")
	}
}

tasks.check {
	dependsOn(integrationTest)
	dependsOn(componentTest)
}