/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accountcontrollers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author U Computers
 */
public class tableloancontroller {

    private SimpleStringProperty lname;
    private SimpleStringProperty lirate;
    private SimpleStringProperty lcapital;
    private SimpleStringProperty lamount;
     private SimpleStringProperty monthlypay;
    private SimpleStringProperty remainingamt;

    public tableloancontroller(String name, String ir, String capital, String total) {
        this.lname = new SimpleStringProperty(name);
        this.lirate = new SimpleStringProperty(ir);
        this.lcapital = new SimpleStringProperty(capital);
        this.lamount = new SimpleStringProperty(total);

    }
      public tableloancontroller(String name, String monthlypay, String remainingamt) {
        this.lname = new SimpleStringProperty(name);
        this.monthlypay = new SimpleStringProperty(monthlypay);
        this.remainingamt = new SimpleStringProperty(remainingamt);
       

    }

    tableloancontroller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getlname() {
        return lname.get();
    }

    public String getlirate() {
        return lirate.get();
    }

    public String getlcapital() {
        return lcapital.get();
    }

    public String getlamount() {
        return lamount.get();
    }
    
    
    
    
      public String getmonthlypay() {
        return monthlypay.get();
    }

    public String getremainingamt() {
        return remainingamt.get();
    }
    
    
    

    public void setlname(String value) {
        lname.set(value);
    }

    public void setlirate(String value) {
        lirate.set(value);
    }

    public void setlcapital(String value) {
        lcapital.set(value);
    }

    public void setaddlamount(String value) {
        lamount.set(value);
    }

    
    
       public void setmonthlypay(String value) {
        monthlypay.set(value);
    }

    public void setremainingamt(String value) {
        remainingamt.set(value);
    }
 
    
    
    
    
    public StringProperty lnameProperty() {
        return lname;
    }

    public StringProperty lirateProperty() {
        return lirate;
    }

    public StringProperty lcapitalProperty() {
        return lcapital;
    }

    public StringProperty lamountProperty() {
        return lamount;
    }

    
    
        public StringProperty  monthlypayProperty() {
        return monthlypay;
    }

    public StringProperty remainingamtProperty() {
        return remainingamt;
    }
}
