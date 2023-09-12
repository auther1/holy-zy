package Client.src.Java;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.fxml.*;

import java.io.IOException;


public class LoginController {

    @FXML//cancel按钮
    private Button cancelButton;
    @FXML//登录按钮
    private Button loginButton;
    @FXML//注册按钮
    private Button registerButton;
    @FXML//输入账号框
    private TextField usernameField;
    @FXML//输入密码框
    private PasswordField enterPasswordField;


    private String backUsername="123";
    private String backPassword="456";

    /*
    点击登录按钮之后的行为
    原本是判断是否可以登录，现在改为将用户输入的邮箱和密码发给数据库
    数据库发回的信息在Client中接收，并交由本类的isValid方法处理
    在isValid方法中进行alert窗口的弹出和主界面跳转
     */
    public void loginButtonAction(ActionEvent event) {

        String username = usernameField.getText();
        String password = enterPasswordField.getText();
        //发送信息给服务器 login#[mail]#[password]$
        String message = "login#"+username+"#"+password+"$";
        try{
            Client.sendMessage(message);
        } catch (IOException e) {
            System.out.println("客户发送登录信息时出现异常");
            throw new RuntimeException(e);
        }

    }

    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    public void registerButtonAction(ActionEvent event)  throws IOException{
        Parent registerRoot = FXMLLoader.load(getClass().getResource("Client.src.Java.Client/src/Fxml/Register.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }

//    private boolean validateUsernameAndPassword(String username, String password){
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("backUsername: " + backUsername);
//        System.out.println("backPassword" + backPassword);
//        if (username.equals( backUsername )&& password.equals(backPassword)) {
//            return true;
//        }else if(username.isEmpty() || password.isEmpty()){
//            return false;
//        }
//       return false;
//    }

    //在Client收到相关消息后，调用此静态方法传回信息
    public static void isValid(int flag){
        switch (flag){
            case 0:

                //进入主界面

            case 1:
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("登录失败");
                alert1.setHeaderText(null);
                alert1.setContentText("密码错误");
                alert1.showAndWait();
            case 2:
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("登录失败");
                alert2.setHeaderText(null);
                alert2.setContentText("账号不存在");
                alert2.showAndWait();
        }
    }

}
