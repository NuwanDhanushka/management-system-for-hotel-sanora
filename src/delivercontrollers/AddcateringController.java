/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivercontrollers;

import Main.DBconnect;
import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Deliverycodes.DBcate;
import Deliverycodes.DBval;
import Deliverycodes.Validation;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class AddcateringController implements Initializable {
    
    private ObservableList<DBcate> data;
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;

    @FXML
    private AnchorPane nicn;
    private TextField dnic;
    @FXML
    private Button dsearch;
    @FXML
    private Button ddelete;
    @FXML
    private Button dupdate;
    private Label regerror;
    @FXML
    private TableView<DBcate> caterTable;
    @FXML
    private TableColumn<DBcate, String> tcustid;
    @FXML
    private TableColumn<DBcate, String> tcustname;
    @FXML
    private TableColumn<DBcate, String> taddress;
    @FXML
    private TableColumn<DBcate, String> tspecialmrk;
    @FXML
    private TableColumn<DBcate, Integer> tphone;
    @FXML
    private TableColumn<DBcate, Integer> tsecondphone;
    @FXML
    private TableColumn<DBcate, Integer> cphone;
    @FXML
    private TableColumn<DBcate, Integer> cextra;
    @FXML
    private Button dview;
    @FXML
    private Button dconfirm;
    @FXML
    private TextField ddate;
    @FXML
    private TextField dqty;
    @FXML
    private Button dclear;
    @FXML
    private ComboBox<String> cpackage;
    @FXML
    private Button dclear_2;
    @FXML
    private Button ddemo;
    @FXML
    private Label dcustid;
    @FXML
    private Label dcost;
    @FXML
    private Label cpackagecode;
    @FXML
    private TextField deventdate;
    @FXML
    private ComboBox<String> dexitem;
      
    @FXML
    private TableColumn<?, ?> tcustpoint;
    @FXML
    private ComboBox<String> superv;
    


      
     private final  ObservableList<String> typecpackage=FXCollections.observableArrayList("Gold","Silver","Platinum","Bronze");
     private final  ObservableList<String> typedexitem=FXCollections.observableArrayList("1","2","3","4","5","6","7");
      private final  ObservableList<String> typesuperv=FXCollections.observableArrayList("rajan","Charitha","Vimal","nuwan","sanoj");
    @FXML
    private TextField dphone;
    @FXML
    private Button ccal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        DelSubMenuListController.delListState=2;
        subHomeC.funcNamelbl.setText("Delivery System - Catering");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/deliverGUIs/DelSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/DeliverHome.fxml";
        
        cpackage.setItems(typecpackage);
        dexitem.setItems(typedexitem);
         superv.setItems(typesuperv);
    }    

    @FXML
    private void searchnum(ActionEvent event) {
        
        boolean validation = true;
        boolean nextNew;
        
        
        
         Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
           try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * From catering where NIC='"+dnic+"'");
        } catch (Exception e) {
            
               System.out.println(e);
        }
           
    }

    @FXML
    private void deletedelivery(ActionEvent event) {
          DBcate user=(DBcate)caterTable.getSelectionModel().getSelectedItem();
        String d="DELETE  FROM catering WHERE cust_id = ?";
        try{
        pst = con.prepareStatement(d);
        pst.setString(1,user.getNIC ());
        pst.execute();
        regerror.setText("Values are susscessfully Deleted!");
                       
        ObservableList<DBcate>UserSelected,allUser;
        allUser=caterTable.getItems();
        UserSelected=caterTable.getSelectionModel().getSelectedItems();
        UserSelected.forEach(allUser::remove);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void updatedelivery(ActionEvent event) {
        String pkg = cpackage.getValue();
            String xitems = dexitem.getValue();
            String qty = dqty.getText();
            String sup = superv.getValue();
            String date = ddate.getText();
            String evntdate=  deventdate.getText();
            String nic=dnic.getText();
            String costt= dcost.getText();
            
            String q3="UPDATE catering SET package='"+pkg+"',extra_items='"+xitems+"',	qty='"+qty+"',supervisor='"+sup+"',date='"+date+"',evengt_date='"+evntdate+"' WHERE 	NIC='"+nic+"'";
            try{
            pst = con.prepareStatement(q3);
            pst.execute();
            
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Are u Sure u want to update?");
            alert.showAndWait();
                 
            
          regerror.setText("Values are susscessfully Updated!");
            cpackage.setValue("");
            dexitem.setValue("");
            dqty.setText("");
            superv.setValue("");
            ddate.setText("");
            deventdate.setText("");
          dcustid.setText("");
            cpackagecode.setText("");
            dcost.setText("");
            
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
            ResultSet rs = con.createStatement().executeQuery("SELECT Cust_id,Customer_name,evengt_date,pakg,qty,Cost,supervisor,Phone_num,extra_items From catering");
             while (rs.next()) {
            data.add(new DBcate(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
          tcustid.setCellValueFactory(new PropertyValueFactory<>("custID"));
        tcustname.setCellValueFactory(new PropertyValueFactory<>("Customer_name"));
        
        taddress.setCellValueFactory(new PropertyValueFactory<>("evengt_date"));
       tspecialmrk.setCellValueFactory(new PropertyValueFactory<>("pakg"));
        
  tphone.setCellValueFactory(new PropertyValueFactory<>("qty"));
        
      tsecondphone.setCellValueFactory(new PropertyValueFactory<>("Cost"));
     tcustpoint.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
           cphone.setCellValueFactory(new PropertyValueFactory<>("Phone_num"));
            cextra.setCellValueFactory(new PropertyValueFactory<>("extra_items"));
      
        
       
        
        caterTable.setItems(null);
        caterTable.setItems(data); 
        
    }


    @FXML
    private void confirmorder(ActionEvent event) {
         
        String C_cpackage = (String) cpackage.getValue();
            String C_dexitem = (String) dexitem.getValue();
            String C_dqty = dqty.getText();
            String C_superv = (String) superv.getValue();
            String C_ddate = ddate.getText();
            String C_deventdate = deventdate.getText();
           
            
        String excep="";
        
        
        boolean validation = true;
        boolean nextNew;
        
        
        
        if(C_cpackage.isEmpty()||C_dexitem.isEmpty()||C_dqty.isEmpty()||C_superv.isEmpty()|| C_ddate.isEmpty()||C_deventdate.isEmpty() ){
            Validation.fieldEmptyMsg();
            validation = false;    
        }
       
        
        

          if(C_cpackage.isEmpty()||C_dexitem.isEmpty()||C_dqty.isEmpty()||C_superv.isEmpty()|| C_ddate.isEmpty()||C_deventdate.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            System.out.println("hello");
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to cancel this?");
            alert.setContentText("This will loss you entered values!");
              
          }else
               {
            
                   try{
           String qry =  "INSERT INTO catering(package,date,evengt_date,supervisor,qty,extra_items) VALUES ('" + C_cpackage + "','" +C_ddate + "','" + C_deventdate + "','"+C_superv +"','"+ C_dqty+"','"+C_dexitem+"'+)";
           pst =con.prepareStatement(qry);
           pst.execute();
             showAlertreg(); 
             
           dcost.setText("");  
           dcustid.setText("");
           cpackagecode.setText("");
         cpackage.setValue("");
         dexitem.setValue("");
         superv.setValue("");
           dqty.setText("");
          deventdate.setText("");
           ddate.setText("");
           
                   }catch(Exception e)
                   {
                       System.out.println(e);
                   }
        } 
    }

    @FXML
    private void clearDetails(ActionEvent event) {
         dcustid.setText("");
        cpackagecode.setText("");
        cpackage.setValue("");
        dexitem.setValue("");
        dqty.setText("");
        dcost.setText("");
        superv.setValue("");
        ddate.setText("");
        deventdate.setText("");
            
        
    }

    @FXML
    private void clearnum(ActionEvent event) {
        
    }

    @FXML
    private void demo(ActionEvent event) {
        dcustid.setText("5");
        cpackagecode.setText("3");
        dexitem.setValue("");
        dqty.setText("150");
        dcost.setText("45000");
        ddate.setText("2016/5/6");
        deventdate.setText("2016/8/8");
        
    }

    private void showAlertreg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void calculate(ActionEvent event) {
        
        String unit= dcost.getText();
         String pkg = cpackage.getValue();    
            
          Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
           try {
            ResultSet rs = con.createStatement().executeQuery("SELECT unit_Price From unit_price Item = '"+pkg+"'");
        } catch (Exception e) {
            System.out.print(e);
        }
     
           
           
    }
    
}
