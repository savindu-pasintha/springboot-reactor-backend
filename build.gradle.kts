plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springdoc.openapi-gradle-plugin") version "1.9.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {// for time use
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

// Email and Monitoring
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

// Retry and Security
	implementation("org.springframework.retry:spring-retry")

//  Security
//	implementation("org.springframework.boot:spring-boot-starter-security")

// JPA / Data
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Swagger
//	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
//	implementation("org.springdoc:springdoc-openapi-starter-webflux-api:2.5.0")
//	implementation ("io.springfox:springfox-boot-starter:3.0.0")
//	implementation ("io.springfox:springfox-swagger-ui:3.0.0")


// Common compile
	compileOnly("org.projectlombok:lombok")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")

// Runtime databases
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

// Development tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

// Annotation processing
	annotationProcessor("org.projectlombok:lombok")

// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.security:spring-security-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
