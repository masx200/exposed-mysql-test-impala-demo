import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

//import groovy.xml.dom.DOMCategory.attributes
//import org.jetbrains.kotlin.js.translate.context.Namer.kotlin
//import sun.jvmstat.monitor.MonitoredVmUtil.mainClass
//import sun.tools.jconsole.LabeledComponent.layout

plugins {
    kotlin("jvm") version "2.0.21"
    id("com.gradleup.shadow") version "8.3.5"
    id("application")
}
//dependencies {
//    implementation(files("lib/ImpalaJDBC41.jar"))
//
//    implementation(files("lib/ImpalaJDBC42-2.6.26.1031.jar"))
//}
group = "exposed-mysql-test"
version = "1.0-SNAPSHOT"
tasks.named<ShadowJar>("shadowJar") {
    isZip64 = true
    manifest {
        attributes["Main-Class"] = "exposed_mysql_test.exposed_mysql_test.MainKt"

    }
}
application {
    mainClass.set("exposed_mysql_test.exposed_mysql_test.MainKt")
}
repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://build.shibboleth.net/maven/releases/")
}
repositories {
    maven {
        url = uri("https://repository.cloudera.com/artifactory/cloudera-repos")
        name = "Cloudera Repositories"
        mavenContent {
            //snapshotsEnabled = false
        }
    }
//    maven(url = "https://nexus.dev.dtstack.cn/nexus/content/repositories/dtstack-release/")
}
dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation("org.jetbrains.exposed:exposed-core:0.58.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.58.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.58.0")
    implementation("mysql:mysql-connector-java:8.0.32")
    // https://mvnrepository.com/artifact/com.cloudera.impala.jdbc/ImpalaJDBC41
//    implementation("com.cloudera.impala.jdbc:ImpalaJDBC41:2.6.12"){
//        isTransitive = false
//    }

    // https://mvnrepository.com/artifact/Impala/ImpalaJDBC42
//    implementation("Impala:ImpalaJDBC42:2.6.26.1031"){
//        isTransitive = false
//    }
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-core
    implementation("io.ktor:ktor-client-core:3.0.3")
// https://mvnrepository.com/artifact/io.ktor/ktor-client-cio
    implementation("io.ktor:ktor-client-cio:3.0.3")

    //implementation("com.cloudera:ImpalaJDBC41:2.6.3")
// https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    implementation("com.zaxxer:HikariCP:6.2.1")

}

tasks.register<Copy>("copyDependencies", Copy::class) {

    val libsDir = layout.buildDirectory.dir("dependency")
    into(libsDir)
    from(configurations.runtimeClasspath)
}
tasks.distZip {
    finalizedBy("copyDependencies")
}
tasks.distTar {
    finalizedBy("copyDependencies")
}
tasks.jar {
    finalizedBy("copyDependencies")

}
tasks.build {
    finalizedBy("copyDependencies")
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.hive/hive-service
//    implementation("org.apache.hive:hive-service:4.0.1")
// https://mvnrepository.com/artifact/org.apache.hive/hive-metastore
//    implementation("org.apache.hive:hive-metastore:4.0.1")
// https://mvnrepository.com/artifact/org.pac4j/pac4j-saml-opensamlv3
    implementation("org.pac4j:pac4j-saml-opensamlv3:4.5.8")
// https://mvnrepository.com/artifact/net.shibboleth.tool/xmlsectool
    runtimeOnly("net.shibboleth.tool:xmlsectool:3.0.0")

//    implementation("com.cloudera.impala.jdbc:hive_metastore:2.5.30")
//    implementation("com.cloudera.impala.jdbc:hive_service:2.5.30")
//    implementation("com.cloudera.impala.jdbc:ql:2.5.30")
//    implementation("com.cloudera.impala.jdbc:TCLIServiceClient:2.5.30")
    implementation("org.apache.thrift:libfb303:0.9.0")
    implementation("org.apache.thrift:libthrift:0.9.0")
    implementation("log4j:log4j:1.2.14")
    implementation("org.slf4j:slf4j-api:1.5.11")
    implementation("org.slf4j:slf4j-log4j12:1.5.11")
    implementation("org.apache.zookeeper:zookeeper:3.4.6")


    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.0.16")

}

dependencies {

    implementation("Impala:ImpalaJDBC42:2.6.26.1031") {
        isTransitive = false
    }
}


dependencies {

    implementation("Impala:ImpalaJDBC41:2.6.12.1013") {
        isTransitive = false
    }
}