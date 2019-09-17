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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
//Implements add,update,delete and search of reservations
public class ManageReservationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Initializes fields in ManageReservations.fxml
    @FXML
    private Label reservationID;
    @FXML
    private DatePicker reservationDateTime;
    @FXML
    private TextField startReserveTime;
    @FXML
    private TextField endReserveTime;
    @FXML
    private TextField noOfGuests;
    @FXML
    private ComboBox hallName;
    @FXML
    private ComboBox manageEvent;
    @FXML
    private Label reservationCost;
    @FXML
    private TextField customerID;
    @FXML
    private TextField SearchreservationID;

    //Initializes buttons in ManageReservations.fxml
    @FXML
    private Button submitReservationBtn;
    @FXML
    private Button clearReservationFormBtn;
    @FXML
    private Button checkResCostBtn;
    @FXML
    private Button previewReservationBtn;
    @FXML
    private Button searchReservationBtn;
    @FXML
    private Button updateReservationBtn;
    @FXML
    private Button deleteReservationBtn;

    //Initializes table view in ManageReservations.fxml
    @FXML
    private TableView<ReservationTableView> reservationTblView;
    @FXML
    private TableColumn<ReservationTableView, String> ReservationID;
    @FXML
    private TableColumn<ReservationTableView, String> ReservationDateTime;
    @FXML
    private TableColumn<ReservationTableView, String> NumberOfGuests;
    @FXML
    private TableColumn<ReservationTableView, String> HallName;
    @FXML
    private TableColumn<ReservationTableView, String> ManageEvent;
    @FXML
    private TableColumn<ReservationTableView, String> ReservationCost;
    @FXML
    private TableColumn<ReservationTableView, String> StartTime;
    @FXML
    private TableColumn<ReservationTableView, String> EndTime;
    @FXML
    private TableColumn<ReservationTableView, String> CustomerID;

    //Initializes table view that checks reservation availablility in ManageReservations.fxml
  
    private ObservableList<ReservationTableView> data;


    //Database connection
    private DBconnect conn;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String selectHall;
    //double perHead;
    //int guestCount;
    float priceForHall;
    private String resvID;
    private String rDate;
    private double rHall;
    private String gtrsdate;
    private String gtrshallnum;
    private String gtrsstart;
    private String gtrsend;
    private boolean hallstatus = true;
    private boolean custstatus = false;
    private String cusID;
    private int maxoccupancy;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SubHomeController subHomeC = MainHomeController.subHome.getController();

        subHomeC.funcNamelbl.setText("Reservations and Events - Reservations");
        subHomeC.backBtn.setVisible(true);//Set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(EventHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/EventHome.fxml";

        //Initializes values for Hall Name combo box
        hallName.getItems().removeAll(hallName.getItems());
        hallName.getItems().addAll("Grand Ballroom", "Cats Eye Ballroom", "Crystal Ballroom");
        hallName.getSelectionModel().select("Grand Ballroom");

        //Initializes values for Manage Event combo box
        manageEvent.getItems().removeAll(manageEvent.getItems());
        manageEvent.getItems().addAll("Yes", "No");
        manageEvent.getSelectionModel().select("Yes");

    }

    //Adds a new reservation
    @FXML
    public void SubmitReservationButtonAction(ActionEvent event) throws IOException {
        LocalDate resDate = reservationDateTime.getValue();

        //Assigns values taken from fields in ManageReservations form to variables
        if (chkoccupancy() == true && validateFields() == true && validateNoOfGuestsField() == true && validateendReserveTimeField() == true && validatestartReserveTimeField() == true) {
            //int reserveId = 8;//Integer.parseInt(reservationID.getText());
            String reserveDate = resDate.toString();
            String starteve = startReserveTime.getText();
            String endeve = endReserveTime.getText();
            int guestNo = Integer.parseInt(noOfGuests.getText());
            String hall = hallName.getSelectionModel().getSelectedItem().toString();
            String manageEventChoice = manageEvent.getSelectionModel().getSelectedItem().toString();
            float reserveCost = priceForHall;
            String customerId = customerID.getText();
            System.out.println(reserveDate);

            Connection con = DBconnect.dbconnect();
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM customer ");

                while (rs.next()) {
                    if (customerId.equals(rs.getString("custID"))) {
                        custstatus = true;
                    }
                }
            } catch (SQLException ex) {
                System.err.println("An error occured during retrieval of the records from reservation table! " + ex);
            }
            if (custstatus == true) {
                try {
                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation ");

                    while (rs.next()) {
                        gtrsdate = rs.getString("ReservationDateTime");
                        gtrshallnum = rs.getString("HallName");
                        gtrsstart = rs.getString("StartTime");
                        gtrsend = rs.getString("EndTime");
                        if (reserveDate.equals(gtrsdate) && hall.equals(gtrshallnum)) {
                            if (Double.parseDouble(gtrsstart) <= Double.parseDouble(starteve) && Double.parseDouble(gtrsend) >= Double.parseDouble(starteve)) {
                                hallstatus = false;
                            } else {
                                hallstatus = true;
                            }
                        }
                    }
                } catch (SQLException ex) {
                    System.err.println("An error occured during retrieval of the records from reservation table! " + ex);
                }

                if (hallstatus == true) {
                    //Confirmation alert box for adding a new reservation
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("CONFIRMATION");
                    confirm.setHeaderText("Add new reservation?");
                    Optional<ButtonType> result = confirm.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        //User selected to add new reservation

                        //Try catch block for adding values in variables to the reservation table in database
                        try {
                            String q = "INSERT INTO reservation(ReservationDateTime,NumberOfGuests,HallName,ManageEvent,ReservationCost,StartTime,EndTime,CustomerID) values ('" + reserveDate + "','" + guestNo + "','" + hall + "','" + manageEventChoice + "','" + reserveCost + "','" + starteve + "','" + endeve + "','" + customerId + "')";
                            pst = con.prepareStatement(q);
                            pst.execute();
                            System.out.println("Reservation successful!");

                        } catch (Exception e) {
                            System.out.println("An error occured during insertion of values to reservation table! " + e);
                        }
                    } else {
                        //User selected not to add new reservation
                    }

                    //Try catch block to retrieve ReservationID from reservation table
                    try {
                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation ");

                        while (rs.next()) {
                            resvID = rs.getString("ReservationID");
                        }
                    } catch (SQLException ex) {
                        System.err.println("An error occured during retrieval of the ReservationID from reservation table! " + ex);
                    }
                    //Setting the ReservationID value from reservation table to the reservationID label
                    reservationID.setText(resvID);

                    //Try catch block for retrieving added records from the reservation table
                    try {
                        data = FXCollections.observableArrayList();
                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation");

                        while (rs.next()) {
                            data.add(new ReservationTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                        }
                    } catch (SQLException ex) {
                        System.err.println("An error occured during retrieval of the record from reservation table! " + ex);
                    }

                    ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
                    ReservationDateTime.setCellValueFactory(new PropertyValueFactory<>("ReservationDateTime"));
                    NumberOfGuests.setCellValueFactory(new PropertyValueFactory<>("NumberOfGuests"));
                    HallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
                    ManageEvent.setCellValueFactory(new PropertyValueFactory<>("ManageEvent"));
                    ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
                    StartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
                    EndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
                    CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

                    reservationTblView.setItems(null);
                    reservationTblView.setItems(data);

                    //Alert box to show reservation was successful
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setHeaderText("Reservation Confirmation");
                    success.setContentText("Reservation Successful!");
                    Optional<ButtonType> sresult = success.showAndWait();
                    if (sresult.get() == ButtonType.OK) {
                        System.out.println("Insert Confirmation Successful");
                    } else {

                    }

                    try {
                        String q = "INSERT INTO tempreservation(amount) values ('" + reserveCost + "')";
                        pst = con.prepareStatement(q);
                        pst.execute();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("Error in reservation");
                    alert.setContentText("Reservation cannot be inserted!\n Hall selected has already been reserved for this date and time!");
                    alert.showAndWait();
                    System.out.println("Reservation not available");

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText("Error in reservation");
                alert.setContentText("Reservation cannot be inserted!\n CustomerID entered is invalid!");
                alert.showAndWait();
                System.out.println("Invalid Customer");
            }
        }
    }

    //Goes to the next user interface
    @FXML
    public void NextUIButtonAction(ActionEvent event) throws IOException {
        if ((manageEvent.getSelectionModel().getSelectedItem().toString().equals("Yes"))) {
            ReservationEventsSubMenuListController.resListState = 3;
            ReservationEventsSubMenuListController.resListState = 1;
            SubHomeController subHomeC = MainHomeController.subHome.getController();
            subHomeC.funcNamelbl.setText("Reservations and Events - Events");
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageEvents.fxml")));
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));

        } else {
            ReservationEventsSubMenuListController.resListState = 3;
            ReservationEventsSubMenuListController.resListState = 1;
            SubHomeController subHomeC = MainHomeController.subHome.getController();
            subHomeC.funcNamelbl.setText("Reservations and Events - Payments");
            subHomeC.subContent.getChildren().clear();
            subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManagePayments.fxml")));
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));

        }
    }

    @FXML
    public void HallSelectionButtonAction(ActionEvent event) {

        selectHall = hallName.getSelectionModel().getSelectedItem().toString();
        //guestCount = Integer.parseInt(noOfGuests.getText());

        if (selectHall.equals("Crystal Ballroom")) {
            reservationCost.setText("100000.00");
            maxoccupancy = 250;
            //perHead = 1000.0;
            priceForHall = Float.parseFloat(reservationCost.getText());
            //priceForHall = (float) ((Float.parseFloat(reservationCost.getText()))+(perHead*guestCount));
        } else if (selectHall.equals("Cats Eye Ballroom")) {
            reservationCost.setText("120000.00");
            maxoccupancy = 500;
            //perHead = 2000.0;
            priceForHall = Float.parseFloat(reservationCost.getText());
            //priceForHall = (float) ((Float.parseFloat(reservationCost.getText()))+(perHead*guestCount));
        } else if (selectHall.equals("Grand Ballroom")) {
            reservationCost.setText("150000.00");
            maxoccupancy = 1000;
            //perHead = 4000.0;
            priceForHall = Float.parseFloat(reservationCost.getText());
            //priceForHall = (float) ((Float.parseFloat(reservationCost.getText()))+(perHead*guestCount));
        }
    }

    //Clears the reservation form
    @FXML
    public void ClearReservationButtonAction(ActionEvent event) {
        //Confirmation alert box for clearing the reservation form
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("CONFIRMATION");
        confirm.setHeaderText("Clear the reservation form?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            //User chose to clear the reservation form
            reservationID.setText("");
            reservationDateTime.setValue(null);
            startReserveTime.setText("");
            endReserveTime.setText("");
            noOfGuests.setText("");
            reservationCost.setText("");
        } else {
            //User chose not to clear the reservation form
        }

    }

    //Shows all currently existing records in the reservation table
    @FXML
    public void PreviewReservationButtonAction(ActionEvent event) {
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation");

            while (rs.next()) {
                data.add(new ReservationTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println("An error occured during retrieval of records for preview from reservation table! " + ex);
        }

        ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
        ReservationDateTime.setCellValueFactory(new PropertyValueFactory<>("ReservationDateTime"));
        NumberOfGuests.setCellValueFactory(new PropertyValueFactory<>("NumberOfGuests"));
        HallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        ManageEvent.setCellValueFactory(new PropertyValueFactory<>("ManageEvent"));
        ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
        StartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        EndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        reservationTblView.setItems(null);
        reservationTblView.setItems(data);

    }

    //Searches for a matching record from the reservation table when reservationID is given
    @FXML
    public void SearchReservationButtonAction(ActionEvent event) {
        if (emptySearchreservationIDField() == true && validateSearchreservationIDField() == true) {
            String searchReserveId = SearchreservationID.getText();

            try {
                Connection con = conn.dbconnect();
                data = FXCollections.observableArrayList();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE ReservationID = ('" + searchReserveId + "')");

                while (rs.next()) {
                    data.add(new ReservationTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                }
            } catch (SQLException ex) {
                System.err.println("An error occured during the search for record in reservation table! " + ex);
            }

            ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
            ReservationDateTime.setCellValueFactory(new PropertyValueFactory<>("ReservationDateTime"));
            NumberOfGuests.setCellValueFactory(new PropertyValueFactory<>("NumberOfGuests"));
            HallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
            ManageEvent.setCellValueFactory(new PropertyValueFactory<>("ManageEvent"));
            ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
            StartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
            EndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
            CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

            reservationTblView.setItems(null);
            reservationTblView.setItems(data);
        }
    }

    @FXML
    private void ReservationTblViewMouseClickAction(MouseEvent event) {
        Connection con = conn.dbconnect();

        //Try catch block to retrieve ReservationID from reservation table
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM customer ");

            while (rs.next()) {
                cusID = rs.getString("CustID");
            }
        } catch (SQLException ex) {
            System.err.println("An error occured during retrieval of the CustID from customer table! " + ex);
        }
        //Setting the ReservationID value from reservation table to the reservationID label
        customerID.setText(cusID);
        try {
            System.out.println("qqqq");
            ReservationTableView reservation = (ReservationTableView) reservationTblView.getSelectionModel().getSelectedItem();
            System.out.println("qqqq");
            String sql = "SELECT * FROM reservation where ReservationID = ?";

            pst = con.prepareStatement(sql);
            System.out.println("qqqq");
            pst.setString(1, reservation.getReservationID());

            rs = pst.executeQuery();
            System.out.println("qqqq");
            while (rs.next()) {
                reservationID.setText(rs.getString(1));
                String ld = rs.getString(2);
                reservationDateTime.setValue(LocalDate.parse(ld, DateTimeFormatter.ISO_DATE));
                // reservationDateTime.setValue(rs.getString(2));
                startReserveTime.setText(rs.getString(7));
                endReserveTime.setText(rs.getString(8));
                noOfGuests.setText(rs.getString(3));
                hallName.setValue(rs.getString(4));
                manageEvent.setValue(rs.getString(5));
                //hallName.getSelectionModel().getSelectedItem().toString()rs.getString(4));
                //manageEvent.getSelectionModel().getSelectedItem().toString()rs.getString(5));
                reservationCost.setText(rs.getString(6));
                customerID.setText(rs.getString(9));
            }
        } catch (Exception e) {
            System.out.println("An error occured during the mouse click event for the reservation table view! " + e);
            e.printStackTrace();
        }
    }

    //Updates an existing reservation
    @FXML
    public void UpdateReservationButtonAction(ActionEvent event) {
   
            LocalDate resDate = reservationDateTime.getValue();
                if (chkoccupancy() == true && validateFields() == true && validateNoOfGuestsField() == true && validateendReserveTimeField() == true && validatestartReserveTimeField() == true) {
            String reservationdate = String.valueOf(resDate);
            //Assigns values taken from fields in ManageReservations form to variables
           String reserveId =reservationID.getText();

            String starteve = startReserveTime.getText();
            String endeve = endReserveTime.getText();
            int guestNo = Integer.parseInt(noOfGuests.getText());
            String hall = hallName.getSelectionModel().getSelectedItem().toString();
            String manageEventChoice = manageEvent.getSelectionModel().getSelectedItem().toString();
            float reserveCost = priceForHall;
            String customerId = customerID.getText();

            //Confirmation alert box for updating an existing reservation
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("CONFIRMATION");
            confirm.setHeaderText("Update reservation?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    Connection con = conn.dbconnect();

                    String q = "UPDATE reservation SET ReservationDateTime='" + reservationdate + "',NumberOfGuests='" + guestNo + "',HallName='" + hall + "',ManageEvent='" + manageEventChoice + "',ReservationCost='" + reserveCost + "',StartTime='" + starteve + "',EndTime='" + endeve + "',CustomerID='" + customerId + "' where ReservationID = '" + reserveId + "'";
                    pst = con.prepareStatement(q);
                    pst.execute();
                } catch (Exception e) {
                    System.out.println("An error occured during updating values in reservation table! " );
                }
            } 
            else {
                //User chose not to update an existing reservation
            }

          
            //Try catch block for retrieving updated records from the reservation table
            try {
                Connection con = conn.dbconnect();

                data = FXCollections.observableArrayList();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation");

                while (rs.next()) {
                    data.add(new ReservationTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                }
            } catch (SQLException ex) {
                System.err.println("An error occured during retrieval of updated records from reservation table! " + ex);
            }

            ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
            ReservationDateTime.setCellValueFactory(new PropertyValueFactory<>("ReservationDateTime"));
            NumberOfGuests.setCellValueFactory(new PropertyValueFactory<>("NumberOfGuests"));
            HallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
            ManageEvent.setCellValueFactory(new PropertyValueFactory<>("ManageEvent"));
            ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
            StartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
            EndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
            CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

            reservationTblView.setItems(null);
            reservationTblView.setItems(data);

            //Alert box to show update was successful
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText("Update Confirmation");
            success.setContentText("Update Successful!");
            Optional<ButtonType> sresult = success.showAndWait();
            if (sresult.get() == ButtonType.OK) {
                System.out.println("Update Confirmation Successful");
            } else {

            }
    //    }
                }
    }

    //Deletes a reservation from the reservation table
    public void DeleteReservationButtonAction(ActionEvent event) {
        LocalDate resDate = reservationDateTime.getValue();

        //Assigns values taken from fields in ManageReservations form to variables
        int reserveId = Integer.parseInt(reservationID.getText());
        String reserveDate = String.valueOf(resDate);
        String starteve = startReserveTime.getText();
        String endeve = endReserveTime.getText();
        int guestNo = Integer.parseInt(noOfGuests.getText());
        String hall = hallName.getSelectionModel().getSelectedItem().toString();
        String manageEventChoice = manageEvent.getSelectionModel().getSelectedItem().toString();
        float reserveCost = priceForHall;

        //Confirmation alert box for deleting an existing reservation
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("CONFIRMATION");
        confirm.setHeaderText("Delete reservation?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            String d = "DELETE FROM reservation where ReservationID = '" + reserveId + "'";
            try {
                Connection con = conn.dbconnect();
                pst = con.prepareStatement(d);
                pst.execute();

            } catch (Exception e) {
                System.out.println("An error occured during during deletion of a record in reservation table! " + e);
            }
        } else {
            //User chose not to delete reservation
        }

        //Try catch block for retrieving existing records after deleting the selected record from above from the reservation table
        try {
            Connection con = conn.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation");

            while (rs.next()) {
                data.add(new ReservationTableView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println("An error occured during retrieval of updated records from reservation table! " + ex);
        }

        ReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
        ReservationDateTime.setCellValueFactory(new PropertyValueFactory<>("ReservationDateTime"));
        NumberOfGuests.setCellValueFactory(new PropertyValueFactory<>("NumberOfGuests"));
        HallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        ManageEvent.setCellValueFactory(new PropertyValueFactory<>("ManageEvent"));
        ReservationCost.setCellValueFactory(new PropertyValueFactory<>("ReservationCost"));
        StartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        EndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        reservationTblView.setItems(null);
        reservationTblView.setItems(data);

        //Alert box to show delete was successful
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Delete Confirmation");
        success.setContentText("Delete Successful!");
        Optional<ButtonType> sresult = success.showAndWait();
        if (sresult.get() == ButtonType.OK) {
            System.out.println("Delete Confirmation Successful");
        } else {

        }
    }

    public void DemoButtonAction(ActionEvent event) {

        startReserveTime.setText("08.50");
        endReserveTime.setText("14.50");
        noOfGuests.setText("100");
        hallName.setValue("Crystal Ballroom");
        manageEvent.setValue("No");
        reservationCost.setText("100000.00");
        customerID.setText("CUST1010");

    }

    public ManageReservationsController() {

    }

    //------VALIDATIONS FOR RESERVATIONS FORM------
    //Validation for empty fields
    //Validated so that noOGuests field cannot be kept empty
    private boolean validateFields() {
        if (noOfGuests.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Empty Fields!");
            alert.setContentText("Please fill all the fields in the form to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Validated so that searchReservationID field cannot be kept empty
    private boolean emptySearchreservationIDField() {
        if (SearchreservationID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Search ReservationID Field Empty!");
            alert.setContentText("Please enter a valid number to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Validated so that startReserveTime field cannot be kept empty
    private boolean emptystartReserveTimeField() {
        if (startReserveTime.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Start Time Field Empty!");
            alert.setContentText("Please enter a valid time to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Validated so that endReserveTime field cannot be kept empty
    private boolean emptyendReserveTimeField() {
        if (endReserveTime.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("End Time Field Empty!");
            alert.setContentText("Please enter a valid time to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }
     private boolean checkMinutes() {
         int x=Integer.parseInt(startReserveTime.getText());
         double y=Double.parseDouble(startReserveTime.getText());
          int a=Integer.parseInt(startReserveTime.getText());
         double b=Double.parseDouble(startReserveTime.getText());
         double mo=y % x;
          double mo1=b % a;
        if (mo>=0.6 && mo1 >=0.6) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("End Time Field Empty!");
            alert.setContentText("Please enter a valid time to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    private boolean chkoccupancy() {
        if (maxoccupancy < Integer.parseInt(noOfGuests.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("max occupancyreached!");
            alert.setContentText("max occupancyreached!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Validations apart from empty field validations
    //Validation for allowing only positive integer values for noOfGuests field
    private boolean validateNoOfGuestsField() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(noOfGuests.getText());
        if (m.find() && m.group().equals(noOfGuests.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in Number of Guests Field");
            alert.setContentText("Please enter a valid number! \n It should contain only positive integer values!");
            alert.showAndWait();
            return false;

        }
    }

    //Validation for allowing only positive integer values for SearchreservationID field
    private boolean validateSearchreservationIDField() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(SearchreservationID.getText());
        if (m.find() && m.group().equals(SearchreservationID.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in Search ReservationID Field");
            alert.setContentText("Please enter a valid number! \n It should contain only positive integer values!");
            alert.showAndWait();
            return false;

        }
    }

    //Validation for allowing only hour and minutes values for startReserveTime field
    //Validated for the 24-hour clock
    private boolean validatestartReserveTimeField() {
        boolean bool = false;
        Pattern p = Pattern.compile("[0-9][0-9][.][0-9][0-9]");
        Matcher m = p.matcher(startReserveTime.getText());
        if (Double.parseDouble(endReserveTime.getText()) <= 23.59) {
            bool = true;
        } else {
            bool = false;
        }
        if ((m.find() && m.group().equals(startReserveTime.getText())) && bool == true) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in Start Time Field");
            alert.setContentText("Please enter a valid time! \n It should be in the format HH.MM!");
            alert.showAndWait();
            return false;

        }
    }

    //Validation for allowing only hour and minutes values for endReserveTime field
    //Validated for the 24-hour clock
    private boolean validateendReserveTimeField() {
        boolean bool = false;
        Pattern p = Pattern.compile("[0-9][0-9][.][0-9][0-9]");
        Matcher m = p.matcher(endReserveTime.getText());
        if (Double.parseDouble(endReserveTime.getText()) <= 23.59) {
            bool = true;
        } else {
            bool = false;
        }
        if ((m.find() && m.group().equals(endReserveTime.getText())) && bool == true) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in End Time Field");
            alert.setContentText("Please enter a valid time! \n It should be in the format HH.MM!");
            alert.showAndWait();
            return false;

        }
    }

}
