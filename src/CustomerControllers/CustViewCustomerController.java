/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerControllers;

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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustViewCustomerController implements Initializable {

    @FXML
    private TextField custIDField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField nicField;
    @FXML
    private RadioButton maleBtn,femaleBtn;
    @FXML
    private TextField dobField;
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
    private TextField custTypeField;
    @FXML
    private TextField custPointsField;
    @FXML
    private ImageView custPhoto;
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
        subHomeC.funcNamelbl.setText("Customer & Campaigns - View Customer");
        subHomeC.backBtn.setVisible(true);//set back button
        SubHomeController.backUrl = "/CustomerGUIs/CustSearchCustomer.fxml";
        loadCustomerData();
    }
    
    public void loadCustomerData(){
        //load customer details into view window
            con = DBconnect.dbconnect();
            String qry = "SELECT * FROM customer where custID = '" + Queries.selectedCustomerID + "'";
            try {           
                pst = con.prepareStatement(qry);
                rs = pst.executeQuery();
                rs.next();
                String gender=rs.getString("gender");
                if(gender.equals("Male")){
                    maleBtn.setSelected(true);
                    custPhoto.setImage(maleImage);
                }   
                else{
                    femaleBtn.setSelected(true);
                    custPhoto.setImage(femaleImage);
                }

                 custIDField.setText(rs.getString("custID"));
                 fnameField.setText(rs.getString("fName"));
                 lnameField.setText(rs.getString("lName"));
                 nicField.setText(rs.getString("NIC"));
                 dobField.setText(rs.getString("DOB"));
                 aLine1Field.setText(rs.getString("aLine1"));
                 aLine2Field.setText(rs.getString("aLine2"));
                 aPcodeField.setText(rs.getString("postalCode"));
                 teleField.setText(rs.getString("telephone"));
                 emailField.setText(rs.getString("email"));
                 custTypeField.setText(rs.getString("custType"));
                 custPointsField.setText(rs.getString("custPoints"));
                

            } catch (Exception e) {
                System.out.print(e);
            }
    }
    @FXML
    public void editActionButton(ActionEvent event)throws IOException{
        if(editMode){
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation Required");
            confirm.setHeaderText("Do you really want to update this Customer?");
            confirm.setContentText("Are you ok with these values?");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                String fnameVal = fnameField.getText();
                String lnameVal = lnameField.getText();
                String nicVal = nicField.getText().toUpperCase();
                String genderVal;
                String dobVal = dobField.getText();
                String aLine1Val = aLine1Field.getText();
                String aLine2Val = aLine2Field.getText();
                String aPcodeVal = aPcodeField.getText();
                String telVal = teleField.getText();
                String emailVal = emailField.getText();
                
                String excep="";
                String duplicateExcep = "Duplicate entry '"+nicVal+"' for key 'NIC'";
                String dateExcep = "Incorrect date value: '"+dobVal+"' for column 'DOB' at row 1";

                if(maleBtn.isSelected())
                    genderVal="Male";
                else
                    genderVal="Female";

                boolean validation = true;

                if(fnameVal.isEmpty()||lnameVal.isEmpty()||nicVal.isEmpty()||dobVal.isEmpty()||aLine1Val.isEmpty()||aLine2Val.isEmpty()||aPcodeVal.isEmpty()||telVal.isEmpty()||emailVal.isEmpty()){
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
                    excep=DBqry.updateCustomerData(Queries.selectedCustomerID,fnameVal,lnameVal,nicVal,genderVal,dobVal,aLine1Val,aLine2Val,aPcodeVal,telVal,emailVal);
                    if(excep.endsWith(duplicateExcep)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("You have already added one user with this NIC !");
                    alert.setContentText("Ooops, there was an error!");

                    alert.showAndWait();
                    }
                    else if(excep.endsWith(dateExcep)){
                        Validations.wrongTextValueMsg("Date of Birth", "1995/12/30");
                    }
                    else{
                        Alert msg = new Alert(AlertType.INFORMATION);
                        msg.setTitle("UPDATED");
                        msg.setHeaderText(""+Queries.selectedCustomerID+" Customer has been updated");
                        msg.setContentText("Updating is completed!");

                        msg.showAndWait();
                        
                        //reload page
                        SubHomeController subHomeC = subHome.getController();
                        subHomeC.subContent.getChildren().clear();
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewCustomer.fxml")));
                        
                    }
                }
            }/* else {
                SubHomeController subHomeC = subHome.getController();
                subHomeC.subContent.getChildren().clear();
                subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewCustomer.fxml"))); 
            }*/

        }
        else{
            editMode=true;
            editBtn.setText("Save");
            fnameField.setEditable(true);
            lnameField.setEditable(true);
            nicField.setEditable(true);
            dobField.setEditable(true);
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
    public void deleteCustActionButton(ActionEvent event){
        DBqry.deleteCustomer(Queries.selectedCustomerID,true);
    }
    @FXML
    public void okActionButton(ActionEvent event) throws IOException{
        if(editMode){
            SubHomeController subHomeC = subHome.getController();
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewCustomer.fxml")));
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
        custPhoto.setImage(maleImage);
    }
    @FXML
    public void femalePhotoview(ActionEvent event){
        custPhoto.setImage(femaleImage);
    }
    
}
