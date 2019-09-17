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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class LoanmainaccController implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private TextField loannametxt;
    @FXML
    private TextField irtxt;
    @FXML
    private TextField lduration;
    @FXML
    private TextField totaltxt;
    @FXML
    private Button editlbut, dellbut;

    @FXML
    private TableView<tableloancontroller> addloan;
    @FXML
    private TableColumn<tableloancontroller, String> lname;
    @FXML
    private TableColumn<tableloancontroller, String> lirate;
    @FXML
    private TableColumn<tableloancontroller, String> lcapital;
    @FXML
    private TableColumn<tableloancontroller, String> lamount;
    @FXML
    private Button viewloanbutton;
    @FXML
    private Button addloanbutton;
    private ObservableList<tableloancontroller> data;

    @FXML
    private TableView<tableloancontroller> payloantable;
    @FXML
    private TableColumn<tableloancontroller, String> payloanname;
    @FXML
    private TableColumn<tableloancontroller, String> payloanamount;
    @FXML
    private TableColumn<tableloancontroller, String> remainder;
    private boolean status = true;
    private double amountpaid = 0;
    int y;
    private String Lname;

    /**
     * Initializes the controller class.
     */
    
    //---------------------------editing the loan table------------------------------
    //------when mouse clicked 
    @FXML
    private void loantableeditevent(MouseEvent event) {
        Connection con = DBconnect.dbconnect();

        try {

            tableloancontroller loann = (tableloancontroller) addloan.getSelectionModel().getSelectedItem();

            String sql = "SELECT * FROM loan where lname = ?";

            pst = con.prepareStatement(sql);

            pst.setString(1, loann.getlname());

            rs = pst.executeQuery();

            while (rs.next()) {
                loannametxt.setText(rs.getString(1));
                irtxt.setText(rs.getString(2));
                double monthlycap = Double.parseDouble(rs.getString(3));
                double totamt = Double.parseDouble(rs.getString(4));
                double dur = totamt / monthlycap;
                lduration.setText(String.valueOf(dur));
                totaltxt.setText(String.valueOf(totamt));

            }
        } catch (Exception e) {
            System.out.println("An error occured during the mouse click event for the Loan table view! " + e);
            e.printStackTrace();
        }

    }

    @FXML
    public void editloan(ActionEvent event) {
         if (validateAmountFeild() == true && validateAmountFeild() == true && validateFields() == true && validateDurationFeild()==true && validateLoanNameField()==true) {
        Lname = loannametxt.getText();
        String interest = irtxt.getText();
        double dur = Double.parseDouble(lduration.getText());
        double tot = Double.parseDouble(totaltxt.getText());
        double monthlycapital = tot / dur;
        try {
            Connection con = DBconnect.dbconnect();
            String q = ("UPDATE loan SET lname='" + Lname + "' ,lirate='" + interest + "', lcapital='" + monthlycapital + "', lamount='" + tot + "' where lname='" + Lname + "'");
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        viewloantable();
         }

    }

    @FXML
    public void deleteloan(ActionEvent event) {
        Lname = loannametxt.getText();
        try {
            Connection con = DBconnect.dbconnect();
            String sql = "DELETE from loan where lname='" + Lname + "'";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        viewloantable();

    }

    @FXML
    public void payloanpayevent(ActionEvent event) {
        Calendar rightNow = Calendar.getInstance();
        int x = rightNow.get(Calendar.MONTH);

        Connection con = DBconnect.dbconnect();
        try {
//--------------check the last month that the loan has been paid
            ResultSet rs = con.createStatement().executeQuery("SELECT * from tempmonth");

            while (rs.next()) {
                y = Integer.parseInt(rs.getString("month"));

                if (y == 11) {
                    y = -1;
                }
                if (x == y + 1) {
                    status = true;
                } else {
                    status = false;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
   //-----------------if loan has not been paid this month
        if (status == true) {
            try {

                ResultSet rs = con.createStatement().executeQuery("SELECT * from loan");

                while (rs.next()) {
                    String loanname = rs.getString("lname");
                    double rem = Double.parseDouble(rs.getString("remainingamt"));
                    double mpay = Double.parseDouble(rs.getString("monthlypay"));
                    rem = rem - mpay;
                    amountpaid = amountpaid + mpay;
                    try {
//-------calculating and updating the paid loan amounts
                        String q = ("UPDATE loan SET remainingamt='" + rem + "' where lname='" + loanname + "'");
                        pst = con.prepareStatement(q);
                        pst.execute();

                    } catch (Exception e) {
                        System.out.println(e);

                    }
                    if(rem==0){
                    try {
//-------------if full amount is paid then delete the loan record
                        String sql = "DELETE from loan where lname='" + loanname + "'";
                        pst = con.prepareStatement(sql);
                        pst.execute();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
            //----------insert the transaction into accounts table
            Date date = new Date();
            String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            try {
                String q = "INSERT INTO acc(date,des,amount,type) values ('" + modifiedDate + "','" + "loan" + "','" + amountpaid + "','" + "expense" + "')";
                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);

            }

            try {
//-------------record that this month loan has been paid
                String q = ("UPDATE tempmonth SET month='" + x + "' where tid='" + 1 + "'");
                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);

            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Loan Paid For This Month!!");
            alert.setHeaderText(null);
            alert.setContentText("Try Again Next Month!!");
            alert.showAndWait();
        }
    }

    @FXML
    public void viewamounttopay() {

        try {
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT lname,monthlypay,remainingamt From loan");

            while (rs.next()) {
                data.add(new tableloancontroller(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        payloanname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        payloanamount.setCellValueFactory(new PropertyValueFactory<>("monthlypay"));
        remainder.setCellValueFactory(new PropertyValueFactory<>("remainingamt"));

        payloantable.setItems(null);
        payloantable.setItems(data);

    }

    @FXML
    public void viewloantable() {
        try {
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT lname,lirate,lcapital,lamount From loan");

            while (rs.next()) {
                data.add(new tableloancontroller(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        lirate.setCellValueFactory(new PropertyValueFactory<>("lirate"));
        lcapital.setCellValueFactory(new PropertyValueFactory<>("lcapital"));
        lamount.setCellValueFactory(new PropertyValueFactory<>("lamount"));

        addloan.setItems(null);
        addloan.setItems(data);

    }

    // and start it off
    @FXML
    public void addloanbuttonevent(ActionEvent event) {
        if (validateAmountFeild() == true && validateAmountFeild() == true && validateFields() == true && validateDurationFeild()==true && validateLoanNameField()==true) {
            String loanname = loannametxt.getText();
            double ir = Double.parseDouble(irtxt.getText());
            double duration = Double.parseDouble(lduration.getText());
            double totalloan = Double.parseDouble(totaltxt.getText());
            double permonthcapital = totalloan / duration;
            double monthlytotal = permonthcapital + (totalloan * (ir / 100.0));

            try {
                String q = "INSERT INTO loan(lname,lirate,lcapital,lamount,monthlypay,remainingamt) values ('" + loanname + "','" + ir + "','" + permonthcapital + "','" + totalloan + "','" + monthlytotal + "','" + totalloan + "')";
                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);

            }
            viewloantable();

        }
    }

    public LoanmainaccController() {

        con = DBconnect.dbconnect();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Accounts - Loan Maintainance");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        } catch (IOException ex) {
            System.out.print(ex);
        }
        SubHomeController.backUrl = "/GUIs/AccountsHome.fxml";
        viewloantable();
        viewamounttopay();
    }

    private boolean validateFields() {
        if (loannametxt.getText().isEmpty() || irtxt.getText().isEmpty() || lduration.getText().isEmpty() || totaltxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fileds!!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields!!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    private boolean validaterateFeild() {
        Pattern p = Pattern.compile("[0-9][0-9][.][0-9]+");
        Matcher m = p.matcher(irtxt.getText());
        if (m.find() && m.group().equals(irtxt.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Amount feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Amount! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;

        }
    }

    private boolean validateAmountFeild() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(totaltxt.getText());
        if (m.find() && m.group().equals(totaltxt.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Interest Rate feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Interest Rate! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;

        }
    }

    private boolean validateDurationFeild() {
        Pattern p = Pattern.compile("[0-9][0-9]");
        Matcher m = p.matcher(lduration.getText());
        if (m.find() && m.group().equals(lduration.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Duration feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Duration! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;

        }
    }

    private boolean validateLoanNameField() {

        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(loannametxt.getText());
        if (m.find() && m.group().equals(loannametxt.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Loan Amount field!!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter valid name for Loan Amount! \n It should be only conatin charactors! ");
            alert.showAndWait();
            return false;

        }
    }

}
