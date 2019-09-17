
package CarRentControllers;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Main.DBconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import CarRentCodes.Rent;
import CarRentCodes.RentDetails;
import CarRentCodes.Validations;
import CarRentCodes.Vehicle;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;


public class Rent_vehicleController implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ObservableList<String> vtypeList = FXCollections.observableArrayList("Car", "Van", "Jeep");
    ObservableList<String> rentList = FXCollections.observableArrayList("KM", "Day");
    ObservableList<String> fuelList = FXCollections.observableArrayList("withFuel", "withoutFuel");
    
    final ObservableList option = FXCollections.observableArrayList();
    final ObservableList option_2 = FXCollections.observableArrayList();
  
    
    
    @FXML
    private Label bookBox;
    @FXML
    private ComboBox<String> custBox;
    @FXML
    private DatePicker BkBox;
    @FXML
    private DatePicker reBox;
    @FXML
    private ComboBox<String> ptypeBox;
    

    @FXML
    private ComboBox<String> driverBox;
    @FXML
    private TextField depoBox;
    @FXML
    private Label vehiBox;
    @FXML
    private TableView<Rent> table_rent;
    @FXML
    private TableColumn<Rent, String> col_bkno;
    @FXML
    private TableColumn<Rent, String> col_custID;
    @FXML
    private TableColumn<Rent, String> col_bkdate;
    @FXML
    private TableColumn<Rent, String> col_rdate;
    @FXML
    private TableColumn<Rent, String> col_pktype;
    @FXML
    private TableColumn<Rent, String> col_vehiNo;
    @FXML
    private TableColumn<Rent, String> col_depo;
    @FXML
    private TableColumn<Rent, String> col_dr;

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

    private ObservableList<Rent> data;
    private ObservableList<Vehicle> dataV;
    private DBconnect conn;

    @FXML
    private ComboBox<String> vtypeBox;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private TableColumn<Rent, String> col_status;
    @FXML
    private TableColumn<Rent, String>col_fuel;
   
    @FXML
    private ComboBox<String> fuelBox;
    

    public Rent_vehicleController() {
        con = DBconnect.dbconnect();
    }

    @FXML
    public void positiveOnlyKeyEvent(KeyEvent event) {
        Validations.positiveOnly(event);
    }
   

    @FXML
    private void addRentDetails(ActionEvent event) {
        LocalDate Bkdate = BkBox.getValue();
        LocalDate Redate = reBox.getValue();

        String dbook_no = bookBox.getText();
        String dcust_id = custBox.getValue();

        String dtype = ptypeBox.getValue();
        String ddeposite = depoBox.getText();
        String dvehi_id = vehiBox.getText();
        String ddriver_id = driverBox.getValue();
        String dfuel=fuelBox.getValue();
        
        if(ddriver_id==null){
            
            ddriver_id="0";
        }
        
        
        boolean validation = true;
        String exp = "";
        System.out.println(ddriver_id);
        if (dbook_no.isEmpty() || dcust_id == null || Bkdate == null || Redate == null || dtype == null || ddeposite.isEmpty() || dvehi_id.isEmpty() ||dfuel==null) {

            Validations.fieldEmptyMsg();
            validation = false;
        }
        if (!ddeposite.isEmpty() && Validations.isDouble(ddeposite) == false) {
            Validations.wrongDoubleType();
            validation = false;

        }
        if (Redate != null) {
            boolean Datevali = Validations.Datevali(Bkdate, Redate);
            if (Datevali == false) {
                validation = false;
                String error = "Return date should be greater than to Booking  date!";
                Validations.WrongMsg(error);

            }

        }
        if (Bkdate != null) {
            String date = lblDate.getText();
            LocalDate systemDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

            boolean Datevali = Validations.Datevali(systemDate, Bkdate);
            if (Datevali == false) {
                validation = false;
                String error = "Booking  date cannot be a past!";
                Validations.WrongMsg(error);

            }

        }

        if (validation == true) {

            String dBdate = Bkdate.toString();
            String dRdate = Redate.toString();
            RentDetails rt = new RentDetails();
            
            exp=rt.addRent(dbook_no, dcust_id, dBdate, dRdate, dtype, ddeposite, dvehi_id, ddriver_id,dfuel);
            
            if (exp.equals("Duplicate entry '"+dbook_no+"' for key 'book_no'")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You have already added one vehicle rent record with this Booking number !");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }
            
            
            
            else{       
            
           
             option_2.clear();
            fill_availble_drivers();
            driverBox.setItems(option_2);
            String qu = "SELECT * From rent";
            loadData(qu);
            clearFeilds();
            loadBook_no();

            boolean nextNew = Validations.nextNewRecordConfirm("Vehicle Rent");
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

            }
        }
        }
    }
//************************************************edit**************************************
    @FXML
    private void editRentDetails(ActionEvent event) {
        LocalDate Bkdate = BkBox.getValue();
        LocalDate Redate = reBox.getValue();

        String dbook_no = bookBox.getText();
        String dcust_id = custBox.getValue();
        String dBdate = Bkdate.toString();
        String dRdate = Redate.toString();
        String dtype = ptypeBox.getValue();
        String ddeposite = depoBox.getText();
        String dvehi_id = vehiBox.getText();
        String ddriver_id = driverBox.getValue();
        String dfuel=fuelBox.getValue();
        
        boolean validation = true;
        
        if(ddriver_id==null){
            
            ddriver_id="0";
        }
        
         if (dbook_no.isEmpty() || dcust_id == null || Bkdate == null || Redate == null || dtype == null || ddeposite.isEmpty() || dvehi_id.isEmpty() ||dfuel==null) {

            Validations.fieldEmptyMsg();
            validation = false;
        }
        if (!ddeposite.isEmpty() && Validations.isDouble(ddeposite) == false) {
            Validations.wrongDoubleType();
            validation = false;

        }
        if (Redate != null) {
            boolean Datevali = Validations.Datevali(Bkdate, Redate);
            if (Datevali == false) {
                validation = false;
                String error = "Return date should be greater than to Booking  date!";
                Validations.WrongMsg(error);

            }

        }
        if (Bkdate != null) {
            String date = lblDate.getText();
            LocalDate systemDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

            boolean Datevali = Validations.Datevali(systemDate, Bkdate);
            if (Datevali == false) {
                validation = false;
                String error = "Booking  date cannot be a past!";
                Validations.WrongMsg(error);

            }

        }

        if (validation == true) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to edit this?");
            alert.setContentText("This will lose your previous entered values!");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                RentDetails rt = new RentDetails();
                rt.editRent(dbook_no, dcust_id, dBdate, dRdate, dtype, ddeposite, dvehi_id, ddriver_id,dfuel);
                  option_2.clear();
                fill_availble_drivers();
                driverBox.setItems(option_2);
                String qu = "SELECT * From rent";
                loadData(qu);
                clearFeilds();
                loadBook_no();
            } else {

            }

        }

    }
//**************************************************delete***********************************************************
    @FXML
    private void deleteRentDetails(ActionEvent event) {
        String dbook_no = bookBox.getText();
        String dvehi_id = vehiBox.getText();
        String ddriver_id = driverBox.getValue();

        if (dbook_no.isEmpty()) {

            Validations.Search_error();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to delete this?");
            alert.setContentText("This will lose your Rent details of " + dbook_no + "!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert ale = new Alert(Alert.AlertType.INFORMATION);
                ale.setTitle("Information Dialog");
                ale.setHeaderText(null);
                ale.setContentText("you have sucessfully deleted rent details of  " + dbook_no + "!");
                ale.showAndWait();

                RentDetails del = new RentDetails();
                del.deleterent(dbook_no, dvehi_id,ddriver_id);
                  option_2.clear();
                fill_availble_drivers();
                driverBox.setItems(option_2);
                String qu = "SELECT * From rent";
                loadData(qu);
                clearFeilds();
                loadBook_no();
            } else {

            }
        }

    }
//******************************************load rent**************************************************
    public void loadData(String d) {
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            rs = con.createStatement().executeQuery(d);

            while (rs.next()) {
                data.add(new Rent(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        col_bkno.setCellValueFactory(new PropertyValueFactory<>("book_no"));
        col_custID.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        col_bkdate.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
        col_rdate.setCellValueFactory(new PropertyValueFactory<>("Rdate"));
        col_pktype.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_depo.setCellValueFactory(new PropertyValueFactory<>("deposite"));
        col_vehiNo.setCellValueFactory(new PropertyValueFactory<>("vehi_id"));
        col_dr.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
        col_fuel.setCellValueFactory(new PropertyValueFactory<>("fuel"));
  
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        table_rent.setItems(null);
        table_rent.setItems(data);

    }

    @FXML
    private void rent_MouseClicked(MouseEvent event) {
        try {

            Rent ret = (Rent) table_rent.getSelectionModel().getSelectedItem();
            String sql = "select * from rent where book_no= ?";

            pst = con.prepareStatement(sql);
            pst.setString(1, ret.getBook_no());
            rs = pst.executeQuery();

            while (rs.next()) {

                bookBox.setText(rs.getString(2));
                custBox.setValue(rs.getString(3));

                String bk = rs.getString(4);
                BkBox.setValue(LocalDate.parse(bk, DateTimeFormatter.ISO_DATE));

                String re = rs.getString(5);
                reBox.setValue(LocalDate.parse(re, DateTimeFormatter.ISO_DATE));

                ptypeBox.setValue(rs.getString(6));

                depoBox.setText(rs.getString(7));
                vehiBox.setText(rs.getString(8));
                driverBox.setValue(rs.getString(9));
                fuelBox.setValue(rs.getString(10));
                
                

            }

        }
        catch(NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("please select a row !");
                alert.setContentText("Ooops, there was an error!");
                 alert.showAndWait();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
    
//******************************************************vehicle load**********************************************    
     String q = "SELECT * From vehicle";

    public void vehicle_loadData(String q) {
        try {
            Connection con = conn.dbconnect();
            dataV = FXCollections.observableArrayList();
            rs = con.createStatement().executeQuery(q);

            while (rs.next()) {
                dataV.add(new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21)));
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
    
        
        table_vehicle.setItems(null);
        table_vehicle.setItems(dataV);

    }
        @FXML
    private void vehicle_MouseClicked(MouseEvent event) {
        try {
            Vehicle vehi = (Vehicle) table_vehicle.getSelectionModel().getSelectedItem();
            String sql = "select * from vehicle where reg_no= ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, vehi.getReg_no());

            rs = pst.executeQuery();

            while (rs.next()) {

                vehiBox.setText(rs.getString(1));

            }
            pst.close();
            rs.close();

        }
        catch(NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("please select a row !");
                alert.setContentText("Ooops, there was an error!");
                 alert.showAndWait();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
//********************************search*************************************************************
    @FXML
    private void Search_bookdate(ActionEvent event) {

        LocalDate Bkdate = BkBox.getValue();

        if (Bkdate == null) {

            Validations.Search_error();

        } else {
            String dBdate = Bkdate.toString();
            String rent_s = "select * from rent where Bdate ='" + dBdate + "'";
            loadData(rent_s);

        }

    }

    @FXML
    private void Search_returnDate(ActionEvent event) {

        LocalDate Redate = reBox.getValue();

        if (Redate == null) {

            Validations.Search_error();

        } else {
            String dRdate = Redate.toString();
            String rent_s = "select * from rent where Rdate ='" + dRdate + "'";
            loadData(rent_s);

        }

    }

    @FXML
    private void Search_pType(ActionEvent event) {
        String dtype = ptypeBox.getValue();
        
        if (dtype == null) {
            Validations.Search_error();

        } else {
            String make_s = "select * from rent where type='" + dtype + "' ";
            loadData(make_s);

        }

    }

    @FXML
    private void Search_vType(ActionEvent event) {
        String dtype = vtypeBox.getValue();
        String dvehi = "Available";

        if (dtype == null) {
            Validations.Search_error();

        } else {
            try {
                String type_s = "select * from vehicle where type = '" + dtype + "' and availability='" + dvehi + "' ";
                vehicle_loadData(type_s);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

     @FXML
    private void Clear_details(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    private void Load_details(ActionEvent event) {
     String qu = "SELECT * From rent";
     loadData(qu);
    }
    //***********************************************************************************************************
    
    public void fill_availble_drivers() {
    

        String date = lblDate.getText();
 
       String  query="select driver from driver d,attendance_mark a where  d.driver=a.E_id and d.in_dr=1 and a.Date='" + date + "' and a.Out_time is null   ";
     
      //String query="select driver from driver where in_dr=1";
      
      try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                       
                        String d  = rs.getString("driver");
                      
              option_2.add(rs.getString("driver"));
                                   
               
            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }

    }   
       

    
    
   

    public void clearFeilds() {

        loadBook_no();
        custBox.setValue(null);
        BkBox.setValue(null);
        reBox.setValue(null);
        ptypeBox.setValue(null);
        vtypeBox.setValue(null);
        depoBox.setText("");
        vehiBox.setText("");
        driverBox.setValue("0");
        fuelBox.setValue("");

    }

    public void not_returned() {
        String sys = lblDate.getText();
        RentDetails rd = new RentDetails();
        rd.load_exceed(sys);

    }

    public void fill_combo() {
        String query = "select custID from customer ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                option.add(rs.getString("custID"));
                
            }
        } catch (SQLException ex) {

            System.out.println(ex);
        }

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

    public void loadBook_no() {
        try {
            Statement s = (Statement) DBconnect.dbconnect().createStatement();
            rs = s.executeQuery("SELECT bkID from rent ORDER BY bkID DESC");
            rs.first();
            int no = Integer.parseInt(rs.getString(1));
            String previous = Integer.toString(no);
            
            String next = "SVRB-" + previous;
            System.out.println(next);
            bookBox.setText(next);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Car Rental System -  Rental Details");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
        } catch (IOException ex) {

        }
        SubHomeController.backUrl = "/GUIs/CarRentHome.fxml";
        vtypeBox.setItems(vtypeList);
        ptypeBox.setItems(rentList);
        fuelBox.setItems(fuelList);
        fill_combo();
       
        custBox.setItems(option);
       
        loadBook_no();  
        showDate();
        showTime();
        fill_availble_drivers();
        driverBox.setItems(option_2);
        not_returned();
        String qu = "SELECT * From rent";
        loadData(qu);
      

    }

}
