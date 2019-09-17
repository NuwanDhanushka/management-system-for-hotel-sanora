/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerControllers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import static Controllers.MainHomeController.subHome;
import Controllers.SubHomeController;
import static Controllers.SubHomeController.backUrl;
import CustomerCodes.Queries;
import CustomerCodes.Validations;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustAddAgentController implements Initializable {

    @FXML
    private TextField agntIDField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField nicField;
    @FXML
    private TextField company;
    @FXML
    private RadioButton maleBtn,femaleBtn;
    @FXML
    private TextField aLine1Field;
    @FXML
    private TextField aLine2Field;
    @FXML
    private TextField aPcodeField;
    @FXML
    private TextField teleField;
    @FXML
    private TextField emailField;
    @FXML
    private ImageView agntPhoto; 
    
    Image maleImage = new Image("/Images/male icon.jpg");
    Image femaleImage = new Image("/Images/female icon.jpg");
    Queries DBqry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBqry = new Queries();
        DBqry.setAgentID(agntIDField);
        
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - Add Agent");
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
    public void integersOnlyKeyEvent(KeyEvent event){ 
        Validations.integersOnly(event);
    }
    @FXML
    public void lettersOnlyKeyEvent(KeyEvent event){ 
        Validations.lettersOnly(event);
    }
    @FXML
    public void lettersAndDigitsOnlyKeyEvent(KeyEvent event){ 
        Validations.lettersAndDigitsOnly(event);
    }
    @FXML
    public void malePhotoview(ActionEvent event){
        agntPhoto.setImage(maleImage);
    }
    @FXML
    public void femalePhotoview(ActionEvent event){
        agntPhoto.setImage(femaleImage);
    }
    
    @FXML
    public void agntAddButtonAction(ActionEvent event){ 
        String agntIDVal = agntIDField.getText();
        String fnameVal = fnameField.getText();
        String lnameVal = lnameField.getText();
        String nicVal = nicField.getText().toUpperCase();
        String genderVal;
        String cmpVal = company.getText();
        String aLine1Val = aLine1Field.getText();
        String aLine2Val = aLine2Field.getText();
        String aPcodeVal = aPcodeField.getText();
        String telVal = teleField.getText();
        String emailVal = emailField.getText();
        String currnetDate;
        
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        currnetDate=dateFormat.format(date);
        
        String excep="";
        String duplicateExcep = "Duplicate entry '"+nicVal+"' for key 'NIC'";
        
        if(maleBtn.isSelected())
            genderVal="Male";
        else
            genderVal="Female";
        
        boolean validation = true;
        boolean nextNew;
        
        if(fnameVal.isEmpty()||lnameVal.isEmpty()||nicVal.isEmpty()||cmpVal.isEmpty()||aLine1Val.isEmpty()||aLine2Val.isEmpty()||aPcodeVal.isEmpty()||telVal.isEmpty()||emailVal.isEmpty()){
            Validations.fieldEmptyMsg();
            validation = false;    
        }
        if(!nicVal.isEmpty() && !Validations.NICvalidate(nicVal)){
            validation = false;
        }
        if(!telVal.isEmpty() && !Validations.TPvalidate(telVal)){
            validation = false;
        }
        if(!emailVal.isEmpty() && !Validations.emailValidate(emailVal)){
            validation = false;
        }
        if(aPcodeVal.length()!=5 && !aPcodeVal.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please enter a valid Postal Code !");
            alert.setContentText("If you don`t know the postal code, Enter it as \"00000\"");

            alert.showAndWait();
        }
        
        if(validation){
            excep=DBqry.insertNewAgentData(agntIDVal,fnameVal,lnameVal,nicVal,genderVal,cmpVal,aLine1Val,aLine2Val,aPcodeVal,telVal,emailVal,currnetDate);
        
            if(excep.endsWith(duplicateExcep)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You have already added one agent with this NIC !");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }
            else{
                nextNew=Validations.nextNewRecordConfirm("Agnet");
                if(!nextNew){
                    SubHomeController subHomeC = MainHomeController.subHome.getController();

                    subHomeC.funcNamelbl.setText("Agent");
                    subHomeC.subContent.getChildren().clear();
                    try {
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CustHome.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(CustAddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    subHomeC.subMenuList.getChildren().clear();
                    try {
                        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(CustAddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    subHomeC.backBtn.setVisible(false);
                }
                else{
                    SubHomeController subHomeC = MainHomeController.subHome.getController();

                    subHomeC.subContent.getChildren().clear();
                    try {
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustAddAgent.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(CustAddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException { 
        String fnameVal = fnameField.getText();
        String lnameVal = lnameField.getText();
        String nicVal = nicField.getText().toUpperCase();
        String cmpVal = company.getText();
        String aLine1Val = aLine1Field.getText();
        String aLine2Val = aLine2Field.getText();
        String aPcodeVal = aPcodeField.getText();
        String telVal = teleField.getText();
        String emailVal = emailField.getText();
        
        if(!fnameVal.isEmpty()||!lnameVal.isEmpty()||!nicVal.isEmpty()||!cmpVal.isEmpty()||!aLine1Val.isEmpty()||!aLine2Val.isEmpty()||!aPcodeVal.isEmpty()||!telVal.isEmpty()||!emailVal.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to cancel this?");
            alert.setContentText("This will loss you entered values!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                SubHomeController subHomeC = subHome.getController();
        
                subHomeC.subContent.getChildren().clear();//remove all items
                subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource(backUrl)));
                //subHomeC.subMenuList.getChildren().clear();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else{
            SubHomeController subHomeC = subHome.getController();
        
                subHomeC.subContent.getChildren().clear();//remove all items
                subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource(backUrl)));
                //subHomeC.subMenuList.getChildren().clear();
        }
        
    }
    @FXML
    public void clearActionButton(ActionEvent event){
        fnameField.setText("");
        lnameField.setText("");
        nicField.setText("");
        company.setText("");
        maleBtn.setSelected(true);
        agntPhoto.setImage(maleImage);
        aLine1Field.setText("");
        aLine2Field.setText("");
        aPcodeField.setText("");
        teleField.setText("");
        emailField.setText("");
    }
    @FXML
    public void demoActionButton(ActionEvent event){
        fnameField.setText("Tharindu");
        lnameField.setText("Madushanka");
        nicField.setText("958855999V");
        company.setText("ABC Company");
        maleBtn.setSelected(true);
        agntPhoto.setImage(maleImage);
        aLine1Field.setText("B263");
        aLine2Field.setText("Malabe");
        aPcodeField.setText("10115");
        teleField.setText("0777123456");
        emailField.setText("abc@gmail.com");
    }
}
