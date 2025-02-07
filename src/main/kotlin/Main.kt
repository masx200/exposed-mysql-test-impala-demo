package  exposed_mysql_test.exposed_mysql_test;

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig

suspend fun main() {
    println(
        Database.connect(
            url = "jdbc:mysql://localhost:3306/hr",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "your_username",
            password = "your_password"
        )
    )
    val client = HttpClient(CIO)
    println("https://ktor.io/")
    client.use { client ->
        val response: HttpResponse = client.get("https://ktor.io/")
        println(response.status)
        println(response.headers)
        client.close()
    }

    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:impala://localhost:21050/hr;AuthMech=3;"
        driverClassName = "com.cloudera.impala.jdbc.Driver"
        username = "username"
        password = "password"
//        maximumPoolSize = 6
//        // as of version 0.46.0, if these options are set here, they do not need to be duplicated in DatabaseConfig
//        isReadOnly = false
//        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

// Gradle
//    implementation "mysql:mysql-connector-java:8.0.33"
//    implementation "com.zaxxer:HikariCP:4.0.3"
    val dataSource = HikariDataSource(config)
    println(
        Database.connect(
            datasource = dataSource,
            databaseConfig = DatabaseConfig {
                // set other parameters here
            }
        )
    )
}