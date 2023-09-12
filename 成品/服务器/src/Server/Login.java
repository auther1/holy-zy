package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    //login#[mail]#[password]$
    /*检查是否允许登录
    返回值如下：
    0：允许登录
    1：密码错误
    2：账号不存在
     */
    public static int check(String[] messageList) throws SQLException {
        //用户输入的邮箱
        String mail=messageList[1];
        //用户输入的密码
        String password=messageList[2];
        Connection connection=JdbcTool.getConnection();
        Statement statement=connection.createStatement();
        String sql="select * from user where mail='"+mail+"'";
        ResultSet resultSet= statement.executeQuery(sql);
        try {
            if(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    System.out.println("检查结果为允许登录");
                    return 0;
                }else{
                    System.out.println("检查结果为密码错误");
                    return 1;
                }
            }else{
                System.out.println("检查结果为账号不存在");
                return 2;
            }
        } finally {
            JdbcTool.close(connection,statement,resultSet);
        }
    }
}
