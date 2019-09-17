/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerControllers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import CustomerCodes.Email;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustSendNotificationController implements Initializable {

    @FXML
    private ImageView beginnerPromoPhoto; 
    @FXML
    private ImageView priorityPromoPhoto; 
    @FXML
    private ImageView platimunPromoPhoto; 
    @FXML
    private TextArea beginnerText;
    @FXML
    private TextArea priorityText;
    @FXML
    private TextArea platinumText;
    @FXML
    public ImageView Bicon;
    @FXML
    public Label Blbl;
    @FXML
    public ImageView Prioicon;
    @FXML
    public Label Priolbl;
    @FXML
    public ImageView Platicon;
    @FXML
    public Label Platlbl;
    
    Image beginnerImage;
    String beginnerImgUrl="";
    Image prioImage;
    String priorityImgUrl="";
    Image paltinumImage;
    String paltinumImgUrl="";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - Send Notifications");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/CustHome.fxml";
    }
    
    @FXML
    public void chooseBeginerPromoImgButtonAction(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.jpg","*.png"));
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile!=null){
            beginnerImgUrl=selectedFile.getAbsolutePath();
            File file = new File(beginnerImgUrl);
            beginnerImage = new Image(file.toURI().toString());
            beginnerPromoPhoto.setImage(beginnerImage);
        }
        else{
            System.out.print("Invalid File");
        }
    }
    @FXML
    public void choosePrioPromoImgButtonAction(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.jpg","*.png"));
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile!=null){
            priorityImgUrl=selectedFile.getAbsolutePath();
            File file = new File(priorityImgUrl);
            prioImage = new Image(file.toURI().toString());
            priorityPromoPhoto.setImage(prioImage);
        }
        else{
            System.out.print("Invalid File");
        }
    }
    @FXML
    public void choosePlatiPromoImgButtonAction(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.jpg","*.png"));
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile!=null){
            paltinumImgUrl=selectedFile.getAbsolutePath();
            File file = new File(paltinumImgUrl);
            paltinumImage = new Image(file.toURI().toString());
            platimunPromoPhoto.setImage(paltinumImage);
        }
        else{
            System.out.print("Invalid File");
        }
    }
    
    @FXML
    public void sendBeginnerPromoButtonAction(ActionEvent event){
        String Text = beginnerText.getText();
        if(Text.isEmpty()&&beginnerImgUrl.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please enter Beginners` promotion details !");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }
        else if(!Text.isEmpty()&&beginnerImgUrl.isEmpty()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required");
            alert.setHeaderText("Do you really want to send Promotion Email without Promo Image?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Bicon.setVisible(true);
                Blbl.setVisible(true);
                Email email = new Email();
                email.sendPromotionEmail(beginnerImgUrl, Text,1); 
                
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else if(Text.isEmpty()&&!beginnerImgUrl.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required");
            alert.setHeaderText("Do you really want to send Promotion Email without Promotion Details?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Bicon.setVisible(true);
                Blbl.setVisible(true);
                Email email = new Email();
                email.sendPromotionEmail(beginnerImgUrl, Text,1);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else{
            Bicon.setVisible(true);
            Blbl.setVisible(true);
            Email email = new Email();
            email.sendPromotionEmail(beginnerImgUrl, Text,1);
        }
        
    }
    @FXML
    public void sendPriorityPromoButtonAction(ActionEvent event){
        String Text = priorityText.getText();
        if(Text.isEmpty()&&priorityImgUrl.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please enter Priority Customers` promotion details !");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }
        else if(!Text.isEmpty()&&priorityImgUrl.isEmpty()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required");
            alert.setHeaderText("Do you really want to send Promotion Email without Promo Image?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Prioicon.setVisible(true);
                Priolbl.setVisible(true);
                Email email = new Email();
                email.sendPromotionEmail(priorityImgUrl, Text,2);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else if(Text.isEmpty()&&!priorityImgUrl.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required");
            alert.setHeaderText("Do you really want to send Promotion Email without Promotion Details?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Prioicon.setVisible(true);
                Priolbl.setVisible(true);
                Email email = new Email();
                email.sendPromotionEmail(priorityImgUrl, Text,2);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else{
            Prioicon.setVisible(true);
            Priolbl.setVisible(true);
            Email email = new Email();
            email.sendPromotionEmail(priorityImgUrl, Text,2);
        }
        
    }
    @FXML
    public void sendPlatinumPromoButtonAction(ActionEvent event){
        String Text = platinumText.getText();
        if(Text.isEmpty()&&paltinumImgUrl.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please enter Platinum Customers` promotion details !");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }
        else if(!Text.isEmpty()&&paltinumImgUrl.isEmpty()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required");
            alert.setHeaderText("Do you really want to send Promotion Email without Promo Image?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platicon.setVisible(true);
                Platlbl.setVisible(true);
                Email email = new Email();
                email.sendPromotionEmail(paltinumImgUrl, Text,3);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else if(Text.isEmpty()&&!paltinumImgUrl.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required");
            alert.setHeaderText("Do you really want to send Promotion Email without Promotion Details?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platicon.setVisible(true);
                Platlbl.setVisible(true);
                Email email = new Email();
                email.sendPromotionEmail(paltinumImgUrl, Text,3);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else{
            Platicon.setVisible(true);
            Platlbl.setVisible(true);
            Email email = new Email();
            email.sendPromotionEmail(paltinumImgUrl, Text,3);
        }   
    }    
}
