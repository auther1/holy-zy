package Client.src.Java;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.application.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent  root = FXMLLoader.load(getClass().getResource("../Fxml/Login.fxml"));
            primaryStage.setTitle("登录");
            primaryStage.setScene(new Scene(root ,579 ,462));
            primaryStage.show();
        }catch(IOException e){
            System.out.println("异常");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}