
package CarRentCodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Maintenance {
    
    private StringProperty vehi_reg;
    private StringProperty m_service;
    private StringProperty interval_ml;
    private StringProperty month;
    private StringProperty lsDate;
    private StringProperty a_paid;
    private StringProperty nsDate;
    private StringProperty expDate;
   
    public Maintenance(String vehi_reg,String m_service, String interval_ml,String month,String lsDate, String a_paid, String nsDate, String expDate) {
        this.vehi_reg = new SimpleStringProperty(vehi_reg);
        this.m_service=new SimpleStringProperty( m_service);
        this.interval_ml = new SimpleStringProperty(interval_ml);
        this.month = new SimpleStringProperty(month);
        this.lsDate = new SimpleStringProperty(lsDate);
        this.a_paid = new SimpleStringProperty(a_paid);
        this.nsDate = new SimpleStringProperty(nsDate);
        
        this.expDate = new SimpleStringProperty(expDate);

    }
    
     Maintenance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public String getVehi_reg() {
        return vehi_reg.get();
    }
    public String getM_Service() {
        return m_service.get();
    }
   
     

    public String getInterval_ml() {
        return interval_ml.get();
    }
    public String getMonth() {
        return month.get();
    }


    public String getLastServicesDate() {
        return lsDate.get();
    }

    public String getAmountpaid() {
        return a_paid.get();
    }

    public String getNextServicesDate() {
        return nsDate.get();
    }

    
    public String getExpDate() {
        return expDate.get();
    }
    
    
    
     public void setReg_no(String value) {
        vehi_reg.set(value);
    }

    public void setMlService(String value) {
         m_service.set(value);
    }

    public void setInterval(String value) {
        interval_ml.set(value);
    }

    public void setMonth(String value) {
        month.set(value);
    }
    public void setLastServicesDate(String value) {
        lsDate.set(value);
    }
     public void setAmountpaid(String value) {
        a_paid.set(value);
    }

    public void setNextServicesDate(String value) {
        nsDate.set(value);
    }

  
    public void setExpDate(String value) {
        expDate.set(value);
    }
    
    
   
    
     public StringProperty reg_noProperty() {
        return vehi_reg;
    }

     public StringProperty m_serviceProperty(){
         return m_service;
     }
    

    public StringProperty intervalProperty() {
        return interval_ml;
    }
   public StringProperty monthProperty() {
        return month;
    }

    public StringProperty lsDateProperty() {
        return lsDate;
    }

    public StringProperty a_paidProperty() {
        return a_paid;
    }

    public StringProperty nsDateProperty() {
        return nsDate;
    }
   

    public StringProperty expDAteProperty() {
        return expDate;
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
