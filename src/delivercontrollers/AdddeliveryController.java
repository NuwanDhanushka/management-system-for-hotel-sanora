/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivercontrollers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Deliverycodes.DBval;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Akhila
 */
public class AdddeliveryController implements Initializable {
    
    private ObservableList<DBval> data;
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;
    


 
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField  dphone;
    @FXML
    private Button  dsearch;
    @FXML
    private Button ddelete;
    @FXML
    private Button dupdate;
    @FXML
    private TableView<DBval> delivtable;
    @FXML
    private TableColumn<DBval, String> tcustid;
    @FXML
    private TableColumn<DBval, String> tcustname;
    @FXML
    private TableColumn<DBval, String> taddress;
    @FXML
    private TableColumn<DBval, String> tspecialmrk;
    @FXML
    private TableColumn<DBval, Integer> tphone;
    @FXML
    private TableColumn<DBval, Integer> tsecondphone;
    @FXML
    private Button dview;
    @FXML
    private Button back;
    @FXML
    private Button dconfirm;
    @FXML
    private TextField ddate;
    @FXML
    private TextField dqty;
    @FXML
    private Button dclear;
    @FXML
    private ComboBox ddriver;
    @FXML
    private ComboBox dpayment;
    @FXML
    private ComboBox ditem;
    @FXML
    private Button dclear_2;
    @FXML
    private Button ddemo;
    @FXML
    private Label dcustid;
    @FXML
    private Label dcost;
    @FXML
    private Label ditemcode;
       @FXML
    private Label regerror;
   
     private final  ObservableList<String> typeditem=FXCollections.observableArrayList("Chiken Friedrice","EGG FriedRice","Vegitable FriedRice","Seafoof friedrice","chiken Nasigorang","Seafood Nasigorang","Chiken buriyani");
     private final  ObservableList<String> typedpaymenttype=FXCollections.observableArrayList("Debit or Credit","Cash");
      private final  ObservableList<String> typeddriver=FXCollections.observableArrayList("Sanat","Charit","nimal","ruwan","anoj");
    @FXML
    private TableColumn<?, ?> tcustpoint;
    @FXML
    private Button dcalc;
    private String p;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        DelSubMenuListController.delListState=1;
        subHomeC.funcNamelbl.setText("Delivery System - Delivery");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/DeliverHome.fxml";
        
         ditem.setItems(typeditem);
        dpayment.setItems(typedpaymenttype);
         ddriver.setItems(typeddriver);
    }    

    private void dclearButtonAction(ActionEvent event) {
         dphone.setText("");
        dqty.setText("");
        ditemcode.setText("");
        ddate.setText("");
        dcost.setText("");
    }

    @FXML
    private void searchnum(ActionEvent event) {
        
        
        
    }

    @FXML
    private void deletedelivery(ActionEvent event) {
          DBval user=(DBval)delivtable.getSelectionModel().getSelectedItem();
        String d="DELETE  FROM delivery_managment WHERE D_phone = ?";
        try{
        pst = con.prepareStatement(d);
        pst.setString(1,user.getD_phone());
        pst.execute();
        regerror.setText("Values are susscessfully Deleted!");
                       
        ObservableList<DBval>UserSelected,allUser;
        allUser=delivtable.getItems();
        UserSelected=delivtable.getSelectionModel().getSelectedItems();
        UserSelected.forEach(allUser::remove);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
      @FXML
    private void Itemevent(ActionEvent event) {
            String x=ditem.getSelectionModel().getSelectedItem().toString();
           try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * From unit_price");
             while (rs.next()) {
                 if(x.equals(rs.getString("Item"))){
                     p=rs.getString("Item_code");
                     dcost.setText(p);
                 
                 
                 }
                 
         
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    
    
    
    
    
    
    }

    @FXML
    private void updatedelivery(ActionEvent event) {
            String custid = dcustid.getText();
            String items = (String) ditem.getValue();
            String qty = dqty.getText();
            String paymnt = (String) dpayment.getValue();
            String date = ddate.getText();
            String driver=  (String) ddriver.getValue();
            String phone=dphone.getText();
            String itemcode=ditemcode.getText();
            String cost=dcost.getText();
            
            String q3="UPDATE delivery_managment SET Items ='"+items+"',payment_type='"+ paymnt+"',		Qty='"+qty+"',Driver='"+driver+"',	Date='"+date+"'' WHERE 	D_phone='"+phone+"'";
            try{
            pst = con.prepareStatement(q3);
            pst.execute();
            
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Are u Sure u want to update?");
            alert.showAndWait();
                 
            
          regerror.setText("Values are susscessfully Updated!");
            ditem.setValue("");
           dpayment.setValue("");
           dqty.setText("");
           ddriver.setValue("");
            ddate.setText("");
          dcost.setText("");
          dcustid.setText("");
           dphone.setText("");
            dcost.setText("");
            ditemcode.setText("");
            
            }catch(Exception e){
                System.out.println(e);
            }
            
    }

    @FXML
    private void onclicktable(MouseEvent event) {
    }

    @FXML
    private void searchAlldelivery(ActionEvent event) {
        Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
           try {
            ResultSet rs = con.createStatement().executeQuery("SELECT custID,fName,aLine1,aLine2,telephone,postalCode From ");
             while (rs.next()) {
            data.add(new DBval(rs.getString(3), rs.getString(2), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
          tcustid.setCellValueFactory(new PropertyValueFactory<>("custID"));
        tcustname.setCellValueFactory(new PropertyValueFactory<>("fName"));
        
        taddress.setCellValueFactory(new PropertyValueFactory<>("aLine1"));
       tspecialmrk.setCellValueFactory(new PropertyValueFactory<>("aLine2"));
        
  tphone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        
      tsecondphone.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        
       
        
        delivtable.setItems(null);
        delivtable.setItems(data); 
            
    }
            

    @FXML
    private void BackAction(ActionEvent event) {
    }

    @FXML
    private void confirmorder(ActionEvent event) {
         String D_item = (String) ditem.getValue();
            String D_payment = (String) dpayment.getValue();
            String D_qty = dqty.getText();
            String D_driver = (String) ddriver.getValue();
            String D_date = ddate.getText();
           
            
        String excep="";
        
        
        boolean validation = true;
        boolean nextNew;
        
        
        
        if(D_item.isEmpty()||D_payment.isEmpty()||D_qty.isEmpty()||D_driver.isEmpty()||D_date.isEmpty()){
            Validation.fieldEmptyMsg();
            validation = false;    
        }
       
        
        

          if(D_item.isEmpty()||D_payment.isEmpty()||D_qty.isEmpty()||D_driver.isEmpty()||D_date.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            System.out.println("hello");
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to cancel this?");
            alert.setContentText("This will loss you entered values!");
              
          }else
               {
            
                   try{
           String qry =  "INSERT INTO delivery_managment(Item,Qty,payment_type,Date,Driver) VALUES ('" + D_item + "','" +D_qty + "','" + D_payment + "','"+ D_driver +"','"+ D_date+"')";
           pst =con.prepareStatement(qry);
           pst.execute();
             showAlertreg(); 
             
           dcost.setText("");  
           ditemcode.setText("");
           ditem.setValue("");
           dpayment.setValue("");
           dqty.setText("");
           ddriver.setValue("");
           ddate.setText("");
           
                   }catch(Exception e)
                   {
                       System.out.println(e);
                   }
        } 
    }

    @FXML
    private void clearDetails(ActionEvent event) {
        
         dcost.setText("");  
           ditemcode.setText("");
           ditem.setValue("");
           dpayment.setValue("");
           dqty.setText("");
           ddriver.setValue("");
           ddate.setText("");
        
    }

    @FXML
    private void clearnum(ActionEvent event) {
        dphone.setText("");
    }

    @FXML
    private void demo(ActionEvent event) {
     dcustid.setText("2");
        ditemcode.setText("3");
        dqty.setText("3");
        dcost.setText("450");
        ddate.setText("2016/5/6");
        
        
    }

    private void showAlertreg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void calculate(ActionEvent event) {
    }


}

