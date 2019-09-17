
package CarRentCodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Rent {
       
    private StringProperty book_no;
    private StringProperty cust_id;
    private StringProperty Bdate;
    private StringProperty Rdate;
    private StringProperty type;
    private StringProperty deposite;
    private StringProperty vehi_id;
    private StringProperty driver_id;
    private StringProperty fuel;
    private StringProperty status;
  
    public Rent(String book_no ,String cust_id,String Bdate,String Rdate,String type,String deposite,String vehi_id,String driver_id,String fuel,String status) {
        this.book_no = new SimpleStringProperty(book_no );
        this.cust_id = new SimpleStringProperty(cust_id);
        this.Bdate   = new SimpleStringProperty(Bdate);
        this.Rdate= new SimpleStringProperty(Rdate);
        this.type= new SimpleStringProperty(type);
        this.deposite = new SimpleStringProperty(deposite);
        this.vehi_id= new SimpleStringProperty(vehi_id);
        this.driver_id = new SimpleStringProperty(driver_id);
        this.fuel=new SimpleStringProperty(fuel);
        this.status = new SimpleStringProperty(status);
        
    }
    
    Rent() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
     public String getBook_no() {
        return book_no.get();
    }

    public String getCust_id() {
        return cust_id.get();
    }

    public String getBookDate() {
        return Bdate.get();
    }
    public String getReturnDate() {
        return Rdate.get();
    }
    public String getType() {
        return type.get();
    }
     public String getDeposite() {
        return deposite.get();
    }

    public String getVehicle_id() {
        return vehi_id.get();
    }

    public String getDriver_id() {
        return driver_id.get();
    }
    
    
    public void setBook_no(String value) {
        book_no.set(value);
    }

    public void setCust_id(String value) {
        cust_id.set(value);
    }

    public void setBookDate(String value) {
        Bdate.set(value);
    }
    public void setReturnDate(String value) {
        Rdate.set(value);
    }
    public void setType(String value) {
        type.set(value);
    }

    public void setDeposite(String value) {
        deposite.set(value);
    }

    public void setVehicle_id(String value) {
        vehi_id.set(value);
    }

    public void setDriver_id(String value) {
        driver_id.set(value);
    }
    
    
    
    
    public StringProperty book_noProperty() {
        return book_no;
    }

    public StringProperty cust_idProperty() {
        return cust_id;
    }

    public StringProperty BdateProperty() {
        return Bdate;
    }

    public StringProperty RdateProperty() {
        return Rdate;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty depositeProperty() {
        return deposite;
    }

    public StringProperty vehi_idProperty() {
        return vehi_id;
    }
    public StringProperty driver_idProperty() {
        return driver_id;
    }
    public StringProperty fuelProperty() {
        return fuel;
    }
    
    public StringProperty statusProperty() {
        return status;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
