package  exposed_mysql_test.exposed_mysql_test;

import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.Test

class ExposedMysqlTest {
    @Test
    fun testmysql() {
        println(
            Database.connect(
                url = "jdbc:mysql://localhost:3306/hr",
                driver = "com.mysql.cj.jdbc.Driver",
                user = "your_username",
                password = "your_password"
            )
        )
    }
//    @Test
//    fun testimpala() {
//        println(
//            Database.connect(
//                url = "jdbc:impala://localhost:21058/hr;AuthMech=3;",
//                driver = "com.cloudera.impala.jdbc.Driver",
//                user = "your_username",
//                password = "your_password"
//            )
//        )
//    }
}


