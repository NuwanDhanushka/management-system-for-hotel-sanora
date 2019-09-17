/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import ReservationEventsControllers.ReservationEventsSubMenuListController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Anushka_Hapukotuwa
 */

//Implements Reservations and Event Handling Main Home UI
public class EventHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Manage Reservation
    @FXML
  private void ManageEventsres(ActionEvent event) throws IOException {
      //ReservationEventsSubMenuListController.resListState=1;
        ReservationEventsSubMenuListController.resListState=3;
        ReservationEventsSubMenuListController.resListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Reservations and Events - Reservations");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageReservations.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));
    }  
    
    //Manage Events
     @FXML
    private void ManageEventsButtonAction(ActionEvent event) throws IOException {
    ReservationEventsSubMenuListController.resListState=1;
        ReservationEventsSubMenuListController.resListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Reservations and Events - Events");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageEvents.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml"))); 
       
    }
    
    //Manage Reservation and Event Payments
     @FXML
    private void ManageReservationPaymentsButtonAction(ActionEvent event) throws IOException {
        ReservationEventsSubMenuListController.resListState=3;
        ReservationEventsSubMenuListController.resListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Reservations and Events - Payments");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManagePayments.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));
      
    }
    
    //Manage Event Packages
 
    
    //Reports and Analysis
     @FXML
    private void ReservationEventsReportsButtonAction(ActionEvent event) throws IOException {
    ReservationEventsSubMenuListController.resListState=3;
        ReservationEventsSubMenuListController.resListState=1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Reservations and Events - Reports");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageReports.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SubHomeController subHomeC = MainHomeController.subHome.getController();
         subHomeC.backBtn.setVisible(false);
    }    
    
}
