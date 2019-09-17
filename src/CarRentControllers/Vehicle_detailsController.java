
package CarRentControllers;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import CarRentCodes.VehicleDetails;
import Main.DBconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import CarRentCodes.Validations;
import CarRentCodes.Vehicle;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;



public class Vehicle_detailsController implements Initializable {
    
  Connection con=null;
  PreparedStatement pst=null;
  ResultSet rs = null;
  ResultSet rs1 = null;
  
    
    
    
ObservableList<String> typeList=FXCollections.observableArrayList("Car","Van","Jeep");   
ObservableList<String> transList=FXCollections.observableArrayList("Auto","Manual"); 
ObservableList<String> avalabilityList=FXCollections.observableArrayList("Available","Reserved"); 
ObservableList<String> conditionList=FXCollections.observableArrayList("A/C","Non A/C"); 
ObservableList<String> TankList=FXCollections.observableArrayList("Disel","Petrol"); 
       
  
    @FXML
    private TextField regiBox;
    @FXML
    private TextField makeBox;
    @FXML
    private TextField colourBox;
    @FXML
    private TextField yearBox;
    @FXML
    private TextField modelBox;
    @FXML
    private TextField seatBox;
    @FXML
    private TextField emonthBox;
    @FXML
    private TextField eweekBox;
    @FXML
    private TextField edayBox;
    @FXML
    private TextField monthBox;
    @FXML
    private TextField weekBox;
    @FXML
    private TextField dayBox;
    @FXML
    private TextField kmBox;
    @FXML
    private ComboBox<String> typeBox;
    @FXML
    private ComboBox<String> transBox;
    @FXML
    private ComboBox<String> availabileBox;
    @FXML
    private ComboBox<String> conditionBox;
    @FXML
    private ComboBox<String> tankBox;
    @FXML
    private TableView<Vehicle> table_vehicle;
    @FXML
    private TableColumn<Vehicle, String> col_reg;
    @FXML
    private TableColumn<Vehicle, String> col_make;
    @FXML
    private TableColumn<Vehicle, String> col_model;
    @FXML
    private TableColumn<Vehicle, String> col_type;
    @FXML
    private TableColumn<Vehicle, String> col_year;
    @FXML
    private TableColumn<Vehicle, String> col_trans;
    @FXML
    private TableColumn<Vehicle, String> col_avl;
    @FXML
    private TableColumn<Vehicle, String> col_condi;
    @FXML
    private TableColumn<Vehicle, String> col_colour;
    @FXML
    private TableColumn<Vehicle, String> col_seat;
    @FXML
    private TableColumn<Vehicle, String> col_day;
    @FXML
    private TableColumn<Vehicle, String> col_eday;
    @FXML
    private TableColumn<Vehicle, String> col_week;
    @FXML
    private TableColumn<Vehicle, String> col_eweek;
    @FXML
    private TableColumn<Vehicle, String> col_month;
    @FXML
    private TableColumn<Vehicle, String> col_emonth;
    @FXML
    private TableColumn<Vehicle, String> col_km;
    @FXML
    private TableColumn<Vehicle, String> col_tank;
    
    private ObservableList<Vehicle> data;
    private DBconnect conn;
    @FXML
    private TableColumn<Vehicle, String> col_fuel;
    @FXML
    private TableColumn<Vehicle, String> col_mil;
    @FXML
    private TableColumn<Vehicle, String> col_driver;
    @FXML
    private TextField fuelBox;
    @FXML
    private TextField milageBox;
    @FXML
    private TextField driver_chBox;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    
    
    
    
     public Vehicle_detailsController(){
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
    public void lettersOnlyKeyEvent(KeyEvent event){ 
        Validations.lettersOnly(event);
    }
     
     @FXML
    private void addVehicleDetails(ActionEvent event) {
        String dreg_no = regiBox.getText();
        String dmake = makeBox.getText();
        String dmodel = modelBox.getText();
        String dtype = typeBox.getValue();
        String dyear = yearBox.getText();
        String dtrans = transBox.getValue();
        String davl = availabileBox.getValue();
        String dcondi = conditionBox.getValue();
        String dcolour = colourBox.getText();
        String dseat = seatBox.getText();
        String dday = dayBox.getText();
        String deday = edayBox.getText();
        String dweek = weekBox.getText();
        String deweek = eweekBox.getText();
        String dmonth = monthBox.getText();
        String demonth = emonthBox.getText();
        String dkm = kmBox.getText();
        String dtank = tankBox.getValue();
        String fuel=fuelBox.getText();
        String driver=driver_chBox.getText();
        String milage=milageBox.getText();
        

        boolean validation = true;
        String exp = "";

        if (dreg_no.isEmpty() || dmake.isEmpty() || dmodel.isEmpty() || dtype == null || dyear.isEmpty() || dtrans == null || davl == null || dcondi == null || dcolour.isEmpty() || dseat.isEmpty() || dday.isEmpty() || deday.isEmpty() || dweek.isEmpty() || deweek.isEmpty() || dmonth.isEmpty() || demonth.isEmpty() || dkm.isEmpty() || dtank == null||fuel.isEmpty()||driver.isEmpty()||milage.isEmpty()) {
            Validations.fieldEmptyMsg();
            validation = false;

        }
        boolean validreg = Validations.regNO_valid(dreg_no);
        if (!dreg_no.isEmpty() && validreg == false) {
            validation = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(" Registration number is not valid !");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }
        if (!dday.isEmpty() && Validations.isDouble(dday) == false || !deday.isEmpty() && Validations.isDouble(deday) == false || !dweek.isEmpty() && Validations.isDouble(dweek) == false || !deweek.isEmpty() && Validations.isDouble(deweek) == false || !dmonth.isEmpty() && Validations.isDouble(dmonth) == false || !demonth.isEmpty() && Validations.isDouble(demonth) == false || !dkm.isEmpty() && Validations.isDouble(dkm) == false|| !fuel.isEmpty() && Validations.isDouble(fuel) == false|| !driver.isEmpty() && Validations.isDouble(driver) == false) {
            Validations.wrongDoubleType();
            validation = false;
        }
       
        if (validation == true) {
            VehicleDetails ad = new VehicleDetails();
            exp = ad.addVehicle(dreg_no, dmake, dmodel, dtype, dyear, dtrans, davl, dcondi, dcolour, dseat, dday, deday, dweek, deweek, dmonth, demonth, dkm, dtank,fuel,driver,milage);

            if (exp.equals("Duplicate entry '" + dreg_no + "' for key 'PRIMARY'")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You have already added one vehicle with this Registration number !");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            } else {
                String q = "SELECT * From vehicle";
                loadData(q);

                boolean nextNew = Validations.nextNewRecordConfirm("Vehicle");
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
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/vehicle_details.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }

    }
////////////////////**********************edit vehicle**************************************************************
    @FXML
    private void editVehicleDetails(ActionEvent event) {
        String dreg_no = regiBox.getText();
        String dmake = makeBox.getText();
        String dmodel = modelBox.getText();
        String dtype = typeBox.getValue();
        String dyear = yearBox.getText();
        String dtrans = transBox.getValue();
        String davl = availabileBox.getValue();
        String dcondi = conditionBox.getValue();
        String dcolour = colourBox.getText();
        String dseat = seatBox.getText();
        String dday = dayBox.getText();
        String deday = edayBox.getText();
        String dweek = weekBox.getText();
        String deweek = eweekBox.getText();
        String dmonth = monthBox.getText();
        String demonth = emonthBox.getText();
        String dkm = kmBox.getText();
        String dtank = tankBox.getValue();
        String fuel=fuelBox.getText();
        String driver=driver_chBox.getText();
        String milage=milageBox.getText();
        boolean validation = true;
        String exp = "";

        if (dreg_no.isEmpty() || dmake.isEmpty() || dmodel.isEmpty() || dtype == null || dyear.isEmpty() || dtrans == null || davl == null || dcondi == null || dcolour.isEmpty() || dseat.isEmpty() || dday.isEmpty() || deday.isEmpty() || dweek.isEmpty() || deweek.isEmpty() || dmonth.isEmpty() || demonth.isEmpty() || dkm.isEmpty() || dtank == null||fuel.isEmpty()||driver.isEmpty()||milage.isEmpty()) {
            Validations.fieldEmptyMsg();
            validation = false;

        }

        if (!dday.isEmpty() && Validations.isDouble(dday) == false || !deday.isEmpty() && Validations.isDouble(deday) == false || !dweek.isEmpty() && Validations.isDouble(dweek) == false || !deweek.isEmpty() && Validations.isDouble(deweek) == false || !dmonth.isEmpty() && Validations.isDouble(dmonth) == false || !demonth.isEmpty() && Validations.isDouble(demonth) == false || !dkm.isEmpty() && Validations.isDouble(dkm) == false|| !fuel.isEmpty() && Validations.isDouble(fuel) == false||!driver.isEmpty() && Validations.isDouble(driver) == false) {
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
                Validations.successfull_msg();
                VehicleDetails ad = new VehicleDetails();
                ad.editVehicle(dreg_no, dmake, dmodel, dtype, dyear, dtrans, davl, dcondi, dcolour, dseat, dday, deday, dweek, deweek, dmonth, demonth, dkm, dtank,fuel,driver,milage);

                String q = "SELECT * From vehicle";
                loadData(q);
                clearFeilds();

            } else {

            }

        }

    }
//*****************************************delete********************************************************************
    @FXML
    private void deleteVehicleDetails(ActionEvent event) {
        
        
        
        
        
        String dreg_no = regiBox.getText();
        String vehicle="";
         String avl="";
        boolean validation=true;
        if (dreg_no.isEmpty()) {

            Validations.Search_error();
            validation=false;
        }
        if (!dreg_no.isEmpty()) {

            try {
                String sql = "select vehi_id from rent ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                String qqq = "select availability from vehicle where reg_no='" + dreg_no + "' ";
                pst = con.prepareStatement(qqq);
                rs1 = pst.executeQuery();
                while (rs.next() && rs1.next()) {
                    vehicle = rs.getString("vehi_id");
                    avl = rs1.getString("availability");
                    System.out.println( vehicle);
                    System.out.println( avl);

                    if (dreg_no.equals(vehicle) && avl.equals("Reserved")) {
                        validation = false;
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("you have already reserved this vehicle for a customer therefore cannot delete the records !");
                        alert.setContentText("Ooops, there was an error!");

                        alert.showAndWait();
                       
                    }

                }
            } catch (Exception ex) {

                System.out.println(ex);
            }

        }
        if(validation==true){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Required!");
                        alert.setHeaderText("Do you really want to delete this?");
                        alert.setContentText("This will lose your vehicle details values and maintenace details of " + dreg_no + "!");
                        
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            Alert ale = new Alert(Alert.AlertType.INFORMATION);
                            ale.setTitle("Information Dialog");
                            ale.setHeaderText(null);
                            ale.setContentText("you have sucessfully deleted vehicle details and maintenance details of " + dreg_no + "!");

                            ale.showAndWait();

                            VehicleDetails del = new VehicleDetails();
                            del.deleteVehicle(dreg_no);
                            String q = "SELECT * From vehicle";
                            loadData(q);
                            clearFeilds();

                        } else {

                        }

        
        
        
        
        
        }
        
      

    }

 //***************************************************load vehicle******************
    public void loadData(String q) {
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            rs = con.createStatement().executeQuery(q);

            while (rs.next()) {
                data.add(new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        col_reg.setCellValueFactory(new PropertyValueFactory<>("reg_no"));
        col_make.setCellValueFactory(new PropertyValueFactory<>("make"));
        col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_trans.setCellValueFactory(new PropertyValueFactory<>("transmission"));
        col_avl.setCellValueFactory(new PropertyValueFactory<>("availability"));
        col_condi.setCellValueFactory(new PropertyValueFactory<>("condition"));
        col_colour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        col_seat.setCellValueFactory(new PropertyValueFactory<>("seats"));
        col_day.setCellValueFactory(new PropertyValueFactory<>("day"));
        col_eday.setCellValueFactory(new PropertyValueFactory<>("eday"));
        col_week.setCellValueFactory(new PropertyValueFactory<>("week"));
        col_eweek.setCellValueFactory(new PropertyValueFactory<>("eweek"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_emonth.setCellValueFactory(new PropertyValueFactory<>("emonth"));
        col_km.setCellValueFactory(new PropertyValueFactory<>("km"));
        col_tank.setCellValueFactory(new PropertyValueFactory<>("tank"));
        col_fuel.setCellValueFactory(new PropertyValueFactory<>("fuel_ch"));
        col_driver.setCellValueFactory(new PropertyValueFactory<>("driver_ch"));
        col_mil.setCellValueFactory(new PropertyValueFactory<>("milage"));

        table_vehicle.setItems(null);
        table_vehicle.setItems(data);

    }

   

    @FXML
    private void handlehMouseClicked(MouseEvent event) {
        try {
            Vehicle vehi = (Vehicle) table_vehicle.getSelectionModel().getSelectedItem();
            String sql = "select * from vehicle where reg_no= ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, vehi.getReg_no());

            rs = pst.executeQuery();

            while (rs.next()) {

                regiBox.setText(rs.getString(1));
                makeBox.setText(rs.getString(2));
                modelBox.setText(rs.getString(3));
                typeBox.setValue(rs.getString(4));
                yearBox.setText(rs.getString(5));
                transBox.setValue(rs.getString(6));
                availabileBox.setValue(rs.getString(7));
                conditionBox.setValue(rs.getString(8));
                colourBox.setText(rs.getString(9));
                seatBox.setText(rs.getString(10));
                dayBox.setText(rs.getString(11));
                edayBox.setText(rs.getString(12));
                weekBox.setText(rs.getString(13));
                eweekBox.setText(rs.getString(14));
                monthBox.setText(rs.getString(15));
                emonthBox.setText(rs.getString(16));
                kmBox.setText(rs.getString(17));
                tankBox.setValue(rs.getString(18));
                fuelBox.setText(rs.getString(19));
                driver_chBox.setText(rs.getString(20));
                milageBox.setText(rs.getString(21));

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
    
    
     public void clearFeilds() {
        regiBox.setText("");
        makeBox.setText("");
        modelBox.setText("");
        typeBox.setValue(null);
        yearBox.setText("");
        transBox.setValue(null);
        availabileBox.setValue(null);
        conditionBox.setValue(null);
        colourBox.setText("");
        seatBox.setText("");
        dayBox.setText("");
        edayBox.setText("");
        weekBox.setText("");
        eweekBox.setText("");
        monthBox.setText("");
        emonthBox.setText("");
        kmBox.setText("");
        tankBox.setValue(null);
        driver_chBox.setText("");
        fuelBox.setText("");
        milageBox.setText("");
    }

      @FXML
    private void ClearDetails(ActionEvent event) {

        clearFeilds();
    }

    @FXML
    private void LoadDetails(ActionEvent event) {
        String q = "SELECT * From vehicle";
        loadData(q);
    }
    
//********************************************search***********************************************************    
    @FXML
    private void Search_vehiReg(ActionEvent event) {

        String dreg_no = regiBox.getText();

        if (dreg_no.isEmpty()) {

            Validations.Search_error();

        } else {

            String vehi_s = "select * from vehicle where reg_no LIKE '%" + dreg_no + "%'";
            loadData(vehi_s);

        }

    }

    @FXML
    private void Search_Make(ActionEvent event) {
        String dmake = makeBox.getText();

        if (dmake.isEmpty()) {
            Validations.Search_error();

        } else {
            String make_s = "select * from vehicle where make LIKE '%" + dmake + "%'";
            loadData(make_s);

        }

    }

    @FXML
    private void Search_Type(ActionEvent event) {
        String dtype = typeBox.getValue();

        if (dtype.isEmpty()) {
            Validations.Search_error();

        } else {
            String type_s = "select * from vehicle where type LIKE '" + dtype + "'";
            loadData(type_s);

        }

    }

    @FXML
    private void Search_availability(ActionEvent event) {
        String davl = availabileBox.getValue();
        if (davl.isEmpty()) {
            Validations.Search_error();

        } else {
            String avl_s = "select * from vehicle where availability='" + davl + "'";
            loadData(avl_s);

        }

    }
    
   
    
    
    
    
    
    
    
    

    @FXML
    public void demoActionButton(ActionEvent event) {
        regiBox.setText("CAD-6969");
        makeBox.setText("BMW");
        modelBox.setText("msport");
        typeBox.setValue("Car");
        yearBox.setText("2012");
        transBox.setValue("Auto");
        availabileBox.setValue("Available");
        conditionBox.setValue("A/C");
        colourBox.setText("Black");
        seatBox.setText("4");
        dayBox.setText("6000.00");
        edayBox.setText("7000.00");
        weekBox.setText("30000.00");
        eweekBox.setText("32000.00");
        monthBox.setText("150000.000");
        emonthBox.setText("175000.00");
        kmBox.setText("500.00");
        tankBox.setValue("Full");
        driver_chBox.setText("50.00");
        fuelBox.setText("30.00");
        milageBox.setText("600");

    }
      public void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(s.format(d));
    }

    public void showTime() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
        lblTime.setText(s.format(d));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Car Rental System - Vehicle Details");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
        } catch (IOException ex) {

        }
        SubHomeController.backUrl = "/GUIs/CarRentHome.fxml";
        showDate();
        showTime();
        VehicleDetails vd=new VehicleDetails();  
        
        vd.nxt_Reserved(lblDate.getText());   
        
        typeBox.setItems(typeList);
        transBox.setItems(transList);
        availabileBox.setItems(avalabilityList);
        conditionBox.setItems(conditionList);
        tankBox.setItems(TankList);
        String q = "SELECT * From vehicle";
        loadData(q);
    }

}
