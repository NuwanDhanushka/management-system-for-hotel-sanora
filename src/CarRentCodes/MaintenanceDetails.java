
package CarRentCodes;

import Main.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert;



public class MaintenanceDetails {
   Connection con=null;
   PreparedStatement pst=null;
    
    
    public MaintenanceDetails(){
        con=DBconnect.dbconnect();
    }
  //********************************************add***********************************************  
    public String addMaintenance(String dvehi_reg, String dml_service, String dinterval_ml, String dmonth, String dlsDate, String da_paid, String dnsDate, String dexpDate) {
      
          String exception="";
        try {
            String q = "INSERT INTO maintenance(vehi_reg,m_service,interval_ml,month,lsDate,a_paid,nsDate,expDate)VALUES('" + dvehi_reg + "','" + dml_service + "','" + dinterval_ml + "','" + dmonth + "','" + dlsDate + "','" + da_paid + "','" + dnsDate + "','" + dexpDate + "')";

            pst = con.prepareStatement(q);
            pst.execute();
        } catch (Exception e) {
               
                 exception = e.getMessage();
                 System.out.println( exception);  
                 
        }
      return exception;
      
      
    }
//****************************************edit **********************************************    
    public void editMaintenance(String dvehi_reg, String dml_service, String dinterval_ml, String dmonth, String dlsDate, String da_paid, String dnsDate, String dexpDate) {
             String exception="";
        try {

            String sql = "UPDATE maintenance SET vehi_reg='"+dvehi_reg+"', m_service='" + dml_service + "',interval_ml='" + dinterval_ml + "',month='" + dmonth + "',lsDate='" + dlsDate + "',a_paid='" + da_paid + "',nsDate='" + dnsDate + "',expDate='" + dexpDate + "' WHERE vehi_reg='" + dvehi_reg + "'";

            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
                System.out.println(e);  
                exception = e.getMessage();
        }
       
    }
    
//*******************************************delete*************************************************************  
       public void deleteMaintenance(String dvehi_reg) {

        try {
            String sql = "DELETE from maintenance WHERE vehi_reg='" + dvehi_reg + "'";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
    
}
