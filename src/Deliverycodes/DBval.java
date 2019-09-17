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
public class DBval {
    
     private StringProperty Deliver_ID;
    private StringProperty D_phone;
    private StringProperty cutomer_name;
    private StringProperty Cust_id;
    private StringProperty Item;
    private StringProperty Item_code;
      private StringProperty Qty;
    private StringProperty payment_type;
    private StringProperty Date;
    private StringProperty Driver;
    private StringProperty Cost;
    private StringProperty status;


    public DBval(String Deliver_ID, String D_phone, String cutomer_name, String Cust_id, String Item, String Item_code,String Qty, String payment_type,String Date, String Driver, String Cost, String status) {
        this.Deliver_ID = new SimpleStringProperty(Deliver_ID);
        this.D_phone = new SimpleStringProperty(D_phone);
        this.cutomer_name = new SimpleStringProperty(cutomer_name);
        this.Cust_id = new SimpleStringProperty(Cust_id);
        this.Item= new SimpleStringProperty( Item);
        this.Item_code= new SimpleStringProperty(Item_code);
         this.Qty = new SimpleStringProperty(Qty);
        this.payment_type = new SimpleStringProperty(payment_type);
        this.Date = new SimpleStringProperty(Date);
        this.Driver = new SimpleStringProperty(Driver);
        this.Cost= new SimpleStringProperty( Cost);
        this.status= new SimpleStringProperty(status);
        
    
}
    public DBval( String D_phone, String cutomer_name, String Cust_id, String Item, String Item_code,String Qty) {
        
        this.D_phone = new SimpleStringProperty(D_phone);
        this.cutomer_name = new SimpleStringProperty(cutomer_name);
        this.Cust_id = new SimpleStringProperty(Cust_id);
        this.Item= new SimpleStringProperty( Item);
        this.Item_code= new SimpleStringProperty(Item_code);
         this.Qty = new SimpleStringProperty(Qty);
       
        
        
        
    
}
    public String getDeliver_ID() {
        return Deliver_ID.get();
    }

    public String getD_phone() {
        return D_phone.get();
    }

    public String getcutomer_name() {
        return cutomer_name.get();
    }

    public String getCust_id() {
        return Cust_id.get();
    }

    public String getItem() {
        return Item.get();
    }

    public String getItem_code() {
        return Item_code.get();
    }
     public String getQty() {
        return Qty.get();
    }

    public String getpayment_type() {
        return payment_type.get();
    }

    public String getDate() {
        return Date.get();
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
        
    public void setDeliver_ID(String value) {
      Deliver_ID.set(value);
    }

    public void setD_phone(String value ) {
        D_phone.set(value);
    }

    public void setcutomer_name(String value) {
        cutomer_name.set(value);
    }

    public void setCust_id(String value) {
       Cust_id.set(value);
    }

    public void setItem(String value) {
      Item.set(value);
    }

    public void setItem_code(String value) {
      Item_code.set(value);
    }
        public void setQty(String value) {
      Qty.set(value);
    }

    public void setpayment_type(String value ) {
        payment_type.set(value);
    }

    public void setDate(String value) {
        Date.set(value);
    }

    public void setDriver(String value) {
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
    
    public StringProperty D_phoneProperty (){
        return D_phone; 
    }
    
    public StringProperty cutomer_nameProperty (){
        return cutomer_name; 
    }
    
    public StringProperty Cust_idProperty (){
        return Cust_id; 
    }
    
    public StringProperty ItemProperty (){
        return Item;
    }
    
    public StringProperty Item_codeProperty (){
        return Item_code;
    }
     public StringProperty  QtyProperty (){
        return  Qty; 
    }
    
    public StringProperty  payment_typeProperty (){
        return  payment_type; 
    }
    
    public StringProperty  DateProperty (){
        return  Date; 
    }
    
    public StringProperty  DriverProperty (){
        return Driver; 
    }
    
    public StringProperty CostProperty (){
        return Cost;
    }
    
    public StringProperty statusProperty (){
        return status;
    }
}
