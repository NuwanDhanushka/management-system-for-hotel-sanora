/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMPcodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SaKU
 */
public class Dbpay {
    
    private StringProperty E_id;
    private StringProperty BasicSalary;
    private StringProperty BonusSalary;
    private StringProperty payWday;
    private StringProperty payWhrs;
    private StringProperty TotSalary;
    private StringProperty nameS;
    
    
    public Dbpay(String E_id, String BasicSalary, String BonusSalary, String payWday, String payWhrs, String TotSalary,String nameS) {
        this.E_id = new SimpleStringProperty(E_id);
        this.BasicSalary = new SimpleStringProperty(BasicSalary);
        this.BonusSalary    = new SimpleStringProperty(BonusSalary);
        this.payWday = new SimpleStringProperty(payWday);
        this.payWhrs = new SimpleStringProperty(payWhrs);
        this.TotSalary = new SimpleStringProperty(TotSalary);
        this.nameS = new SimpleStringProperty(nameS);
    }
    
     public String getPE_id() {
        return E_id.get();
    }

    public String getBasicSalary() {
        return BasicSalary.get();
    }

    public String getBonusSalary() {
        return BonusSalary.get();
    }

    public String getpayWday() {
        return payWday.get();
    }

    public String getpayWhrs() {
        return payWhrs.get();
    }

    public String getTotSalary() {
        return TotSalary.get();
    }
    public String getnameS() {
        return nameS.get();
    }
    
    
    public void setPE_id(String value) {
        E_id.set(value);
    }

    public void setBasicSalary(String value ) {
        BasicSalary.set(value);
    }

    public void setBonusSalary(String value) {
        BonusSalary.set(value);
    }

    public void setpayWday(String value) {
        payWday.set(value);
    }

    public void setpayWhrs(String value) {
       payWhrs.set(value);
    }

    public void setTotSalary(String value) {
        TotSalary.set(value);
    }
    public void setnameS(String value) {
        nameS.set(value);
    }
    
    
    public StringProperty PE_idProperty (){
        return E_id; 
    }
    
    public StringProperty BasicSalaryProperty (){
        return BasicSalary; 
    }
    
    public StringProperty BonusSalaryProperty (){
        return BonusSalary; 
    }
    
    public StringProperty payWdayProperty (){
        return payWday; 
    }
    
    public StringProperty payWhrsProperty (){
        return payWhrs;
    }
    
    public StringProperty TotSalaryProperty (){
        return TotSalary;
    }
    
    public StringProperty nameSProperty (){
        return nameS;
    }
    
}
