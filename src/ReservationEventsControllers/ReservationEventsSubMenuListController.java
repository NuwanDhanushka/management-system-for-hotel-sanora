/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationEventsControllers;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Anushka_Hapukotuwa
 */
public class ReservationEventsSubMenuListController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    //Manage Reservation sub menu list
    public static int resListState;
    public Button resReservationTileBtn,resEventTileBtn,resPaymentsTileBtn,resReportsTileBtn;
     @FXML
    private void ManageReservationsTabAction(ActionEvent event) throws IOException {
        resListState = 1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Reservations and Events - Reservations");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageReservations.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));

        
    }
    
    //Manage Events sub menu list
     @FXML
    private void ManageEventsTabAction(ActionEvent event) throws IOException {
        resListState = 2;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Reservations and Events - Events");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageEvents.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));

    }
    
    //Manage Payments sub menu list
     @FXML
    private void ManagePaymentsTabAction(ActionEvent event) throws IOException {
            resListState=3;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Reservations and Events - Payments");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManagePayments.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));  
    }
    
    //Manage reports and analysis sub menu list
     @FXML
    private void ManageReportsTabAction(ActionEvent event) throws IOException {
        resListState = 4;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        
        subHomeC.funcNamelbl.setText("Reservations and Events - Reports & Analysis");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManageReports.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));  
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFocusedResTab(resListState);
    }    
    public void setFocusedResTab(int i){
        if(i==1){
            resReservationTileBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            resReservationTileBtn.setOnAction(null);
        }
        if(i==2){
            resEventTileBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            resEventTileBtn.setOnAction(null);
        }
        if(i==3){
            resPaymentsTileBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            resPaymentsTileBtn.setOnAction(null);
        }
        if(i==4){
            resReportsTileBtn.setStyle("-fx-text-fill: #d3eaf7;"+"-fx-background-color: #02161d;"+"-fx-border-color:red;"+"-fx-border-width:0px 0px 0px 3px;");
            resReportsTileBtn.setOnAction(null);
        }
     }
}
