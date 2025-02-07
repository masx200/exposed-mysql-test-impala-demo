package exposed_mysql_test.exposed_mysql_test

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.Test

class ExposedIMPALATest {
    @Test
    fun testimpala() {
        var jdbcUrl = "jdbc:impala://localhost:21050/databaseName;AuthMech=3;"
        var config = HikariConfig()
        config.apply {
            username = "username"
            password = "password"
        }
        val driverClassName = "com.cloudera.impala.jdbc41.Driver"
        config.setDriverClassName(driverClassName)
        config.setJdbcUrl(jdbcUrl)
        var hikariDataSource = HikariDataSource(config)
        hikariDataSource.getConnection().use { connection ->
            println(
                connection
            )
        }

    }
}
