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
import CustomerCodes.Queries;
import CustomerCodes.Validations;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
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
public class CustViewAgentController implements Initializable {

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
    @FXML
    private Button editBtn,okBtn;
    
    public boolean editMode;
    
    Image maleImage = new Image("/Images/male icon.jpg");
    Image femaleImage = new Image("/Images/female icon.jpg");
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;
    Queries DBqry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBqry = new Queries();
        editMode=false;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - View/Edit Agent");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/CustomerGUIs/CustSearchAgent.fxml";
        loadAgentData();
    }   
    
    public void loadAgentData(){
        //load customer details into view window
            con = DBconnect.dbconnect();
            String qry = "SELECT * FROM agent where agntID = '" + Queries.selectedAgentID + "'";
            try {           
                pst = con.prepareStatement(qry);
                rs = pst.executeQuery();
                rs.next();
                String gender=rs.getString("gender");
                if(gender.equals("Male")){
                    maleBtn.setSelected(true);
                    agntPhoto.setImage(maleImage);
                }   
                else{
                    femaleBtn.setSelected(true);
                    agntPhoto.setImage(femaleImage);
                }

                 agntIDField.setText(rs.getString("agntID"));
                 fnameField.setText(rs.getString("fName"));
                 lnameField.setText(rs.getString("lName"));
                 nicField.setText(rs.getString("NIC"));
                 company.setText(rs.getString("company"));
                 aLine1Field.setText(rs.getString("aLine1"));
                 aLine2Field.setText(rs.getString("aLine2"));
                 aPcodeField.setText(rs.getString("postalCode"));
                 teleField.setText(rs.getString("telephone"));
                 emailField.setText(rs.getString("email"));
                

            } catch (Exception e) {
                System.out.print(e);
            }
    }
    
    @FXML
    public void editActionButton(ActionEvent event) throws IOException{
        if(editMode){
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation Required");
            confirm.setHeaderText("Do you really want to update this Customer?");
            confirm.setContentText("Are you ok with these values?");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK){
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
                    excep=DBqry.updateAgentData(Queries.selectedAgentID,fnameVal,lnameVal,nicVal,genderVal,cmpVal,aLine1Val,aLine2Val,aPcodeVal,telVal,emailVal);
                    if(excep.endsWith(duplicateExcep)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("You have already added one agent with this NIC !");
                    alert.setContentText("Ooops, there was an error!");

                    alert.showAndWait();
                    }
                    else{
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("UPDATED");
                        msg.setHeaderText(""+Queries.selectedAgentID+" Agent has been updated");
                        msg.setContentText("Updating is completed!");

                        msg.showAndWait();
                        
                        //reload page
                        SubHomeController subHomeC = subHome.getController();
                        subHomeC.subContent.getChildren().clear();
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewAgent.fxml")));
                        
                    }
                }
            }
        }
        else{
            editMode=true;
            editBtn.setText("Save");
            fnameField.setEditable(true);
            lnameField.setEditable(true);
            nicField.setEditable(true);
            company.setEditable(true);
            aLine1Field.setEditable(true);
            aLine2Field.setEditable(true);
            aPcodeField.setEditable(true);
            teleField.setEditable(true);
            emailField.setEditable(true); 
            maleBtn.setDisable(false);
            femaleBtn.setDisable(false);
            okBtn.setText("Cancel");
        }
        
    }
    
    @FXML
    public void deleteAgentActionButton(ActionEvent event){
        DBqry.deleteAgent(Queries.selectedAgentID,true);
    }
    
    @FXML
    public void okActionButton(ActionEvent event) throws IOException{
        if(editMode){
            SubHomeController subHomeC = subHome.getController();
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewAgent.fxml")));
        }
        else{
            SubHomeController subHomeC = subHome.getController();
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CustHome.fxml")));
            subHomeC.funcNamelbl.setText("Customer & Campaigns");
        subHomeC.backBtn.setVisible(false);
        }
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
    
}
