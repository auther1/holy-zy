package Java;



import javafx.scene.Parent;
import javafx.scene.Scene;



import javafx.stage.*;
import javafx.fxml.*;
import javafx.application.*;


import java.io.File;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent  root = FXMLLoader.load(getClass().getResource("../Fxml/Login.fxml"));
        primaryStage.setTitle("登录");
        primaryStage.setScene(new Scene(root ,800 ,600));
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {


        launch(args);
    }
}