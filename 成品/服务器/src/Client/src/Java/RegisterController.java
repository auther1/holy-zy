package Client.src.Java;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.fxml.*;



public class RegisterController {

    @FXML//cancel按钮
    private Button shieldButton;













    
    public void shieldButtonAction(ActionEvent event){
        Stage stage = (Stage) shieldButton.getScene().getWindow();
        stage.close();

    }






}
