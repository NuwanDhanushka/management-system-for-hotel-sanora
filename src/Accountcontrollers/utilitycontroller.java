/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accountcontrollers;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Main.DBconnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author U Computers
 */
public class utilitycontroller implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private ComboBox ubillname;
    @FXML
    private TextField ubillamount;
 
    @FXML
    private Button addBill;
    @FXML
    private Button viewbill;
    @FXML
    private DatePicker ubilldate;

    @FXML
    private TableView<tableutilitycontroller> ubilltableview;
    @FXML
    private TableColumn<tableutilitycontroller, String> uBillID;
    @FXML
    private TableColumn<tableutilitycontroller, String> uDate;
    @FXML
    private TableColumn<tableutilitycontroller, String> uName;
    @FXML
    private TableColumn<tableutilitycontroller, String> uAmount;
    private ObservableList<tableutilitycontroller> data;
    private String editbillid;
    private String modifiedDate;

    public utilitycontroller() {

    }

    @FXML
    private void ubilltableevent(MouseEvent event) {
        Connection con = DBconnect.dbconnect();

        try {
        
            tableutilitycontroller util = (tableutilitycontroller) ubilltableview.getSelectionModel().getSelectedItem();
         
            String sql = "SELECT * FROM utilitybill where uBillID = ?";

            pst = con.prepareStatement(sql);
            
            pst.setString(1, util.uBillID());

            rs = pst.executeQuery();
           
            while (rs.next()) {
                editbillid=rs.getString(1);
                String ld = rs.getString(2);
                ubilldate.setValue(LocalDate.parse(ld, DateTimeFormatter.ISO_DATE));

                ubillname.setValue(rs.getString(3));
                ubillamount.setText(rs.getString(4));

            }
        } catch (Exception e) {
            System.out.println("An error occured during the mouse click event for the Utility Bill table view! " + e);
            e.printStackTrace();
        }

    }

    @FXML
    public void addBillevent(ActionEvent event) {

        Connection con = DBconnect.dbconnect();

        if (validateFields() == true && validateAmountFeild() == true) {

            String billname = ubillname.getSelectionModel().getSelectedItem().toString();
            String billamount = ubillamount.getText();
            LocalDate Bdate = ubilldate.getValue();
            String billdate = Bdate.toString();
            try {

                String q = "INSERT INTO utilitybill(uDate,uName,uAmount)" + " VALUES ('" + billdate + "','" + billname + "','" + billamount + "')";
                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);

            }
             try {
        Date date = new Date();
          modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String q = "INSERT INTO acc(date,des,amount,type) values ('" + modifiedDate + "','" + "utility" + "','" + billamount + "','" + "expense" + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
            showbills();
        }
    }

    @FXML
    public void deleteutilityevent(ActionEvent event) {
           try {
            Connection con = DBconnect.dbconnect();
            String sql = "DELETE from utilitybill where uBillID='" + editbillid + "'";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
            editbillid=null;
            showbills();
            
        
    }

    @FXML

    public void outputbills() {

    }

    public void editubillevent(){
         if (validateFields() == true && validateAmountFeild() == true) {
        
        String editname=ubillname.getSelectionModel().getSelectedItem().toString();
        String editbillamt=ubillamount.getText();
        LocalDate Bdate = ubilldate.getValue();
            String editdate= Bdate.toString();
              try {
            Connection con = DBconnect.dbconnect();
            String q = ("UPDATE utilitybill SET uDate='" + editdate + "' ,uName='" + editname + "', uAmount='" + editbillamt + "' where uBillID='" + editbillid + "'");
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
              editbillid=null;
              showbills();
              
    
         }
    
    }  
    public void showbills() {

        try {
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From utilitybill");

            while (rs.next()) {
                data.add(new tableutilitycontroller(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        uBillID.setCellValueFactory(new PropertyValueFactory<>("uBillID"));
        uDate.setCellValueFactory(new PropertyValueFactory<>("uDate"));
        uName.setCellValueFactory(new PropertyValueFactory<>("uName"));
        uAmount.setCellValueFactory(new PropertyValueFactory<>("uAmount"));

        ubilltableview.setItems(null);
        ubilltableview.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Accounts - UTILITY BILLS");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().
                    clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        } catch (IOException ex) {
            System.out.print(ex);
        }
        SubHomeController.backUrl = "/GUIs/AccountsHome.fxml";
        // TODO
        ubillname.getItems().removeAll(ubillname.getItems());
        ubillname.getItems().addAll("ELECTRICITY", "WATER");
        ubillname.getSelectionModel().select("ELECTRICITY");
        showbills();
    }

    private boolean validateFields() {
        if (ubillamount.getText().isEmpty() || (ubilldate.getValue()).toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty  Fileds!!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields!!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAmountFeild() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(ubillamount.getText());
        if (m.find() && m.group().equals(ubillamount.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Amount Field!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Amount! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;

        }
    }
}
