/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivercontrollers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Deliverycodes.DBcate;
import Deliverycodes.DBpay;
import Deliverycodes.Validation;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pavilion 15
 */
public class Del_driversController implements Initializable {
    
    
    private ObservableList<DBpay> data;
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;

    @FXML
    private TableView<DBpay> payTable;
    @FXML
    private TableColumn<DBpay, Integer> D_id;
    @FXML
    private TableColumn<DBpay, String> driver;
    @FXML
    private TableColumn<DBpay, Integer> cost;
    @FXML
    private TableColumn<DBpay, String> status;
    @FXML
    private Button search;
    @FXML
    private Button clear;
    @FXML
    private TextField D_ID;
    @FXML
    private Label paytot;
    @FXML
    private Label payerror;
    @FXML
    private Button viewall;
    @FXML
    private Button payed;
    @FXML
    private ComboBox<String> chooser;

    /**
     * Initializes the controller class.
     * 
     */
    private final  ObservableList<String> typechooser=FXCollections.observableArrayList("Delivery","Catering");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         SubHomeController subHomeC = MainHomeController.subHome.getController();
         DelSubMenuListController.delListState=1;
        subHomeC.funcNamelbl.setText("Delivery System - Payment");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/DeliverHome.fxml";
        chooser.setItems(typechooser);
        // TODO
    }    

    @FXML
    private void search(ActionEvent event) {
          Connection con = DBconnect.dbconnect();
          data = FXCollections.observableArrayList();
          String C_chooser = chooser.getValue();
          String C_D_ID= D_ID.getText();
          
          try {
              if(C_chooser=="Delivery"){
            ResultSet rs = con.createStatement().executeQuery("SELECT Cust_id From Delivery where Cust_id='"+D_ID+"'");
              }
              else
              {
                   ResultSet rs = con.createStatement().executeQuery("SELECT Cust_id From catering where Cust_id='"+D_ID+"'");
              }
              
                  
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        D_ID.setText("");
    }

    @FXML
    private void viewall(ActionEvent event) {
         Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
           try {
            ResultSet rs = con.createStatement().executeQuery("SELECT Cust_id,supervisor,Cost,status");
             while (rs.next()) {
            data.add(new DBpay(rs.getString(3), rs.getString(9), rs.getString(12), rs.getString(13)));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
          D_id.setCellValueFactory(new PropertyValueFactory<>("Cust_id"));
        driver.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        
        cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
       status.setCellValueFactory(new PropertyValueFactory<>("status"));
       
        
        
        payTable.setItems(null);
        payTable.setItems(data); 
        
    }

    @FXML
    private void payed(ActionEvent event) {
         String did = D_ID.getText();
         String choose = chooser.getValue();
         
        String excep="";
        
        
        boolean validation = true;
        boolean nextNew;
        
        if(did.isEmpty()||choose.isEmpty()){
            Validation.fieldEmptyMsg();
            validation = false;    
        }
        if(did.isEmpty()||choose.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            System.out.println("hello");
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to cancel this?");
            alert.setContentText("This will loss you entered values!");       
        }
        else
               {
                   try{
                          if(choose=="Delivery")
                    {
                        
                    
                        String pay="payed";
           String qry = "INSERT INTO delivery_managment(Status) VALUES ('" + pay + "' )";
           pst =con.prepareStatement(qry);
           pst.execute();
             showAlertreg(); 
                    }
                          else
                          {
                              String pay="payed";
                              String qry = "INSERT INTO catering(Status) VALUES ('" + pay +"' )";
           pst =con.prepareStatement(qry);
           pst.execute();
             showAlertreg(); 
                          }
             
           D_ID.setText("");  
           chooser.setValue("");
        
           
                   }catch(Exception e)
                   {
                       System.out.println(e);
                   }
        } 
    }

    private void showAlertreg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
