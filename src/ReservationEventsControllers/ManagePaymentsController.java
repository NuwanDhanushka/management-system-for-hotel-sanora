/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationEventsControllers;

import Controllers.EventHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Anushka_Hapukotuwa
 */

//Implements add,search,preview and delete of payments
public class ManagePaymentsController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    //Initializes fields in ManagePayments.fxml
    @FXML
    private Label paymentID;
    @FXML
    private Label eventIDX;
    @FXML
    private Label reservationID;
    @FXML
    private Label reservationCost;
    @FXML
    private Label eventCost;
    @FXML
    private Label totalAmount;
    @FXML
    private TextField searchPaymentID;
    
    //Initializes buttons in ManagePayments.fxml
    @FXML
    private Button submitPaymentBtn;
    @FXML
    private Button previewPaymentBtn;
    @FXML
    private Button searchPaymentBtn;
    @FXML
    private Button deletePaymentBtn;

    //Initializes table view in ManagePayments.fxml
    @FXML
    private TableView<PaymentTableView> paymentTblView;
    @FXML
    private TableColumn<PaymentTableView, String> PaymentID;
    @FXML
    private TableColumn<PaymentTableView, String> ReservationCost;
    @FXML
    private TableColumn<PaymentTableView, String> EventCost;
    @FXML
    private TableColumn<PaymentTableView, String> TotalAmount;
    @FXML
    private TableColumn<PaymentTableView, String> ReservationID;
    @FXML
    private TableColumn<PaymentTableView, String> EventID;

    private ObservableList<PaymentTableView> data;

    double resamount, eventamount, totalamount;
    private String resID;
    private double resCost;
    private String eventId;
    private double eventcost;

    //Database connection
    private DBconnect conn;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private double totalcost;
    private int paymentId;
    private String EventManage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.funcNamelbl.setText("Reservations and Events - Payments");
        subHomeC.backBtn.setVisible(true);//Set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(EventHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/EventHome.fxml";
    }
 @FXML
    public void deletepaymentevent(ActionEvent event) {
    String pid=searchPaymentID.getText();
     String d = "DELETE FROM respayment where PaymentID = '" +  pid + "'";
            try {
                Connection con = conn.dbconnect();
                pst = con.prepareStatement(d);
                pst.execute();

            } catch (Exception e) {
                System.out.println("An error occured during during deletion of a record in payment table! " + e);
            }
    
    
    
    
    }
    //Adds a new payment
    @FXML
    public void submitbuttonaction(ActionEvent event) {
       
        Connection con = DBconnect.dbconnect();
        try {

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation ");

            while (rs.next()) {
                resID = rs.getString("ReservationID");
                resCost = Double.parseDouble(rs.getString("ReservationCost"));
                EventManage=rs.getString("ManageEvent");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        eventcost=0;
        if(EventManage.equals("Yes")){
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM eventdet");

            while (rs.next()) {
                eventId = rs.getString("EventID");
                eventcost = Double.parseDouble(rs.getString("TotalCost"));

            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        }
        totalcost = resCost + eventcost;
        
        //Confirmation alert box for adding a new payment
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("CONFIRMATION");
        confirm.setHeaderText("Add new payment?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            //User selected to add new payment
            //Try catch block for adding values in variables to the reservation table in database
            try {
                String q = "INSERT INTO respayment(ReservationCost,EventCost,TotalAmount,ReservationID,EventID) values ('" + resCost + "','" + eventcost + "','" + totalcost + "','" + resID + "','" + eventId + "')";
                pst = con.prepareStatement(q);
                pst.execute();
                System.out.println("Payment successful!");

            } catch (Exception e) {
                System.out.println("An error occured during insertion of values to respayment table! " + e);
            }
        } else {
            //User selected not to add new payment
        }

        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM respayment");

            while (rs.next()) {
                paymentId = Integer.parseInt(rs.getString("PaymentID"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        //paymentId = paymentId + 1;
        paymentID.setText(String.valueOf(paymentId));
        reservationCost.setText(String.valueOf(resCost));
        eventCost.setText(String.valueOf(eventcost));
       totalAmount.setText(String.valueOf(totalcost));
        reservationID.setText(resID);
        eventIDX.setText(eventId);
        
        try {

            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM respayment");

            while (rs.next()) {
                data.add(new PaymentTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println("An error occured during retrieval of the record from respayment table! " + ex);
        }

        PaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
        ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
        EventCost.setCellValueFactory(new PropertyValueFactory<>("EventCost"));
        TotalAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
        ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
        EventID.setCellValueFactory(new PropertyValueFactory<>("EventID"));

        paymentTblView.setItems(null);
        paymentTblView.setItems(data);

        //Alert box to show reservation was successful
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("New Payment Confirmation");
        success.setContentText("Payment Successful!");
        Optional<ButtonType> sresult = success.showAndWait();
        if (sresult.get() == ButtonType.OK) {
            System.out.println("Confirmation Successful!");
        } else {

        }
    //}
    }

    //Shows all currently existing records in the respayment table
    @FXML
    public void PreviewPaymentButtonAction(ActionEvent event) {
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM respayment");

            while (rs.next()) {
                data.add(new PaymentTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println("An error occured during retrieval of records for preview from reservation table! " + ex);
        }

        PaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
        ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
        EventCost.setCellValueFactory(new PropertyValueFactory<>("EventCost"));
        TotalAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
        ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
        EventID.setCellValueFactory(new PropertyValueFactory<>("EventID"));

        paymentTblView.setItems(null);
        paymentTblView.setItems(data);

    }

    //Searches for a matching record from the respayment table when paymentID is given
    @FXML
    public void SearchPaymentButtonAction(ActionEvent event) {
        String searchPaymentId = searchPaymentID.getText();
        if (emptysearchPaymentIDField() == true && validatesearchPaymentIDField() == true) {
            try {
                Connection con = conn.dbconnect();
                data = FXCollections.observableArrayList();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM respayment WHERE PaymentID = ('" + searchPaymentId + "')");

                while (rs.next()) {
                    data.add(new PaymentTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                }
            } catch (SQLException ex) {
                System.err.println("An error occured during the search for record in respayment table! " + ex);
            }

            PaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
            ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
            EventCost.setCellValueFactory(new PropertyValueFactory<>("EventCost"));
            TotalAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
            ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
            EventID.setCellValueFactory(new PropertyValueFactory<>("EventID"));

            paymentTblView.setItems(null);
            paymentTblView.setItems(data);
        }

    }

    @FXML
    private void PaymentMouseClickAction(MouseEvent event) {
        con = DBconnect.dbconnect();
        /*try {
            Rent rent = (Rent) paymentTblView.getSelectionModel().getSelectedItem();
            String sql = "SELECT * FROM respayment where book_no= ?";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, rent.getBook_no());
           

           rs = pst.executeQuery();

            while (rs.next()) {

                bookedBox.setText(rs.getString(1)); 
                custBox.setText(rs.getString(2));
                booked_L.setText(rs.getString(3));
                return_L.setText(rs.getString(4));
         //       depoBox.setText(rs.getString(6));
                vehi_L.setText(rs.getString(7));
                  

            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }*/

    }
    
    
    //Validation for allowing only positive integer values for searchPaymentID field
    private boolean validatesearchPaymentIDField() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(searchPaymentID.getText());
        if (m.find() && m.group().equals(searchPaymentID.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in Search Payment Field");
            alert.setContentText("Please enter a valid number! \n It should contain only positive integer values!");
            alert.showAndWait();
            return false;

        }
    }
    
    //Validation for empty fields
    private boolean emptysearchPaymentIDField() {
        if (searchPaymentID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Search PaymentID Field Empty!");
            alert.setContentText("Please enter a valid number to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

}
