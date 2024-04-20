plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.flywaydb.flyway") version "10.11.1"
}

group = "com.jgvasconcelos"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	// Spring Dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation:3.2.1")

	// Database
	runtimeOnly("org.postgresql:postgresql:42.6.2")
	implementation("org.flywaydb:flyway-core:10.11.1")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:10.11.1")

	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
