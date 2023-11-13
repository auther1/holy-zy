package petShop.persistence;

import java.sql.*;

public class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jpetstore?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "zyc1234567";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            System.out.println("没连上？");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection!=null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void closeStaement(Statement statement){
        if(statement!=null){
            try{
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void closePreparedStaement(PreparedStatement preparedStatement){
        if(preparedStatement!=null){
            try{
                preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closePreparedStatent(PreparedStatement pStatement) throws Exception{
        pStatement.close();
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
