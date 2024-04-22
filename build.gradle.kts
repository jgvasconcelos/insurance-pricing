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
	implementation("org.springframework.boot:spring-boot-starter-validation:3.2.1")

	// HTTP
	implementation("org.apache.httpcomponents.client5:httpclient5")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")

	// Database
	implementation("org.postgresql:postgresql:42.6.2")
	implementation("org.flywaydb:flyway-core:10.11.1")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:10.11.1")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "mockito.core")
	}
	testImplementation("io.zonky.test:embedded-database-spring-test:2.3.0")
	testImplementation("io.zonky.test:embedded-postgres:2.0.4")
	testImplementation("org.flywaydb.flyway-test-extensions:flyway-spring-test:7.0.0")
}

sourceSets {
	create("componentTest") {
		java.srcDir(file("src/componentTest/java"))
		resources.srcDir(file("src/componentTest/resources"))
		compileClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations["testRuntimeClasspath"]
		runtimeClasspath += output + compileClasspath
	}
}

tasks.withType<ProcessResources>{
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.register<Test>("componentTest") {
	description = "Runs the component tests."
	group = "verification"
	testClassesDirs = sourceSets["componentTest"].output.classesDirs
	classpath = sourceSets["componentTest"].runtimeClasspath

	mustRunAfter(tasks["test"])
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy("componentTest")
}