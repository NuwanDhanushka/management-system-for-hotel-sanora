
package CarRentCodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Payment_Day {
    
    private StringProperty bill_no;
    private StringProperty booked_no;
    private StringProperty bill_date;
    private StringProperty bill_time;
    private StringProperty cust_id;
    private StringProperty ex_day;
    private StringProperty nof_km;
    private StringProperty damage_ch;
    private StringProperty driver_ch;
    private StringProperty fuel_ch;
    private StringProperty milage_l;
    private StringProperty total;
    private StringProperty cust_info;
    private StringProperty cust_des;

    

    public Payment_Day(String bill_no, String booked_no, String bill_date,String bill_time,String cust_id, String ex_day,String nof_km, String damage_ch, String driver_ch,String fuel_ch,String milage_l, String total,String cust_info,String cust_des) {

        this.bill_no = new SimpleStringProperty(bill_no);
        this.booked_no = new SimpleStringProperty(booked_no);
        this.bill_date = new SimpleStringProperty(bill_date);
        this.bill_time=new SimpleStringProperty(bill_time);
        this.cust_id=new SimpleStringProperty(cust_id);
        this.ex_day = new SimpleStringProperty(ex_day);
        this.nof_km = new SimpleStringProperty(nof_km);
        this.damage_ch = new SimpleStringProperty(damage_ch);
        this.driver_ch = new SimpleStringProperty(driver_ch);
        this.fuel_ch = new SimpleStringProperty(fuel_ch);
        this.milage_l = new SimpleStringProperty(milage_l);        
        this.total = new SimpleStringProperty(total);
         this.cust_info = new SimpleStringProperty(cust_info);
        this.cust_des = new SimpleStringProperty(cust_des);  
   
    }

    Payment_Day() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getBill_no() {
        return bill_no.get();
    }

    public String getBooked_no() {
        return booked_no.get();
    }

    public String getBill_date() {
        return bill_date.get();
    }
    public String getBill_time() {
        return bill_time.get();
    }
    
    public String getCust_id() {
        return cust_id.get();
    }
    
    public String getEx_day() {
        return ex_day.get();
    }

     public String getNof_km() {
        return nof_km.get();
    }
    public String getDamage_ch() {
        return damage_ch.get();
    }

    public String getDriver_ch() {
        return driver_ch.get();
    }

    public String getTotal() {
        return total.get();
    }

    public void setBill_no(String value) {
        bill_no.set(value);
    }

    public void setBooked_no(String value) {
        booked_no.set(value);
    }

    public void setBill_date(String value) {
        bill_date.set(value);
    }
    public void setBill_time(String value) {
        bill_time.set(value);
    }
    public void setCust_id(String value) {
        cust_id.set(value);
    }
    public void setEx_day(String value) {
        ex_day.set(value);
    }
    public void setNof_km(String value) {
        nof_km.set(value);
    }
    public void setDamage_ch(String value) {
        damage_ch.set(value);
    }

    public void setDriver_ch(String value) {
        driver_ch.set(value);
    }

    public void setTotal(String value) {
        total.set(value);
    }

    public StringProperty bill_noProperty() {
        return bill_no;
    }

    public StringProperty booked_noProperty() {
        return booked_no;
    }

    public StringProperty bill_dateProperty() {
        return bill_date;
    }
    public StringProperty bill_timeProperty() {
        return bill_time;
    }
    
    public StringProperty cust_idProperty() {
        return cust_id;
    }

    
    public StringProperty ex_dayProperty() {
        return ex_day;
    }
     public StringProperty nof_kmProperty() {
        return nof_km;
    }

    public StringProperty damage_chProperty() {
        return damage_ch;
    }

    public StringProperty driver_chProperty() {
        return driver_ch;
    }

    public StringProperty totalProperty() {
        return total;
    }
     
    
    public StringProperty fuel_chProperty() {
        return fuel_ch;
    }

    public StringProperty milage_lProperty() {
        return milage_l;
    }
      public StringProperty cust_infoProperty() {
        return cust_info;
    }

    public StringProperty cust_desProperty() {
        return cust_des;
    }
 
    
    
    
    
    
    
    
    
    
 
    
    
    
}
