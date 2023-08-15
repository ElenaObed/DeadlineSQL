package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner queryRunner = new QueryRunner();
    private SQLHelper(){
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("dbMySQL.url"), "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode(){
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC Limit 1";
        var conn = getConn();
        var code = queryRunner.query(conn,codeSQL, new ScalarHandler<String>());
        return  new DataHelper.VerificationCode(code);
    }

    @SneakyThrows
    public static void cleanDataBase(){
        var connection = getConn();
        queryRunner.execute(connection, "DELETE FROM card_transactions");
        queryRunner.execute(connection, "DELETE FROM cards");
        queryRunner.execute(connection, "DELETE FROM auth_codes");
        queryRunner.execute(connection, "DELETE FROM users");
    }
}