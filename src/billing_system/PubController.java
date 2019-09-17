/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */
public class PubController implements Initializable {

    @FXML
   public void jump(ActionEvent event)throws Exception{
       Stage stage=new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("calBill.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
       
   }
@FXML
public void buttonclick(ActionEvent event) throws Exception{
    Stage stage=new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("updateStock.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
}
        
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
