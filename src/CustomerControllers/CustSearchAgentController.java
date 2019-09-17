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
import CustomerCodes.AgentDetails;
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
public class CustSearchAgentController implements Initializable {

    @FXML
    private ComboBox keyType;
    @FXML
    private TextField keyVal;
    //combobox data list
    private ObservableList<String> keyList = FXCollections.observableArrayList("Agent ID","NIC","First Name");
    
    @FXML
    private TableView<AgentDetails> agentTable;
    
    @FXML
    private TableColumn<AgentDetails,String> agntIDCol;
    @FXML
    private TableColumn<AgentDetails,String> agntfNameCol;
    @FXML
    private TableColumn<AgentDetails,String> agntlNameCol;
    @FXML
    private TableColumn<AgentDetails,String> agntNICCol;
    @FXML
    private TableColumn<AgentDetails,String> agntAddCol;
    @FXML
    private TableColumn<AgentDetails,String> agntTelCol;
    @FXML
    private TableColumn<AgentDetails,String> agntCompanyCol;
    
    //initialize ObservableList to hold out database data
    private ObservableList<AgentDetails> agentList;
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;
    Queries DBqry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBqry = new Queries();
        keyType.setItems(keyList);
        keyType.setValue("Agent ID");
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - View/Edit Agent");
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
    public void loadAgentTable(ActionEvent Event){
        char type = 'a';
        String keyTypeVal = keyType.getValue().toString();
        if(keyTypeVal.equals("Agent ID")){
            type = 'i';
        }
        if(keyTypeVal.equals("NIC")){
            type = 'n';
        }
        if(keyTypeVal.equals("First Name")){
            type = 'f';
        }
        String keyString = keyVal.getText().toUpperCase();
        loadAgentDataintoTable(keyString, type);
    }
    @FXML
    public void viewAgentActionButton(ActionEvent event) throws IOException{
        if(!agentTable.getSelectionModel().isEmpty()){
            Queries.selectedAgentID = agentList.get(agentTable.getSelectionModel().getSelectedIndex()).getagentID();
            SubHomeController subHomeC = subHome.getController();
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustViewAgent.fxml"))); 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Please Select a record to proceed!");
            alert.setContentText("Careful with this step!");

            alert.showAndWait();
        }
    }
    public void loadAgentTableSuggestions(KeyEvent event){
        char type = 'a';
        String keyTypeVal = keyType.getValue().toString();
        if(keyTypeVal.equals("Agent ID")){
            type = 'i';
        }
        if(keyTypeVal.equals("NIC")){
            type = 'n';
        }
        if(keyTypeVal.equals("First Name")){
            type = 'f';
        }
        String keySuggestion = keyVal.getText().toUpperCase();
        loadAgentDataintoTable(keySuggestion+"%", type);
    }
    
    public void loadAgentDataintoTable(String key,char keyField){
    
        con = DBconnect.dbconnect();
        
        agentList=FXCollections.observableArrayList();
        
        String searchBy="";
        if(keyField=='f'){
            searchBy="fName";
        }
        if(keyField=='n'){
            searchBy="NIC";
        }
        if(keyField=='i'){
            searchBy="agntID";
        }
        
        try {           
            String qry = "Select * from agent where " + searchBy + " LIKE '" + key + "'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while(rs.next()){
                agentList.add(new AgentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(8), rs.getString(10), rs.getString(6)));
            }
                       
        } catch (Exception e) {
            System.out.print(e);
        }
        
        agntIDCol.setCellValueFactory(new PropertyValueFactory<>("agentID"));
        agntfNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        agntlNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        agntNICCol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        agntAddCol.setCellValueFactory(new PropertyValueFactory<>("aLine2"));
        agntTelCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        agntCompanyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        
        agentTable.setItems(null);
        agentTable.setItems(agentList);  
    }
    
    public void deleteAgentActionButton(ActionEvent event){
        if(!agentTable.getSelectionModel().isEmpty()){
            String selectedAgntID = agentList.get(agentTable.getSelectionModel().getSelectedIndex()).getagentID();
            DBqry.deleteAgent(selectedAgntID,false);
            //reload table
            char type = 'a';
            String keyTypeVal = keyType.getValue().toString();
            if(keyTypeVal.equals("Agent ID")){
                type = 'i';
            }
            if(keyTypeVal.equals("NIC")){
                type = 'n';
            }
            if(keyTypeVal.equals("First Name")){
                type = 'f';
            }
            String keySuggestion = keyVal.getText().toUpperCase();
            loadAgentDataintoTable(keySuggestion+"%", type);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
