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
public class PaymentTableView {
    private SimpleStringProperty PaymentID;
    private SimpleStringProperty ReservationCost;
    private SimpleStringProperty EventCost;
    private SimpleStringProperty TotalAmount;
    private SimpleStringProperty ReservationID; 
    private SimpleStringProperty EventID;
    
    public PaymentTableView(String PaymentID, String ReservationCost, String EventCost, String TotalAmount, String ReservationID, String EventID) {
        this.PaymentID = new SimpleStringProperty(PaymentID);
        this.ReservationCost = new SimpleStringProperty(ReservationCost);
        this.EventCost = new SimpleStringProperty(EventCost);
        this.TotalAmount = new SimpleStringProperty(TotalAmount);
        this.ReservationID = new SimpleStringProperty(ReservationID);
        this.EventID = new SimpleStringProperty(EventID);
    }     
    
    public PaymentTableView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public StringProperty PaymentIDProperty (){
        return PaymentID; 
    }
    
    public StringProperty ReservationCostProperty (){
        return ReservationCost; 
    }
    
    public StringProperty EventCostProperty (){
        return EventCost; 
    }
    
    public StringProperty TotalAmountProperty (){
        return TotalAmount; 
    }
    
    public StringProperty ReservationIDProperty (){
        return ReservationID; 
    }
    
    public StringProperty EventIDProperty (){
        return EventID; 
    }

    
    public String getPaymentID() {
        return PaymentID.get();
    }

    public String getReservationCost() {
        return ReservationCost.get();
    }

    public String getEventCost() {
        return EventCost.get();
    }

    public String getTotalAmount() {
        return TotalAmount.get();
    }

    public String getReservationID() {
        return ReservationID.get();
    }
    public String getEventID() {
        return EventID.get();
    }
    
    

    
    public void setPaymentID(String value) {
        PaymentID.set(value);
    }

    public void setReservationCost(String value ) {
        ReservationCost.set(value);
    }
    
    public void setEventCost(String value) {
        EventCost.set(value);
    }

    public void setTotalAmount(String value) {
       TotalAmount.set(value);
    }

    public void setReservationID(String value) {
        ReservationID.set(value);
    }
    public void setEventID(String value) {
        EventID.set(value);
    }
}
