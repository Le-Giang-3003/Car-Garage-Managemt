/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author legiang300304
 */
public class DBUtils {
    private static final String DB_NAME="Car_Dealership";
    private static final String USER_NAME="sa";
    private static final String PASSWORD="12345";
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection cn= null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;databaseName="+ DB_NAME;
        cn= DriverManager.getConnection(url, USER_NAME, PASSWORD);
        return cn;
    }
}
