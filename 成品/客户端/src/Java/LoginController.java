package Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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


//    private String backUsername="123";
//    private String backPassword="456";

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
        //等待接受服务器发来的信息
        String msg=Client.receiveMessage();
        //收到信息后处理
        Client.handleReceivedMessage(msg);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 创建新的 Stage，并将注册界面设置为其 Scene
        Stage registerStage = new Stage();
        currentStage.hide();
    }

    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    public void registerButtonAction(ActionEvent event)  throws IOException{
        // 加载注册界面的 FXML 文件
        Parent registerRoot = FXMLLoader.load(getClass().getResource("../Fxml/Register.fxml"));
        Scene registerScene = new Scene(registerRoot);

        // 获取当前 Stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 创建新的 Stage，并将注册界面设置为其 Scene
        Stage registerStage = new Stage();
        registerStage.setScene(registerScene);

        // 关闭当前 Stage
        currentStage.close();

        // 显示注册界面的 Stage
        registerStage.show();


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
    public static void isSuccess(int flag){
        switch (flag){
            case 0:
                System.out.println("登陆成功");
                //进入主界面


                break;
            case 1:
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("登录失败");
                alert1.setHeaderText(null);
                alert1.setContentText("密码错误");
                alert1.showAndWait();
                break;
            case 2:
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("登录失败");
                alert2.setHeaderText(null);
                alert2.setContentText("账号不存在");
                alert2.showAndWait();
                break;
        }
    }

}
