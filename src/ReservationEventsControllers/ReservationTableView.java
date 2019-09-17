/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationEventsControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anushka_Hapukotuwa
 */
public class ReservationTableView {

    private SimpleStringProperty ReservationID;
    private SimpleStringProperty ReservationDateTime;
    private SimpleStringProperty NumberOfGuests;
    private SimpleStringProperty HallName;
    private SimpleStringProperty ManageEvent;
    private SimpleStringProperty ReservationCost;
    private SimpleStringProperty StartTime;
    private SimpleStringProperty EndTime;
    private SimpleStringProperty CustomerID;

    public ReservationTableView(String ReservationID, String ReservationDateTime, String NumberOfGuests, String HallName, String ManageEvent, String ReservationCost, String StartTime, String EndTime, String CustomerID) {
        this.ReservationID = new SimpleStringProperty(ReservationID);
        this.ReservationDateTime = new SimpleStringProperty(ReservationDateTime);
        this.NumberOfGuests = new SimpleStringProperty(NumberOfGuests);
        this.HallName = new SimpleStringProperty(HallName);
        this.ManageEvent = new SimpleStringProperty(ManageEvent);
        this.ReservationCost = new SimpleStringProperty(ReservationCost);
        this.StartTime = new SimpleStringProperty(StartTime);
        this.EndTime = new SimpleStringProperty(EndTime);
        this.CustomerID = new SimpleStringProperty(CustomerID);
    }

    public ReservationTableView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public StringProperty ReservationIDProperty() {
        return ReservationID;
    }

    public StringProperty ReservationDateTimeProperty() {
        return ReservationDateTime;
    }

    public StringProperty NumberOfGuestsProperty() {
        return NumberOfGuests;
    }

    public StringProperty HallNameProperty() {
        return HallName;
    }

    public StringProperty ManageEventProperty() {
        return ManageEvent;
    }

    public StringProperty ReservationCostProperty() {
        return ReservationCost;
    }

    public StringProperty StartTimeProperty() {
        return StartTime;
    }

    public StringProperty EndTimeProperty() {
        return EndTime;
    }

    public StringProperty CustomerIDProperty() {
        return CustomerID;
    }

    
    
    public String getReservationID() {
        return ReservationID.get();
    }

    public String getReservationDateTime() {
        return ReservationDateTime.get();
    }

    public String getNumberOfGuests() {
        return NumberOfGuests.get();
    }

    public String getHallName() {
        return HallName.get();
    }

    public String getManageEvent() {
        return ManageEvent.get();
    }

    public String getReservationCost() {
        return ReservationCost.get();
    }

    public String getStartTime() {
        return StartTime.get();
    }

    public String getEndTime() {
        return EndTime.get();
    }

    public String getCustomerID() {
        return CustomerID.get();
    }
    
    

    public void setReservationID(String value) {
        ReservationID.set(value);
    }

    public void setReservationDateTime(String value) {
        ReservationDateTime.set(value);
    }

    public void setNumberOfGuests(String value) {
        NumberOfGuests.set(value);
    }

    public void setHallName(String value) {
        HallName.set(value);
    }

    public void setManageEvent(String value) {
        ManageEvent.set(value);
    }

    public void setReservationCost(String value) {
        ReservationCost.set(value);
    }
    
    public void setStartTime(String value) {
        StartTime.set(value);
    }

    public void setEndTime(String value) {
        EndTime.set(value);
    }

    public void setCustomerID(String value) {
        CustomerID.set(value);
    }

}
