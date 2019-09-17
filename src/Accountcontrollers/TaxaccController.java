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

/**
 * FXML Controller class
 *
 * @author U Computers
 */
public class TaxaccController implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private ComboBox taxnamecombo;
    @FXML
    private TextField taxpercentext;
    @FXML
    private Label taxviewnbt;
    @FXML
    private Label taxviewvat;

    @FXML
    private TableView<tabletaxcontroller> taxtable;

    @FXML
    private TableColumn<tabletaxcontroller, String> taxname;
    @FXML
    private TableColumn<tabletaxcontroller, String> taxpercen;

    private ObservableList<tabletaxcontroller> data;
    @FXML
    private Button viewtax;
    @FXML
    private Button viewrates;
    @FXML
    private Button edittax;

    @FXML
    private Button paytax;
    double am = 0;
    double amt = 0;
    double nbt = 0;
    double vat = 0;
    double nbtamt = 0;
    double vatamt = 0;
    double displaynbt = 0;
    double displayvat = 0;
    double displaytotalnbt = 0;
    double displaytotalvat = 0;
    String modifiedDate;

    @FXML
    public void edittaxbuttonevent(ActionEvent event) {

        String x = taxnamecombo.getSelectionModel().getSelectedItem().toString();
        String y = taxpercentext.getText();
         if( validateFields()==true && validateAmountFeild()==true){
        try {
            Connection con = DBconnect.dbconnect();
            String q = ("UPDATE tax SET taxpercen='" + y + "' where taxname='" + x + "'");
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        updatetaxtableview();
         }
    }

    @FXML
    public void updatetaxtableview() {

        try {
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From tax");

            while (rs.next()) {
                data.add(new tabletaxcontroller(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        taxname.setCellValueFactory(new PropertyValueFactory<>("taxname"));
        taxpercen.setCellValueFactory(new PropertyValueFactory<>("taxpercen"));

        taxtable.setItems(null);
        taxtable.setItems(data);

    }
@FXML
public void processtax(){
         taxviewnbt.setText("");
        taxviewvat.setText("");
        try {
            Connection con = DBconnect.dbconnect();

            ResultSet rs = con.createStatement().executeQuery("SELECT * From pendingtax");
            am = 0;
            amt = 0;
            nbt = 0;
            vat = 0;
            while (rs.next()) {
                am = Double.parseDouble(rs.getString("ptaxamount"));
                amt = amt + am;
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection con = DBconnect.dbconnect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From tax");

            while (rs.next()) {
                if (rs.getString("taxname").equals("NBT")) {
                    nbt = Double.parseDouble(rs.getString("taxpercen"));
                } else {
                    vat = Double.parseDouble(rs.getString("taxpercen"));

                }
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        double nbtamt = amt * (nbt / 100.0);
        double vatamt = amt * (vat / 100.0);
        Date date = new Date();
          modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        try {
            
            Connection con = DBconnect.dbconnect();
            String q = "INSERT INTO taxentries(date,name,amount) values ('" + modifiedDate + "','" + "NBT" + "','" + nbtamt + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            Connection con = DBconnect.dbconnect();
            String q = "INSERT INTO taxentries(date,name,amount) values ('" + modifiedDate + "','" + "VAT" + "','" + vatamt + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        displaynbt = 0;
        displayvat = 0;
       displaytotalnbt = 0;
        displaytotalvat = 0;

        try {

            Connection con = DBconnect.dbconnect();

            ResultSet rs = con.createStatement().executeQuery("SELECT * From taxentries");

            while (rs.next()) {
                String z = rs.getString("name");
                if (z.equals("NBT")) {
                    displaynbt = Double.parseDouble(rs.getString("amount"));
                    displaytotalnbt = displaytotalnbt + displaynbt;
                } else {
                    displayvat = Double.parseDouble(rs.getString("amount"));
                    displaytotalvat = displaytotalvat + displayvat;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
          try {
            Connection con = DBconnect.dbconnect();
            String sql = "DELETE from pendingtax ";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }


}
    @FXML
    public void paytaxbuttonaction(ActionEvent event) {
        processtax();
      
        try {
            Connection con = DBconnect.dbconnect();
            String sql = "DELETE from taxentries ";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        double dtotal = displaytotalvat + displaytotalnbt;

        try {
            Connection con = DBconnect.dbconnect();
            String q = "INSERT INTO paidtax(name,amoun) values ('" + "NBT" + "','" + displaytotalnbt + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            Connection con = DBconnect.dbconnect();
            String q = "INSERT INTO paidtax(name,amoun) values ('" + "VAT" + "','" + displaytotalvat + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            Connection con = DBconnect.dbconnect();
            String q = "INSERT INTO acc(date,des,amount,type) values ('" + modifiedDate + "','" + "tax" + "','" + dtotal + "','" + "expense" + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        taxviewnbt.setText(" TOTAL NBT PAID " + displaytotalnbt + "\n \n TOTAL NBT TO PAY IS   Rs. 0");
        taxviewvat.setText(" TOTAL VAT PAID " + displaytotalvat + "\n \n TOTAL VAT TO PAY IS   Rs. 0");

    }

    @FXML
    public void loaddatanew(ActionEvent event) {
        updatetaxtableview();

    }

    public TaxaccController() {
        //initComponents();

    }

    @FXML
    public void viewpendingtax(ActionEvent event) throws SQLException {
        
        processtax();
         taxviewnbt.setText( "TOTAL NBT TO PAY RS."+String.valueOf(displaytotalnbt));
        taxviewvat.setText("TOTAL VAT TO PAY RS."+String.valueOf( displaytotalvat));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Accounts - Tax Management");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        } catch (IOException ex) {
            System.out.print(ex);
        }
        SubHomeController.backUrl = "/GUIs/AccountsHome.fxml";
        // TODO
        taxnamecombo.getItems().removeAll(taxnamecombo.getItems());
        taxnamecombo.getItems().addAll("NBT", "VAT");
        taxnamecombo.getSelectionModel().select("NBT");
          updatetaxtableview();
    }
      private boolean validateFields(){
        if(taxpercentext.getText().isEmpty()  ){
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Empty  Fileds!!");
           alert.setHeaderText(null);
           alert.setContentText("Please fill all the fields!!");
           alert.showAndWait();
           return false;
        }
        
       else
            return true;
    }
        private boolean validateAmountFeild(){
        Pattern p=Pattern.compile("[0-9][0-9][0-9][.][0-9]+");
        Matcher m=p.matcher(taxpercentext.getText());
        if(m.find()&& m.group().equals(taxpercentext.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Tax Percentage feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Amount! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;
        
    }
    }
    //private void initComponents() {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
