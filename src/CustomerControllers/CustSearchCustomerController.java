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
import CustomerCodes.CustomerDetails;
import CustomerCodes.Queries;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustSearchCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox keyType;
    @FXML
    private TextField keyVal;
    
    @FXML
    private TableView<CustomerDetails> custTable; 
    
    @FXML
    private TableColumn<CustomerDetails,String> custIDCol;
    @FXML
    private TableColumn<CustomerDetails,String> custfNameCol;
    @FXML
    private TableColumn<CustomerDetails,String> custlNameCol;
    @FXML
    private TableColumn<CustomerDetails,String> custNICCol;
    @FXML
    private TableColumn<CustomerDetails,String> custAddCol;
    @FXML
    private TableColumn<CustomerDetails,String> custTelCol;
    @FXML
    private TableColumn<CustomerDetails,String> custTypeCol;
    @FXML
    private TableColumn<CustomerDetails,Float> custPointsCol;
    
    //initialize ObservableList to hold out database data
    private ObservableList<CustomerDetails> custList;
    //combobox data list
    private ObservableList<String> keyList = FXCollections.observableArrayList("Customer ID","NIC","First Name");
        
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;
    Queries DBqry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBqry = new Queries();
        keyType.setItems(keyList);
        keyType.setValue("Customer ID");
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - View/Edit Customer");
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
    public void loadCustTable(ActionEvent Event){
        char type = 'a';
        String keyTypeVal = keyType.getValue().toString();
        if(keyTypeVal.equals("Customer ID")){
            type = 'i';
        }
        if(keyTypeVal.equals("NIC")){
            type = 'n';
        }
        if(keyTypeVal.equals("First Name")){
            type = 'f';
        }
        String keyString = keyVal.getText().toUpperCase();
        loadCustDataintoTable(keyString, type);
    }
    
    public void loadCustTableSuggestions(KeyEvent event){
        char type = 'a';
        String keyTypeVal = keyType.getValue().toString();
        if(keyTypeVal.equals("Customer ID")){
            type = 'i';
        }
        if(keyTypeVal.equals("NIC")){
            type = 'n';
        }
        if(keyTypeVal.equals("First Name")){
            type = 'f';
        }
        String keySuggestion = keyVal.getText().toUpperCase();
        loadCustDataintoTable(keySuggestion+"%", type);
    }
    
    public void loadCustDataintoTable(String key,char keyField){
    
        con = DBconnect.dbconnect();
        
        custList=FXCollections.observableArrayList();
        
        String searchBy="";
        if(keyField=='f'){
            searchBy="fName";
        }
        if(keyField=='n'){
            searchBy="NIC";
        }
        if(keyField=='i'){
            searchBy="custID";
        }
        
        try {           
            String qry = "Select * from customer where " + searchBy + " LIKE '" + key + "'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while(rs.next()){
                custList.add(new CustomerDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(8), rs.getString(10), rs.getString(12), rs.getFloat(13)));
            }
                       
        } catch (Exception e) {
            System.out.print(e);
        }
        
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        custfNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        custlNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        custNICCol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        custAddCol.setCellValueFactory(new PropertyValueFactory<>("aLine2"));
        custTelCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        custTypeCol.setCellValueFactory(new PropertyValueFactory<>("custType"));
        custPointsCol.setCellValueFactory(new PropertyValueFactory<>("custPoints"));
        
        custTable.setItems(null);
        custTable.setItems(custList);
        
    }
    
    @FXML
    public void viewCustActionButton(ActionEvent event) throws IOException{
        if(!custTable.getSelectionModel().isEmpty()){
            Queries.selectedCustomerID = custList.get(custTable.getSelectionModel().getSelectedIndex()).getCustID();
            SubHomeController subHomeC = subHome.getController();
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewCustomer.fxml"))); 
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Please Select a record to proceed!");
            alert.setContentText("Careful with this step!");

            alert.showAndWait();
        }
    }
    
    public void deleteCustActionButton(ActionEvent event){
        if(!custTable.getSelectionModel().isEmpty()){
            String selectedCustID = custList.get(custTable.getSelectionModel().getSelectedIndex()).getCustID();
            DBqry.deleteCustomer(selectedCustID,false);
            //reload table
            char type = 'a';
            String keyTypeVal = keyType.getValue().toString();
            if(keyTypeVal.equals("Customer ID")){
                type = 'i';
            }
            if(keyTypeVal.equals("NIC")){
                type = 'n';
            }
            if(keyTypeVal.equals("First Name")){
                type = 'f';
            }
            String keySuggestion = keyVal.getText().toUpperCase();
            loadCustDataintoTable(keySuggestion+"%", type);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Please Select a record to proceed!");
            alert.setContentText("Careful with this step!");

            alert.showAndWait();
        }
    }
    
    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException { 
        SubHomeController subHomeC = subHome.getController();
        
        subHomeC.subContent.getChildren().clear();//remove all items
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource(backUrl)));
    }
    
}
