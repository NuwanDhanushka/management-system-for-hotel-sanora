/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class SubHomeController implements Initializable {

    @FXML
    public Label funcNamelbl;
    
    @FXML
    public StackPane subContent;
    
    @FXML
    public Button backBtn,custBtn;
    
    @FXML
    public AnchorPane subMenuList; 
    
    public static String backUrl="/GUIs/CustHome.fxml";
    
    @FXML
    public void homeButtonAction(ActionEvent event) throws IOException { 
        Parent root = FXMLLoader.load(getClass().getResource("/GUIs/MainHome.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();  
    }   
    
    @FXML
    public void backButtonAction(ActionEvent event) throws IOException { 
        subContent.getChildren().clear();//remove all items
        subContent.getChildren().add(FXMLLoader.load(getClass().getResource(backUrl)));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backBtn.setVisible(false);
    }    
    
}
