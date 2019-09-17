
package CarRentCodes;

import Main.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

public class RentDetails {
   Connection con=null;
   PreparedStatement pst=null;
   PreparedStatement ps=null;
   PreparedStatement ps1=null;
    ResultSet rs = null;
      ResultSet rs1 = null;
        
    public RentDetails(){
        con=DBconnect.dbconnect();
    }
    
    
    public String addRent(String dbook_no, String dcust_id, String dBdate, String dRdate, String dtype, String ddeposite, String dvehi_id, String ddriver_id,String dfuel) {
        String exception = "";
        String avl="Reserved";
        try {
            String q = "INSERT INTO rent(book_no,cust_id,Bdate,Rdate,type,deposite,vehi_id,driver_id,fuel)VALUES('" + dbook_no + "','" + dcust_id + "','" + dBdate + "','" + dRdate + "','" + dtype + "','" + ddeposite + "','" + dvehi_id + "','" + ddriver_id + "','"+dfuel+"')";
             pst = con.prepareStatement(q);
             pst.execute();
            String sql = "UPDATE vehicle SET availability='"+avl+"' where reg_no='"+dvehi_id +"'";
             ps = con.prepareStatement(sql);
             ps.execute();
            String qq="UPDATE driver SET in_dr=0 where driver='"+ddriver_id +"'";
           
            ps1 = con.prepareStatement(qq);
            ps1.execute();
          
            
        } catch (Exception e) {
           System.out.println(e);
            exception = e.getMessage();
        }
         return exception;
    }
    
    
    public void editRent(String dbook_no, String dcust_id, String dBdate, String dRdate, String dtype, String ddeposite, String dvehi_id, String ddriver_id,String dfuel) {
        String cur="";
        String avl="";
       String query="select vehi_id from rent where book_no='"+dbook_no+"' ";
       String query1="select driver_id from rent where book_no='"+dbook_no+"' ";
       
      try {
          pst = con.prepareStatement(query);
          rs=pst.executeQuery();
          
          
          
          while(rs.next())
          {
             cur=(rs.getString("vehi_id"));
              
          }
         
         if(cur.equals(dvehi_id)){
           String sql = "UPDATE rent SET Bdate='" +dBdate+ "',Rdate='" + dRdate + "',type='" + dtype + "',deposite='" + ddeposite + "',vehi_id='" + dvehi_id + "',driver_id='" + ddriver_id + "' ,fuel='"+dfuel+"' WHERE book_no='" + dbook_no + "'";

            pst = con.prepareStatement(sql);
            pst.execute();
              
                  
        }      
        else{
             avl="Available";  
             String sql = "UPDATE vehicle SET availability='"+avl+"' where reg_no='" + cur + "'";
             pst = con.prepareStatement(sql);
             pst.execute();
             String qq = "UPDATE rent SET Bdate='" + dBdate + "',Rdate='" + dRdate + "',type='" + dtype + "',deposite='" + ddeposite + "',vehi_id='" + dvehi_id + "',driver_id='" + ddriver_id + "',fuel='"+dfuel+"' WHERE book_no='" + dbook_no + "'";
             pst = con.prepareStatement(qq);
             pst.execute();
             avl="Reserved";
             String pp = "UPDATE vehicle SET availability='"+avl+"' where reg_no='" + dvehi_id + "'";
             pst = con.prepareStatement(pp);
             pst.execute();
           
             
             
             

              }
         
          
      } catch (SQLException ex) {
         
          System.out.println(ex);
      }
      
      
      try{
          ps1 = con.prepareStatement(query1);
          rs1=ps1.executeQuery();
           while(rs1.next())
          {
             cur=(rs1.getString("driver_id"));
              
          }
          if(cur.equals(ddriver_id)){
              
           String sql = "UPDATE rent SET Bdate='" +dBdate+ "',Rdate='" + dRdate + "',type='" + dtype + "',deposite='" + ddeposite + "',vehi_id='" + dvehi_id + "',driver_id='" + ddriver_id + "',fuel='"+dfuel+"' WHERE book_no='" + dbook_no + "'";

            pst = con.prepareStatement(sql);
            pst.execute();
          }
          else{
               
             String sql = "UPDATE driver SET in_dr=1 where driver='" + cur + "'";
             pst = con.prepareStatement(sql);
             pst.execute();
             String qq = "UPDATE rent SET Bdate='" + dBdate + "',Rdate='" + dRdate + "',type='" + dtype + "',deposite='" + ddeposite + "',vehi_id='" + dvehi_id + "',driver_id='" + ddriver_id + "',fuel='"+dfuel+"' WHERE book_no='" + dbook_no + "'";
             pst = con.prepareStatement(qq);
             pst.execute();
            
             String pp = "UPDATE  driver SET in_dr=0 where  driver='" + ddriver_id + "'";
             pst = con.prepareStatement(pp);
             pst.execute();
           
              
              
          }
          
      }  
       catch (Exception e) {
         
          System.out.println(e);
      } 
        
        
       

    }
    
      public void deleterent(String dbook_no,String dvehi_id,String ddriver_id) {
        String avl="Available";
        try {
            String sql ="DELETE from rent WHERE book_no='" + dbook_no + "'";
            String q = "UPDATE vehicle SET availability='"+avl+"' where reg_no='"+dvehi_id +"'";
             String qq="UPDATE driver SET in_dr=1 where driver='"+ddriver_id +"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            ps = con.prepareStatement(q);
            ps1 = con.prepareStatement(qq);
            ps.execute();
            ps1.execute();
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
 //*******************************************not Returned*************************************************   
    public int calc_extraDays(String date, String re_Date) {

        LocalDate da = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        int year_sys = da.getYear();
        int month_sys = da.getMonthValue();
        int day_sys = da.getDayOfMonth();

        LocalDate re = LocalDate.parse(re_Date, DateTimeFormatter.ISO_DATE);

        int year_re = re.getYear();
        int month_re = re.getMonthValue();
        int day_re = re.getDayOfMonth();

        int ex_year = (year_sys - year_re);
        int ex_months = (month_sys - month_re);
        int ex_Days = (day_sys - day_re);

        int extra = ex_year * 365 + ex_months * 30 + ex_Days * 1;

        return extra;

    }
    public String returnDate_exceeed(String sys) {

        String ret = "";
        String pp = "";
        String query = "select Rdate  from rent ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                ret = (rs.getString("Rdate"));

                int no = calc_extraDays(sys, ret);

                if (no > 0) {

                    String qq = "select book_no  from rent where Rdate='" + ret + "' ";
                    pst = con.prepareStatement(qq);
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        String vehi = (rs.getString("book_no"));
                        System.out.println(vehi);
                        return vehi;
                    }

                } else {

                }

            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        

        return pp;

    }

    public void load_exceed(String sys) {

        String value = returnDate_exceeed(sys);
        System.out.println(value);
        if (value.equals("")) {

        } else {

            String stat = "Not Returned";
            String sql = "UPDATE rent SET status='" + stat + "' where book_no='" + value + "' ";
            try {

                pst = con.prepareStatement(sql);
                pst.execute();

                String qu = "SELECT * From rent";
               
            } catch (SQLException ex) {

                System.out.println(ex);
            }

        }

    }


    
    
    
    
    
    
    
    
    
    
    
    
}
