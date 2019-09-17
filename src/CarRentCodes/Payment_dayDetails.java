package CarRentCodes;

import Main.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment_dayDetails {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Payment_dayDetails() {
        con = DBconnect.dbconnect();
    }
   //***********************************************add payment****************************************************//
 public String addPayment_day(String dvehi,String dbill_no, String dbooked_no, String dbill_date,String dbill_time,String dcust, String dex_day, String dnof_km, String ddamage_ch, String ddriver_ch,String dfuel ,String dtotal,String dmil,String dcust_info,String dcust_des) {
         String exception = "";
        try {
            String  avl="Available";
            String sql = "UPDATE vehicle SET availability='"+avl+"',milage='"+dmil+"' where reg_no='"+dvehi +"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            
            String q = "INSERT INTO payment_day(bill_no,booked_no,bill_date,bill_time,cust_id,ex_day,nof_km,damage_ch,driver_ch,fuel_ch,milage_l,total,cust_info,cust_des)VALUES('" + dbill_no + "','" + dbooked_no + "','" + dbill_date + "','"+dbill_time+"','"+dcust+"','" + dex_day + "','" + dnof_km + "','" + ddamage_ch + "','" + ddriver_ch + "','"+dfuel+"','"+dmil+"','" + dtotal + "','"+dcust_info+"','"+dcust_des+"')";
            pst = con.prepareStatement(q);
            pst.execute();
            avl="Returned";
            String qq = "UPDATE rent SET status='"+avl+"' where book_no='"+dbooked_no +"'";
            pst = con.prepareStatement(qq);
            pst.execute();
            
            
            
            
            

        } catch (Exception e) {
            System.out.println(e);
            exception = e.getMessage();
        }
        return exception;
    }
 //*****************************************************edit payment*******************************//////////////////

 public String editPayment_day(String dbill_no, String dbooked_no, String dbill_date,String dbill_time,String dcust, String dex_day, String dnof_km, String ddamage_ch, String ddriver_ch,String dfuel,String dmil, String dtotal,String dvehi,String dcust_info,String dcust_des) {
        String exception = "";
        try {

            payment_edited(dbill_no);

            String qq = "UPDATE payment_day SET  bill_no='" + dbill_no + "',booked_no='" + dbooked_no + "' ,bill_date='" + dbill_date + "',bill_time='" + dbill_time + "',cust_id='" + dcust + "',ex_day='" + dex_day + "',nof_km='" + dnof_km + "',damage_ch='" + ddamage_ch + "',driver_ch='" + ddriver_ch + "',fuel_ch='" + dfuel + "',milage_l='" + dmil + "',total='" + dtotal + "' ,cust_info='" + dcust_info + "',cust_des='" + dcust_des + "' where bill_no='" + dbill_no + "' ";
            pst = con.prepareStatement(qq);
            pst.execute();

            String sql = "UPDATE vehicle SET milage='" + dmil + "' where reg_no='" + dvehi + "'";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
            exception = e.getMessage();
        }

        return exception;

    }

    public void payment_edited(String dbill_no) {

        String query = "select * from payment_day where bill_no='" + dbill_no + "'";

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String dbooked_no = rs.getString("booked_no");
                String dbill_date = rs.getString("bill_date");
                String dbill_time = rs.getString("bill_time");
                String dcust = rs.getString("cust_id");
                String dex_day = rs.getString("ex_day");
                String dnof_km = rs.getString("nof_km");
                String ddamage_ch = rs.getString("damage_ch");
                String ddriver_ch = rs.getString("driver_ch");
                String dfuel = rs.getString("fuel_ch");
                String dmil = rs.getString("milage_l");
                String dtotal = rs.getString("total");
                String dcust_info = rs.getString("cust_info");
                String dcust_des = rs.getString("cust_des");

                String q = "INSERT INTO payment_day_edit(bill_no,booked_no,bill_date,bill_time,cust_id,ex_day,nof_km,damage_ch,driver_ch,fuel_ch,milage_l,total,cust_info,cust_des)VALUES('" + dbill_no + "','" + dbooked_no + "','" + dbill_date + "','" + dbill_time + "','" + dcust + "','" + dex_day + "','" + dnof_km + "','" + ddamage_ch + "','" + ddriver_ch + "','" + dfuel + "','" + dmil + "','" + dtotal + "','" + dcust_info + "','" + dcust_des + "')";
                pst = con.prepareStatement(q);
                pst.execute();

            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }

    }

    //**************************************************calculate day payment******************////////////////////////
    public double calcTotal_Day(String vehi, String ex_dates, String damage_ch, String driver_ch, int nof_day, String fuel) {

        double day = 0, eday = 0, week = 0, eweek = 0, month = 0, emonth = 0, extra_amount = 0;
        double damage, driver, fuel_ch;
        int ex_date;
        double tot = 0;
        try {
            String sql = "select * from vehicle where reg_no='" + vehi + "' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {

                day = Double.parseDouble(rs.getString(11));
                eday = Double.parseDouble(rs.getString(12));
                week = Double.parseDouble(rs.getString(13));
                eweek = Double.parseDouble(rs.getString(14));
                month = Double.parseDouble(rs.getString(15));
                emonth = Double.parseDouble(rs.getString(16));

            }

            ex_date = Integer.parseInt(ex_dates);
            damage = Double.parseDouble(damage_ch);
            driver = Double.parseDouble(driver_ch);
            fuel_ch = Double.parseDouble(fuel);
           
            
            
            if (nof_day == 0) {
                
                if (ex_date == 0) {

                    extra_amount = day;
                    tot = (extra_amount + damage + driver);
                    //return tot;
                } else if (ex_date > 0 && ex_date < 7) {
                    extra_amount = day * nof_day + eday * ex_date;
                    tot = (extra_amount + damage + driver + fuel_ch);
                    // return tot;
                } else if (ex_date >= 7 && ex_date < 30) {
                    extra_amount = day * nof_day + eweek * ex_date;
                    tot = (extra_amount + damage + driver + fuel_ch);
                    // return tot;
                } else {
                    extra_amount = day * nof_day + emonth * ex_date;
                    tot = (extra_amount + damage + driver + fuel_ch);
                    //return tot;
                }

            } else if (nof_day == 7) {
                if (ex_date == 0) {

                    extra_amount = week;
                    tot = (extra_amount + damage + driver + fuel_ch);

                    // return tot;
                } else if (ex_date > 0 && ex_date < 7) {
                    extra_amount = week * nof_day + eday * ex_date;
                    tot = (extra_amount + damage + driver + fuel_ch);
                    // return tot;
                } else if (ex_date >= 7 && ex_date < 30) {
                    extra_amount = week * nof_day + eweek * ex_date;
                    tot = (extra_amount + damage + driver + fuel_ch);
                    // return tot;
                } else {
                    extra_amount = week * nof_day + emonth * ex_date;
                    tot = (extra_amount + damage + driver + fuel_ch);
                    // return tot;
                }

            } else if (ex_date == 30) {

                extra_amount = month;
                tot = (extra_amount + damage + driver + fuel_ch);
                // return tot;
            } else if (ex_date > 0 && ex_date < 7) {
                extra_amount = month * nof_day + eday * ex_date;
                tot = (extra_amount + damage + driver + fuel_ch);
                // return tot;
            } else if (ex_date >= 7 && ex_date < 30) {
                extra_amount = month * nof_day + eweek * ex_date;
                tot = (extra_amount + damage + driver + fuel_ch);
                //  return tot;
            } else {
                extra_amount = month * nof_day + emonth * ex_date;
                tot = (extra_amount + damage + driver + fuel_ch);
                // return tot;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Payment_dayDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tot;
    }

    public double calcTotal_Km(String vehi, String damage_ch, String driver_ch, String dnof_km, String fuel) {

        double km = 0, tot = 0;
        double nof_km, damage, driver, fuel_ch;

        nof_km = Double.parseDouble(dnof_km);
        damage = Double.parseDouble(damage_ch);
        driver = Double.parseDouble(driver_ch);
        fuel_ch = Double.parseDouble(fuel);

        try {

            String sql = "select * from vehicle where reg_no='" + vehi + "' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                km = Double.parseDouble(rs.getString(17));
            }
            tot = (km * nof_km + damage + driver + fuel_ch);

        } catch (SQLException ex) {
            Logger.getLogger(Payment_dayDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tot;

    }

}
