
package CarRentControllers;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Main.DBconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import CarRentCodes.Maintenance;
import CarRentCodes.MaintenanceDetails;
import CarRentCodes.Validations;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;


public class Vehi_maintenanceController implements Initializable {

  Connection con=null;
  PreparedStatement pst=null;
  ResultSet rs = null;
    
    final ObservableList option = FXCollections.observableArrayList(); 
    
    @FXML
    private TextField lserviceBox;
    @FXML
    private DatePicker ldateBox;
    @FXML
    private DatePicker expBox;
    @FXML
    private DatePicker ndateBox;
    @FXML
    private TextField intervalBox;
    @FXML
    private ComboBox<String> regiBox;
    @FXML
    private TextField paidBox;
    @FXML
    private TextField monthBox;
    @FXML
    private TableView<Maintenance> table_maintenance;
    @FXML
    private TableColumn<Maintenance, String> col_regno;
    private TableColumn<Maintenance, String> col_mlservice;
    @FXML
    private TableColumn<Maintenance, String> col_interval;
    @FXML
    private TableColumn<Maintenance, String> col_month;
    @FXML
    private TableColumn<Maintenance, String> col_lsDate;
    @FXML
    private TableColumn<Maintenance, String> col_paid;
    @FXML
    private TableColumn<Maintenance, String> col_nsDate;
    @FXML
    private TableColumn<Maintenance, String> col_expDate;

    private ObservableList<Maintenance> data;
    private DBconnect conn;
    @FXML
    private TableColumn<Maintenance, String> col_mservice;
    
     public Vehi_maintenanceController(){
        con=DBconnect.dbconnect();
    }
     
      @FXML
      public void integersOnlyKeyEvent(KeyEvent event){ 
        Validations.integersOnly(event);
    }
    @FXML
        public void  positiveOnlyKeyEvent(KeyEvent event){ 
        Validations.positiveOnly(event);
        }
     
     
     
      
     @FXML
    private void addMaintenanceDetails(ActionEvent event) {

        LocalDate ldate = ldateBox.getValue();
        LocalDate ndate = ndateBox.getValue();
        LocalDate expdate = expBox.getValue();
        String dvehi_reg = regiBox.getValue();
        String dml_service = lserviceBox.getText();
        String dinterval_ml = intervalBox.getText();
        String dmonth = monthBox.getText();
        String da_paid = paidBox.getText();
        String dlsDate;
        String dnsDate;
        String dexpDate;

        boolean validation = true;
        String exp = "";

        if (dvehi_reg==null || dml_service.isEmpty() || ldate == null || ndate == null || expdate == null || dinterval_ml.isEmpty() || dmonth.isEmpty()) {
            Validations.fieldEmptyMsg();
            validation = false;

        }

        boolean validreg = Validations.regNO_valid(dvehi_reg);
        if (!dvehi_reg.isEmpty() && validreg == false) {
            validation = false;
            String error = " Registration number is not valid !";
            Validations.WrongMsg(error);

        }

        if (ndate != null) {
            boolean Datevali = Validations.Datevali(ldate, ndate);
            if (Datevali == false) {
                validation = false;
                String error = "Next services date should be greater than to last service date!";
                Validations.WrongMsg(error);

            }

        }
        if (!da_paid.isEmpty() && Validations.isDouble(da_paid) == false) {
            Validations.wrongDoubleType();
            validation = false;

        }
        if (validation == true) {
            MaintenanceDetails ad = new MaintenanceDetails();
            dlsDate = ldate.toString();
            dnsDate = ndate.toString();
            dexpDate = expdate.toString();
            exp = ad.addMaintenance(dvehi_reg, dml_service, dinterval_ml, dmonth, dlsDate, da_paid, dnsDate, dexpDate);

            if (exp.equals("Duplicate entry '" + dvehi_reg + "' for key 'PRIMARY'")) {
                String error = "You have already added one Maintenance details with this Registration number !";
                Validations.WrongMsg(error);

            }
      /*      else if (exp.equals("Cannot add or update a child row: a foreign key constraint fails (`itpproject`.`maintenance`, CONSTRAINT `fk_main` FOREIGN KEY (`vehi_reg`) REFERENCES `vehicle` (`reg_no`) ON DELETE CASCADE ON UPDATE NO ACTION)")) {
                String error = "There is no such a vehicle for " + dvehi_reg + " Registration number !";
                Validations.WrongMsg(error);
            } */
            else {
                String query = "SELECT * From maintenance";
                
                loadData(query);

                boolean nextNew = Validations.nextNewRecordConfirm("Vehicle Maintenance");
                if (!nextNew) {
                    SubHomeController subHomeC = MainHomeController.subHome.getController();

                    subHomeC.funcNamelbl.setText("Vehicle");
                    subHomeC.subContent.getChildren().clear();
                    try {
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CarRentHome.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    subHomeC.subMenuList.getChildren().clear();
                    try {
                        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    subHomeC.backBtn.setVisible(false);
                } else {
                    SubHomeController subHomeC = MainHomeController.subHome.getController();

                    subHomeC.subContent.getChildren().clear();
                    try {
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/vehi_maintenance.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }

    }
    //****************************************************edit********************************************

    @FXML
    private void editMaintenanceDetails(ActionEvent event) {

        LocalDate ldate = ldateBox.getValue();
        LocalDate ndate = ndateBox.getValue();
        LocalDate expdate = expBox.getValue();

        String dvehi_reg = regiBox.getValue();
        String dml_service = lserviceBox.getText();
        String dinterval_ml = intervalBox.getText();
        String dmonth = monthBox.getText();
        String dlsDate = ldate.toString();
        String da_paid = paidBox.getText();
        String dnsDate = ndate.toString();
        String dexpDate = expdate.toString();
        boolean validation = true;
        String exp = "";
        if (dvehi_reg==null|| dml_service.isEmpty() || ldate == null || ndate == null || expdate == null || dinterval_ml.isEmpty() || dmonth.isEmpty()) {
            Validations.fieldEmptyMsg();
            validation = false;

        }
        if (ndate != null) {
            boolean Datevali = Validations.Datevali(ldate, ndate);
            if (Datevali == false) {
                validation = false;
                String error = "Next services date should be lesser than to last service date!";
                Validations.WrongMsg(error);

            }

        }
        if (!da_paid.isEmpty() && Validations.isDouble(da_paid) == false) {
            Validations.wrongDoubleType();
            validation = false;

        }
        if (validation == true) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to edit this?");
            alert.setContentText("This will lose your previous entered values!");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                MaintenanceDetails ed = new MaintenanceDetails();
                dlsDate = ldate.toString();
                dnsDate = ndate.toString();
                dexpDate = expdate.toString();
                ed.editMaintenance(dvehi_reg, dml_service, dinterval_ml, dmonth, dlsDate, da_paid, dnsDate, dexpDate);
                Validations.successfull_msg();
                
                String query = "SELECT * From maintenance";                
                loadData(query);
            } else {

            }

        }

    }
    ///*****************************************************delete*******************************************  

    @FXML
    private void deleteMaintenanceDetails(ActionEvent event) {
        String dreg_no = regiBox.getValue();

        if (dreg_no==null) {

            Validations.Search_error();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to delete this?");
            alert.setContentText("This will lose your maintenace details of " + dreg_no + "!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert ale = new Alert(Alert.AlertType.INFORMATION);
                ale.setTitle("Information Dialog");
                ale.setHeaderText(null);
                ale.setContentText("you have sucessfully deleted  maintenance details of " + dreg_no + "!");
                ale.showAndWait();

                MaintenanceDetails del = new MaintenanceDetails();
                del.deleteMaintenance(dreg_no);
               String query = "SELECT * From maintenance";                
                loadData(query);
                clearFeilds();

            } else {

            }

        }

    }
             String query = "SELECT * From maintenance";
    public void loadData(String query) {
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            rs = con.createStatement().executeQuery(query );

            while (rs.next()) {
                data.add(new Maintenance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        catch (Exception ex) {
            System.err.println("Error" + ex);
        }
        col_regno.setCellValueFactory(new PropertyValueFactory<>("vehi_reg"));
        col_mservice.setCellValueFactory(new PropertyValueFactory<>("m_service"));
        col_interval.setCellValueFactory(new PropertyValueFactory<>("interval_ml"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_lsDate.setCellValueFactory(new PropertyValueFactory<>("lsDate"));
        col_paid.setCellValueFactory(new PropertyValueFactory<>("a_paid"));
        col_nsDate.setCellValueFactory(new PropertyValueFactory<>("nsDate"));
        col_expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        table_maintenance.setItems(null);
        table_maintenance.setItems(data);

    }

    @FXML
    private void main_MouseClicked(MouseEvent event) {

        try {

            Maintenance ma = (Maintenance) table_maintenance.getSelectionModel().getSelectedItem();
            System.out.println("doneee2");
            String q = "SELECT * FROM maintenance WHERE vehi_reg= ?";

            pst = con.prepareStatement(q);
            pst.setString(1, ma.getVehi_reg());

            rs = pst.executeQuery();

            while (rs.next()) {

                regiBox.setValue(rs.getString(1));
                lserviceBox.setText(rs.getString(2));
                intervalBox.setText(rs.getString(3));

                monthBox.setText(rs.getString(4));
                String ld = rs.getString(5);
                ldateBox.setValue(LocalDate.parse(ld, DateTimeFormatter.ISO_DATE));

                paidBox.setText(rs.getString(6));
                String nd = rs.getString(7);
                ndateBox.setValue(LocalDate.parse(nd, DateTimeFormatter.ISO_DATE));

                String ed = rs.getString(8);
                expBox.setValue(LocalDate.parse(ed, DateTimeFormatter.ISO_DATE));

            }
            pst.close();
            rs.close();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("please select a row !");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //***********************************Search*******************************************************************
    @FXML
    private void Search_vehiReg(ActionEvent event) {

        String dreg_no = regiBox.getValue();
        System.out.println(dreg_no);
        if (dreg_no==null) {

            Validations.Search_error();

        } else {
            String vehi_s = "select * from maintenance where vehi_reg='" + dreg_no + "'";
            loadData(vehi_s);

        }

    }
   
    
    //******************************************************************************************************************
      public void fill_combo() {
        String query = "select reg_no from vehicle ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                option.add(rs.getString("reg_no"));
            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }

    }
    public void clearFeilds() {
        regiBox.setValue(null);
        lserviceBox.setText("");
        intervalBox.setText("");
        monthBox.setText("");
        ldateBox.setValue(null);
        paidBox.setText("");
        ndateBox.setValue(null);
        expBox.setValue(null);

    }
    @FXML
    private void demo(ActionEvent event) {
       
        lserviceBox.setText("20000");
        intervalBox.setText("30000");
        monthBox.setText("6");
        
        paidBox.setText("20000.00");
        ndateBox.setValue(null);
        expBox.setValue(null);
    }

    @FXML
    private void Clear_details(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    private void Load_details(ActionEvent event) {
        String query = "SELECT * From maintenance";                
        loadData(query);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Car Rental System - Maintenance Details");
        subHomeC.backBtn.setVisible(true);
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
        } catch (IOException ex) {

        }
        SubHomeController.backUrl = "/GUIs/CarRentHome.fxml";
        
        fill_combo();
        regiBox.setItems(option);
        String query = "SELECT * From maintenance";                
        loadData(query);
        
    }

}
