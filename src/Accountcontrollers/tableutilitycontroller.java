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
public class tableutilitycontroller {

    private SimpleStringProperty uBillID;
    private SimpleStringProperty uDate;
    private SimpleStringProperty uName;
    private SimpleStringProperty uAmount;

    public tableutilitycontroller(String uBillID, String uDate, String uName, String uAmount) {
        this.uBillID = new SimpleStringProperty(uBillID);
        this.uDate = new SimpleStringProperty(uDate);
        this.uName = new SimpleStringProperty(uName);
        this.uAmount = new SimpleStringProperty(uAmount);

    }

    tableutilitycontroller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String uBillID() {
        return uBillID.get();
    }

    public String uDate() {
        return uDate.get();
    }

    public String uName() {
        return uName.get();
    }

    public String uAmount() {
        return uAmount.get();
    }

    public void setuBillID(String value) {
        uBillID.set(value);
    }

    public void setuDate(String value) {
        uDate.set(value);
    }

    public void setuName(String value) {
        uName.set(value);
    }

    public void setuAmount(String value) {
        uAmount.set(value);
    }

    public StringProperty uBillIDProperty() {
        return uBillID;
    }

    public StringProperty uDateProperty() {
        return uDate;
    }

    public StringProperty uNameProperty() {
        return uName;
    }

    public StringProperty uAmountProperty() {
        return uAmount;
    }

}
