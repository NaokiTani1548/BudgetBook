buildscript {
	dependencies {
		classpath("org.flywaydb:flyway-database-postgresql:12.2.0")
		classpath("org.postgresql:postgresql:42.7.4")
	}
}

plugins {
	java
	id("org.springframework.boot") version "4.0.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "12.2.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	create("mybatisGenerator")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")

	// Security + OAuth2（Google認証用）
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

	// JWT（トークン認証用、後で使う）
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

	// MyBatis
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:4.0.0")

	// Flyway + PostgreSQL
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")

	// PostgreSQL Driver
	runtimeOnly("org.postgresql:postgresql")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
	testRuntimeOnly("com.h2database:h2")  // テスト用インメモリDB
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	"mybatisGenerator"("org.mybatis.generator:mybatis-generator-core:1.4.2")
	"mybatisGenerator"("org.postgresql:postgresql:42.7.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<JavaExec>("mbGenerate") {
	mainClass.set("org.mybatis.generator.api.ShellRunner")
	classpath = configurations["mybatisGenerator"]
	args = listOf("-configfile", "src/main/resources/mybatis-generator-config.xml", "-overwrite")
}

flyway {
	url = "jdbc:postgresql://localhost:5432/budget_book_db"
	user = "budget_book_user"
	password = "budget_book_pass"
	locations = arrayOf("classpath:db/migration")
}