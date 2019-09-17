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
public class DBcate {
    
     private StringProperty Catering_id;
    private StringProperty Customer_name;
    private StringProperty Cust_id;
    private StringProperty NIC;
    private StringProperty Phone_num;
    private StringProperty pakg;
    private StringProperty date;
     private StringProperty evengt_date;
    private StringProperty supervisor;
     private StringProperty qty;
    private StringProperty extra_items;
    private StringProperty Cost;
    private StringProperty status;
    
    
    
    public DBcate(String Catering_id, String Customer_name, String Cust_id, String NIC, String Phone_num, String pakg,String date,String evengt_date,String supervisor,String qty,String extra_items,String Cost,String status ) {
        this.Catering_id = new SimpleStringProperty(Catering_id);
         this.Customer_name = new SimpleStringProperty(Customer_name);
        this.Cust_id    = new SimpleStringProperty(Cust_id);
        this.NIC = new SimpleStringProperty(NIC);
        this.Phone_num = new SimpleStringProperty(Phone_num);
        this.pakg = new SimpleStringProperty(pakg);
        this.date = new SimpleStringProperty(date );
        this.evengt_date = new SimpleStringProperty(evengt_date );
        this. supervisor = new SimpleStringProperty( supervisor);
         this.qty = new SimpleStringProperty(qty);
        this.extra_items = new SimpleStringProperty(extra_items);
        this.Cost = new SimpleStringProperty(Cost);
        this.status = new SimpleStringProperty(status);
    
    }
    
    public DBcate( String Customer_name,  String NIC, String Phone_num, String pakg,String evengt_date,String supervisor,String qty,String extra_items,String Cost ) {
        
         this.Customer_name = new SimpleStringProperty(Customer_name);
        this.NIC = new SimpleStringProperty(NIC);
        this.Phone_num = new SimpleStringProperty(Phone_num);
        this.pakg = new SimpleStringProperty(pakg);
        this.evengt_date = new SimpleStringProperty(evengt_date );
        this. supervisor = new SimpleStringProperty( supervisor);
         this.qty = new SimpleStringProperty(qty);
        this.extra_items = new SimpleStringProperty(extra_items);
        this.Cost = new SimpleStringProperty(Cost);
        
    
    }
    public DBcate(String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getCatering_id () {
        return Catering_id .get();
    }

    public String getCustomer_name () {
        return Customer_name .get();
    }

    public String getCust_id () {
        return Cust_id .get();
    }

    public String getNIC() {
        return NIC.get();
    }

    public String getPhone_num() {
        return Phone_num.get();
    }

    public String getpakg() {
        return pakg.get();
    }
    public String getdate() {
        return date.get();
    }
    public String getevengt_date() {
        return evengt_date.get();
    }
    public String getsupervisor() {
        return supervisor.get();
    }
     public String getqty () {
        return qty .get();
    }
    public String getextra_items() {
        return extra_items.get();
    }
    public String getCost() {
        return Cost.get();
    }
    public String getstatus() {
        return status.get();
    }
    
    public void setCatering_id(String value) {
        Catering_id.set(value);
    }

    public void setCustomer_name(String value ) {
        Customer_name.set(value);
    }

    public void setCust_id (String value) {
        Cust_id .set(value);
    }

    public void setNIC(String value) {
        NIC.set(value);
    }

    public void setPhone_num(String value) {
       Phone_num.set(value);
    }

    public void setpakg(String value) {
        pakg.set(value);
    }
    public void setdate(String value) {
       date.set(value);
    }
    public void setevengt_date(String value) {
      evengt_date.set(value);
    }
    public void setsupervisor(String value) {
      supervisor.set(value);
    }
      public void setqty(String value) {
        qty.set(value);
    }
    public void setextra_items(String value) {
       extra_items.set(value);
    }
    public void setCost(String value) {
      Cost.set(value);
    }
    public void setstatus(String value) {
      status.set(value);
    }
    
    
    
    public StringProperty  Catering_idProperty (){
        return  Catering_id; 
    }
    
    public StringProperty Customer_nameProperty (){
        return Customer_name; 
    }
    
    public StringProperty Cust_idProperty (){
        return Cust_id ; 
    }
    
    public StringProperty NICProperty (){
        return NIC; 
    }
    
    public StringProperty Phone_numProperty (){
        return Phone_num;
    }
    
    public StringProperty pakgProperty (){
        return pakg;
    }
    
    public StringProperty  dateProperty (){
        return  date;
    }
    public StringProperty  evengt_dateProperty (){
        return  evengt_date;
    }
    public StringProperty  supervisorProperty (){
        return supervisor;
    }
    public StringProperty qtyProperty (){
        return qty;
    }
    
    public StringProperty  extra_itemsProperty (){
        return  extra_items;
    }
    public StringProperty  CostProperty (){
        return  Cost;
    }
    public StringProperty  statusProperty (){
        return status;
    }
}
