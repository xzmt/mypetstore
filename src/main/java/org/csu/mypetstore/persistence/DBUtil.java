package org.csu.mypetstore.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBUtil {
private static String driver = "com.mysql.cj.jdbc.Driver";
private static String url = "jdbc:mysql://127.0.0.1/mypetstore";
private static String username = "root";
private static String password = "";

public static Connection getConnection() throws Exception{
    Connection connection = null;

    Class.forName(driver);
    connection = DriverManager.getConnection(url,username,password);

    return connection;
}

public static void closeConnection(Connection connection) throws Exception
{
    if(connection != null)
    {
        connection.close();
    }
}

public static void closeResultSet(ResultSet resultSet) throws Exception{

    resultSet.close();
}

public static void closeStatement(Statement statement) throws Exception{
    statement.close();
}

public static void closePreparedStatement(PreparedStatement preparedStatement) throws Exception{
    preparedStatement.close();
}
//
//public static void main(String[] args) throws Exception{
//    Connection con = DBUtil.getConnection();
//    System.out.println(con);
//}
}
