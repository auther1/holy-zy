package Server;

import java.sql.*;

public class JdbcTool {
    //mail password name point id
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc驱动出现异常");
            throw new RuntimeException(e);
        }
    }

    //创建连接
    public static Connection getConnection(){
        Connection connection=null;
        try{
            String url="jdbc:mysql://localhost:3306/poker?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            String username="root";
            String password="zyc1234567";
            connection= DriverManager.getConnection(url,username,password);

        } catch (SQLException e) {
            System.out.println("连接数据库异常");
            throw new RuntimeException(e);
        }
        return connection;
    }

    //关闭连接
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        try{
            if(connection!=null) connection.close();
            if(statement!=null) statement.close();
            if(resultSet!=null) resultSet.close();
        } catch (SQLException e) {
            System.out.println("关闭连接异常");
            throw new RuntimeException(e);
        }
    }
}
