
package CarRentControllers;


//import Controllers.CustHomeController;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import CarRentCodes.Payment_Day;
import CarRentCodes.Payment_dayDetails;
import CarRentCodes.Rent;
import CarRentCodes.RentDetails;
import CarRentCodes.Validations;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class Payment_dayController implements Initializable {

    
  Connection con=null;
  PreparedStatement pst=null;
  ResultSet rs = null;
    
    
   @FXML
    private Label bookedBox; 
    @FXML
    private Label exBox;
    @FXML
    private TextField damageBox;
    @FXML
    private Label driverBox;
    @FXML
    private Label bill_L;
    @FXML
    private TableView<Payment_Day> table_day;
    @FXML
    private TableColumn<Payment_Day, String> col_bill;
    @FXML
    private TableColumn<Payment_Day, String> col_booked;
    @FXML
    private TableColumn<Payment_Day, String> col_date;
    @FXML
    private TableColumn<Payment_Day, String> col_exday;
    @FXML
    private TableColumn<Payment_Day, String> col_damage;
    @FXML
    private TableColumn<Payment_Day, String> col_driver;
    @FXML
    private TableColumn<Payment_Day, String> col_tot;
     @FXML
    private TableColumn<Payment_Day, String> col_km;
     @FXML
    private TableColumn<Payment_Day, String> col_time;
    @FXML
    private TableColumn<Payment_Day, String> col_custid;
    @FXML
    private Label kmBox;
    @FXML
    private Label total_L;
    
    private ObservableList<Payment_Day> data;
    private ObservableList<Rent> dataR;   
    final ObservableList option = FXCollections.observableArrayList();
    private DBconnect conn;
    @FXML
    private Label return_L;
    @FXML
    private Label booked_L;
    @FXML
    private Label vehi_L;
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
    private TableColumn<Rent, String> col_status;
    
    @FXML
    private ComboBox<String> custBox;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label type_L;
    @FXML
    private Button btnKM;
    @FXML
    private Button btnDay;
    @FXML
    private TableColumn<Rent, String> col_fuel;
    @FXML
    private TextField milBox;
    @FXML
    private Label fuelBox;
    @FXML
    private TableColumn<Payment_Day, String> col_ful;
    @FXML
    private TableColumn<Payment_Day, String> col_mil;
    @FXML
    private TextArea desBox;
    @FXML
    private TextField addBox;
    @FXML
    private TableColumn<Payment_Day, String> col_info;
    @FXML
    private TableColumn<Payment_Day, String> col_des;
  
    
   
     public Payment_dayController(){
        con=DBconnect.dbconnect();
    }
        @FXML
        public void  positiveOnlyKeyEvent(KeyEvent event){ 
        Validations.positiveOnly(event);
        }
        @FXML
      public void integersOnlyKeyEvent(KeyEvent event){ 
        Validations.integersOnly(event);
    }
     
       @FXML
    private void addPayment_day(ActionEvent event) {

        String dbill_no = bill_L.getText();
        String dbooked_no = bookedBox.getText();
        String dbill_date = lblDate.getText();
        String dbill_time = lblTime.getText();
        String dex_day = exBox.getText();
        String dnof_km = kmBox.getText();
        String ddamage_ch = damageBox.getText();
        String ddriver_ch = driverBox.getText();
        String dtotal = total_L.getText();
        String dvehi = vehi_L.getText();
        String dcust = custBox.getValue();
        String dmil=milBox.getText();
        String dfuel=fuelBox.getText();
        String dcust_info=addBox.getText();
        String ddes=desBox.getText();
        boolean validation = true;
        String exp = "";

        if (dcust_info.isEmpty()||dvehi.isEmpty() || dbill_no.isEmpty() || dbooked_no.isEmpty() || dbill_date.isEmpty() || dbill_time.isEmpty() || dex_day.isEmpty() || dnof_km.isEmpty() || ddamage_ch.isEmpty() || ddriver_ch.isEmpty() || dtotal.isEmpty() || dcust == null|| dmil.isEmpty() || dfuel.isEmpty()) {

            Validations.fieldEmptyMsg();
            validation = false;
        }
        
        
        

        if (!dcust_info.isEmpty() && Validations.isDouble(dtotal) == false  ||!dex_day.isEmpty() && Validations.isInteger(dex_day) == false || !dtotal.isEmpty() && Validations.isDouble(dtotal) == false || !ddamage_ch.isEmpty() && Validations.isDouble(ddamage_ch) == false || !ddriver_ch.isEmpty() && Validations.isDouble(ddriver_ch) == false) {

            Validations.wrongDoubleType();
            validation = false;
        }
        if(!dcust_info.isEmpty() &&Validations.isDouble(dtotal) == true ){
            
            if( Double.parseDouble(dcust_info) >0 && ddes.isEmpty() ){
                
            Validations.fieldEmptyMsg();
            validation = false;
            }
           
            
        }
        if(!dtotal.isEmpty() && Validations.isDouble(dtotal) == true  &&  Validations.isPositive(dtotal) == false){
            
            Validations.NegativeType();
            validation = false;
            
        }
        if (validation == true) {
            Payment_dayDetails pd = new Payment_dayDetails();
            exp = pd.addPayment_day(dvehi, dbill_no, dbooked_no, dbill_date, dbill_time, dcust, dex_day, dnof_km, ddamage_ch, ddriver_ch,dfuel,dtotal,dmil,dcust_info,ddes);
            if (exp.equals("Duplicate entry '"+dbooked_no+"' for key 'booked_no'")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You have already added payment for this '" + dbooked_no + "'boooking number !");
                alert.setContentText("Ooops, there was an error!");
                alert.showAndWait();
            } else {
                loadBill();
                loadData();
                loadRent(q);
                showDate();
                showTime();
            

            boolean nextNew = Validations.nextNewRecordConfirm("Vehicle Payment");
            if (!nextNew) {
                SubHomeController subHomeC = MainHomeController.subHome.getController();

                subHomeC.funcNamelbl.setText("Vehicle Payment");
                subHomeC.subContent.getChildren().clear();
                try {
                    subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/CarRentHome.fxml")));
                } catch (IOException e) {
                    Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, e);
                }
                subHomeC.subMenuList.getChildren().clear();
                try {
                    subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/GUIs/MainMenuList.fxml")));
                } catch (IOException e) {
                    Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, e);
                }
                subHomeC.backBtn.setVisible(false);
            } else {
                SubHomeController subHomeC = MainHomeController.subHome.getController();

                subHomeC.subContent.getChildren().clear();
                try {
                    subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/payment_day.fxml")));
                } catch (IOException e) {
                    Logger.getLogger(Payment_dayController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    }
//*****************************************edit***********************************************************

    @FXML
    private void editPayment_day(ActionEvent event) {

        String dbill_no = bill_L.getText();
        String dbooked_no = bookedBox.getText();
        String dbill_date = lblDate.getText();
        String dbill_time = lblTime.getText();
        String dex_day = exBox.getText();
        String dnof_km = kmBox.getText();
        String ddamage_ch = damageBox.getText();
        String ddriver_ch = driverBox.getText();
        String dtotal = total_L.getText();
        String dvehi = vehi_L.getText();
        String dcust = custBox.getValue();
        String dmil=milBox.getText();
        String dfuel=fuelBox.getText();
        String dcust_info=addBox.getText();
        String ddes=desBox.getText();
        boolean validation = true;
        String exp = "";

        if (dcust_info.isEmpty()||dvehi.isEmpty() || dbill_no.isEmpty() || dbooked_no.isEmpty() || dbill_date.isEmpty() || dbill_time.isEmpty() || dex_day.isEmpty() || dnof_km.isEmpty() || ddamage_ch.isEmpty() || ddriver_ch.isEmpty() || dtotal.isEmpty() || dcust == null||dmil.isEmpty() || dfuel.isEmpty()) {

            Validations.fieldEmptyMsg();
            validation = false;
        }
       
        if (!dcust_info.isEmpty() && Validations.isDouble(dtotal) == false ||!dex_day.isEmpty() && Validations.isInteger(dex_day) == false || !dtotal.isEmpty() && Validations.isDouble(dtotal) == false || !ddamage_ch.isEmpty() && Validations.isDouble(ddamage_ch) == false || !ddriver_ch.isEmpty() && Validations.isDouble(ddriver_ch) == false ) {

            Validations.wrongDoubleType();
            validation = false;
        }
          if(!dcust_info.isEmpty() &&Validations.isDouble(dtotal) == true ){
            
            if( Double.parseDouble(dcust_info) >0 && ddes.isEmpty() ){
                
            Validations.fieldEmptyMsg();
            validation = false;
            }
           
            
        }
        if (validation == true) {
            Payment_dayDetails pd = new Payment_dayDetails();
                 
            exp = pd.editPayment_day(dbill_no, dbooked_no, dbill_date, dbill_time, dcust, dex_day, dnof_km, ddamage_ch, ddriver_ch,dfuel,dmil,dtotal,dvehi,dcust,ddes);
            if (exp.equals("Duplicate entry '"+dbooked_no+"' for key 'booked_no'")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You have already added payment for this '" + dbooked_no + "'boooking number !");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Required!");
                alert.setHeaderText("Do you really want to edit this?");
                alert.setContentText("This will lose your previous entered values!");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    loadBill();
                    loadData();
                    loadRent(q);
                    showDate();
                    showTime();
                } else {

                }

            }

        }

    }

    //*****************************************payment details*****************************************   
    public void loadData() {
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            rs = con.createStatement().executeQuery("SELECT * From payment_day");

            while (rs.next()) {
                data.add(new Payment_Day(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        col_bill.setCellValueFactory(new PropertyValueFactory<>("bill_no"));
        col_booked.setCellValueFactory(new PropertyValueFactory<>("booked_no"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("bill_date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("bill_time"));
        col_custid.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        col_exday.setCellValueFactory(new PropertyValueFactory<>("ex_day"));
        col_km.setCellValueFactory(new PropertyValueFactory<>("nof_km"));
        col_damage.setCellValueFactory(new PropertyValueFactory<>("damage_ch"));
        col_driver.setCellValueFactory(new PropertyValueFactory<>("driver_ch"));
        col_ful.setCellValueFactory(new PropertyValueFactory<>("fuel_ch"));
        col_mil.setCellValueFactory(new PropertyValueFactory<>("milage_l"));
        col_tot.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_info.setCellValueFactory(new PropertyValueFactory<>("cust_info"));
        col_des.setCellValueFactory(new PropertyValueFactory<>("cust_des"));
        

        
        
        table_day.setItems(null);
        table_day.setItems(data);

    }

    @FXML
    private void payment_MouseClicked(MouseEvent event) {
        clearFeilds();
     
 
        try {

            

            Payment_Day pay = (Payment_Day) table_day.getSelectionModel().getSelectedItem();
            String pp = "SELECT * FROM payment_day WHERE bill_no = ?";

            pst = con.prepareStatement(pp);
            pst.setString(1, pay.getBill_no());
            rs = pst.executeQuery();

            while (rs.next()) {

                bill_L.setText(rs.getString(2));
                bookedBox.setText(rs.getString(3));
                lblDate.setText(rs.getString(4));
                lblTime.setText(rs.getString(5));
                custBox.setValue(rs.getString(6));
                exBox.setText(rs.getString(7));
                kmBox.setText(rs.getString(8));
                damageBox.setText(rs.getString(9));
                driverBox.setText(rs.getString(10));
                fuelBox.setText(rs.getString(11));
                milBox.setText(rs.getString(12));       
                total_L.setText(rs.getString(13));
                addBox.setText(rs.getString(14));       
                desBox.setText(rs.getString(15));
              
                
                

                String book_no = rs.getString(3);
               
                String query = "select type from rent where book_no='" + book_no + "'";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                String type = "";
                while (rs.next()) {

                    type = rs.getString("type");

                    if (type.equals("Day")) {

                        String date = lblDate.getText();
                        String re_Date = return_L.getText();

                        RentDetails rd = new RentDetails();
                        int extra = rd.calc_extraDays(date, re_Date);

                        exBox.setText(Integer.toString(extra));
                        kmBox.setText("0");
                        kmBox.setDisable(true);
                        btnKM.setDisable(true);
                        btnDay.setDisable(false);

                    }
                    if (type.equals("KM")) {

                        kmBox.setDisable(false);
                        exBox.setText("0");
                        btnDay.setDisable(true);
                        btnKM.setDisable(false);
                    }

                }
                      
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
            e.printStackTrace();
        }
   /// }
    }

///****************************************************rent details***********************************************
    String q = "SELECT * From rent";

    public void loadRent(String q) {
        try {
            Connection con = conn.dbconnect();

            dataR = FXCollections.observableArrayList();
            rs = con.createStatement().executeQuery(q);

            while (rs.next()) {
                dataR.add(new Rent(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11)));
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
        table_rent.setItems(dataR);

    }

    @FXML
    private void rent_MouseClicked(MouseEvent event) {
     
        int milage=0;
        boolean validation=true;
        
        String mil=milBox.getText();
         if(mil.isEmpty()){
             validation=false;
               Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("please fill the milage at last first !");
                alert.setContentText("Ooops, there was an error!");
                 alert.showAndWait();
         }
         if(!mil.isEmpty()&&Validations.isDouble(mil) == false){
             validation=false;
             Validations.fieldEmptyMsg();
         }
                    
        if(validation==true){
        milage=Integer.parseInt(mil);
        try {

            Rent rent = (Rent) table_rent.getSelectionModel().getSelectedItem();
            String sql = "select * from rent where book_no= ?";

            pst = con.prepareStatement(sql);
            pst.setString(1, rent.getBook_no());

            rs = pst.executeQuery();

            while (rs.next()) {

                bookedBox.setText(rs.getString(2));
                custBox.setValue(rs.getString(3));
                booked_L.setText(rs.getString(4));
                return_L.setText(rs.getString(5));
                type_L.setText(rs.getString(6));
                vehi_L.setText(rs.getString(8));
                 String driver=rs.getString(9);
                 String vehi=rs.getString(8);
                 String dfuel=rs.getString(10);
                 
                 
                 
                int nof_km=get_km(vehi,milage);
                System.out.println(Integer.toString(nof_km));              
                kmBox.setText(Integer.toString(nof_km));
                
                if (type_L.getText().equals("Day")) {

                    String date = lblDate.getText();
                    String re_Date = return_L.getText();

                    RentDetails rd = new RentDetails();
                    int extra = rd.calc_extraDays(date, re_Date);
                    if(extra <0){
                         exBox.setText("0");
                    }
                    else{
                    exBox.setText(Integer.toString(extra));}
                    btnKM.setDisable(true);
                    btnDay.setDisable(false);
                     

                }
                if (type_L.getText().equals("KM")) {

                   
                    exBox.setText("0");
                    btnDay.setDisable(true);
                    btnKM.setDisable(false);
                }
                 if(driver != "0"){
                    driver_ch( vehi, nof_km);
                }
                if(driver.equals("0")){                   
                    driverBox.setText("0");
                    
                }
                if(dfuel.equals("withFuel"))
                {
                     fuel_ch( vehi,nof_km);
                }
                if(dfuel.equals("withoutFuel"))
                {
                    fuelBox.setText("0");
                }
                
               

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
        //    e.printStackTrace();
        }
    }
            
        
        
        
        
        
    }

//*********************************payment calulations********************************************************  
    public int get_km(String vehi_id, int mil_last) {
        int no_of_km = 0, mil = 0;
        String sql = "select milage from vehicle where reg_no='" + vehi_id + "'";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                mil = Integer.parseInt(rs.getString("milage"));

            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }
        no_of_km = mil_last - mil;
        return no_of_km;

    }

    public void driver_ch(String vehi, int km) {
        String sql = "select driver_ch from vehicle where reg_no='" + vehi + "'";

        double driver_ch = 0;

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                driver_ch = Double.parseDouble(rs.getString("driver_ch"));

            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        double tot_driver = driver_ch * km;

        driverBox.setText(Double.toString(tot_driver));

    }

    public void fuel_ch(String vehi, int km) {
        double fuel_ch = 0;
        String sql = "select fuel_ch from vehicle where reg_no='" + vehi + "'";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                fuel_ch = Double.parseDouble(rs.getString("fuel_ch"));

            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }
        double tot_fuel = fuel_ch * km;

        fuelBox.setText(Double.toString(tot_fuel));

    }

    @FXML
    private void calc_paymentDay(ActionEvent event) {

        boolean validation = true;
        String ddex_day = exBox.getText();
        String ddamage_ch = damageBox.getText();
        String ddriver_ch = driverBox.getText();
        String vehi = vehi_L.getText();
        String dfuel = fuelBox.getText();
        String dmil = milBox.getText();
        String dnof_km = kmBox.getText();
       
        
             
        if (vehi.isEmpty() || ddex_day.isEmpty() || ddamage_ch.isEmpty() || ddriver_ch.isEmpty() || dfuel.isEmpty()|| dnof_km.isEmpty()) {
            Validations.fieldEmptyMsg();
        }
        if (!ddamage_ch.isEmpty() && Validations.isDouble(ddamage_ch) == false) {

            Validations.wrongDoubleType();
            validation = false;
        }
        if (!ddex_day.isEmpty() && Validations.isInteger(ddex_day) == false) {

            Validations.wrongDoubleType();
            validation = false;
        }
                
        
        if(!ddex_day.isEmpty() && Validations.isInteger(ddex_day) == true  &&  Validations.isPositive(ddex_day) == false){
            
            Validations.NegativeType();
            validation = false;
            
        }
        
        
         if(!dnof_km.isEmpty() && Validations.isInteger(dnof_km) == true  &&  Validations.isPositive(dnof_km) == false){
            
            Validations.NegativeType();
            validation = false;
            
        }
        if (validation == true) {

            String dex_day = exBox.getText();
            
            
            String damage_ch = damageBox.getText();
            String driver_ch = driverBox.getText();

            String re_Date = return_L.getText();
            LocalDate re = LocalDate.parse(re_Date, DateTimeFormatter.ISO_DATE);

            String bk_Date = return_L.getText();
            LocalDate bk = LocalDate.parse(bk_Date, DateTimeFormatter.ISO_DATE);

            int year_re = re.getYear();
            int month_re = re.getMonthValue();
            int day_re = re.getDayOfMonth();

            int year_bk = bk.getYear();
            int month_bk = bk.getMonthValue();
            int day_bk = bk.getDayOfMonth();

            int year = (year_re - year_bk);
            int months = (month_re - month_bk);
            int Days = (day_re - day_bk);

            int nof_days = year * 365 + months * 30 + Days * 1;
            
            Payment_dayDetails pday = new Payment_dayDetails();
            
            Double tot = pday.calcTotal_Day(vehi, dex_day, damage_ch, driver_ch, nof_days, dfuel);         
            total_L.setText(Double.toString(tot));

        }

    }

    
    
    @FXML
    private void calc_paymentKM(ActionEvent event) {
        boolean validation = true;
        String dnof_km = kmBox.getText();
        String ddamage_ch = damageBox.getText();
        String ddriver_ch = driverBox.getText();
        String vehi = vehi_L.getText();
        String dfuel=fuelBox.getText();
        String dcust_info=addBox.getText();
        
        
        
        if (vehi.isEmpty() || dnof_km.isEmpty() || ddamage_ch.isEmpty() || ddriver_ch.isEmpty()||dfuel.isEmpty()) {
            Validations.fieldEmptyMsg();
            validation = false;
        }

        if (!dnof_km.isEmpty() && Validations.isInteger(dnof_km) == false || !ddamage_ch.isEmpty() && Validations.isDouble(ddamage_ch) == false || !ddriver_ch.isEmpty() && Validations.isDouble(ddriver_ch) == false) {

            Validations.wrongDoubleType();
            validation = false;
        }
         if(!dnof_km.isEmpty() && Validations.isInteger(dnof_km) == true  &&  Validations.isPositive(dnof_km) == false){
            
            Validations.NegativeType();
            validation = false;
            
        }
        

        if (validation == true) {

            Payment_dayDetails pk = new Payment_dayDetails();
            double tot = pk.calcTotal_Km(vehi, ddamage_ch, ddriver_ch, dnof_km,dfuel);
            total_L.setText(Double.toString(tot));
            
           
           
        }

    }
    
    @FXML
    public void deduction_tot(ActionEvent event)
    {
        String dcust_info=addBox.getText();
        boolean validation=true;
        if(dcust_info.isEmpty()){
            Validations.fieldEmptyMsg();
            validation = false; 
        }
       if( !dcust_info.isEmpty() && Validations.isDouble(dcust_info) == false)
       {
           Validations.wrongDoubleType();
            validation = false;
           
       }
       //if(!dcust_info.isEmpty() && Validations.isDouble(dcust_info) == false)
       if(validation==true){
            Double tot=Double.parseDouble( total_L.getText());
            Double additional=Double.parseDouble(dcust_info);
            Double final_tot=tot-additional;
            total_L.setText(Double.toString(final_tot));
           
       }
        
        
    }
    ///***********************************************load details******************************
@FXML
private void load_detail(ActionEvent event) {
        String q="select * from rent";
        loadData();
        loadRent(q);
    
}
 @FXML
    private void Clear_details(ActionEvent event) {
        clearFeilds();
    }
//***************************************************Search***********************************************   

    @FXML
    private void Search_cust_rent(ActionEvent event) {

        String cust = custBox.getValue();

        if (cust.isEmpty()) {

            Validations.Search_error();

        } else {

            String rent_s = "select * from rent where cust_id='" + cust + "'";
            loadRent(rent_s);

        }

    }

//******************************************************************************************************************//
    public void loadBill() {
        try {
            Statement s = (Statement) DBconnect.dbconnect().createStatement();
            rs = s.executeQuery("SELECT ID from payment_day ORDER BY ID DESC");
            rs.first();
            int no = Integer.parseInt(rs.getString(1));
            String previous = Integer.toString(no);

            String next = "SVRP-" + previous;

            bill_L.setText(next);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearFeilds() {
         loadBill();
        bookedBox.setText("");
        exBox.setText("");
        damageBox.setText("");
        driverBox.setText("");
        total_L.setText("");
        vehi_L.setText("");
        kmBox.setText("");
        custBox.setValue(null);
       

    }

    public void fill_combo_cust() {
        String query = "select cust_id from rent ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                option.add(rs.getString("cust_id"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Car Rental System - Payment Details");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CarRentGUIs/CarSubMenuList.fxml")));
        } catch (IOException ex) {

        }
        SubHomeController.backUrl = "/GUIs/CarRentHome.fxml";
        fill_combo_cust();
        custBox.setItems(option);
        loadBill();
        loadData();
        loadRent(q);
        showDate();
        showTime();
        

    }

}
