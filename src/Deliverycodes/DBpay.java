/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deliverycodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pavilion 15
 */
public class DBpay {
     private StringProperty Deliver_ID;
    private StringProperty Driver;
    private StringProperty Cost;
    private StringProperty status;
    
      public DBpay(String Deliver_ID, String Driver, String Cost, String status) {
        this.Deliver_ID = new SimpleStringProperty(Deliver_ID);
        this.Driver = new SimpleStringProperty(Driver);
        this.Cost    = new SimpleStringProperty(Cost);
        this.status = new SimpleStringProperty(status);
        
    }
        public String getDeliver_ID() {
        return Deliver_ID.get();
    }

    public String getDriver() {
        return Driver.get();
    }

    public String getCost() {
        return Cost.get();
    }

    public String getstatus() {
        return status.get();
    }
    public void setDeliver_ID(String value ) {
       Deliver_ID.set(value);
    }

    public void setD_Driver(String value) {
        Driver.set(value);
    }

    public void setCost(String value) {
        Cost.set(value);
    }

    public void setstatus(String value) {
       status.set(value);
    }
     public StringProperty Deliver_IDProperty (){
        return Deliver_ID; 
    }
    
    public StringProperty DriverProperty (){
        return Driver; 
    }
    
    public StringProperty CostProperty (){
        return Cost; 
    }
    
    public StringProperty statusProperty (){
        return status; 
    }
}
