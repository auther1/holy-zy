package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
        //register#[mail]#[password]#[name]$
    //判断是否注册成功，注册成功返回true，账号存在返回false
    public static boolean register(String[] messageList) throws SQLException {
        String mail=messageList[1];
        String password=messageList[2];
        String name=messageList[3];
        Connection connection=JdbcTool.getConnection();
        Statement statement=connection.createStatement();
        String sql1="select * from user where mail='"+mail+"'";
        ResultSet resultSet= statement.executeQuery(sql1);
        if(resultSet.next()){
            System.out.println("注册时账号已存在");
            return false;
        }else{
            System.out.println("允许注册");
            String sql2= String.format("insert into user (mail,password,name) value ('%s','%s','%s')", mail,password,name);
            System.out.println(sql2);
            statement.executeUpdate(sql2);
            return true;
        }
    }
}
