package Java;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.fxml.*;

import java.io.IOException;

public class RegisterController {

    @FXML//cancel按钮
    private Button shieldButton;
    @FXML
    private TextField mailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField determineField;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField mobileField;
    @FXML
    private Button registerButton;


    public void shieldButtonAction(ActionEvent event){
        Stage stage = (Stage) shieldButton.getScene().getWindow();
        stage.close();

    }
    public void registerButtonAction(ActionEvent event){
        String mail=mailField.getText();
        String password=passwordField.getText();
        String determinePassword=determineField.getText();
        String name=nicknameField.getText();
        if(!password.equals(determinePassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("注册失败");
            alert.setHeaderText(null);
            alert.setContentText("两次输入的密码不同");
            alert.showAndWait();
            return;
        }
        //register#[mail]#[password]#[name]$
        String message="register#"+mail+"#"+password+"#"+name+"$";
        try {
            Client.sendMessage(message);
        } catch (IOException e) {
            System.out.println("注册时发送信息出现异常");
            throw new RuntimeException(e);
        }
        //等待接收服务器发来的信息
        String msg=Client.receiveMessage();
        Client.handleReceivedMessage(msg);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 创建新的 Stage，并将注册界面设置为其 Scene

        currentStage.hide();
    }

    public static void isSuccess(int flag){
        if(flag==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("注册失败");
            alert.setHeaderText(null);
            alert.setContentText("账号已存在");
            alert.showAndWait();
        }else{
            System.out.println("注册成功");
            //进行相应的注册成功提示，并跳转界面

        }
    }
}
