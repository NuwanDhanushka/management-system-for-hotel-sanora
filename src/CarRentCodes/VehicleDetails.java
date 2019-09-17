
package CarRentCodes;

import Main.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VehicleDetails {

    Connection con = null;
    PreparedStatement pst = null;
     ResultSet rs = null;
      ResultSet rs1 = null;

    public VehicleDetails() {
        con = DBconnect.dbconnect();
    }

    public String addVehicle(String dreg_no, String dmake, String dmodel, String dtype, String dyear, String dtrans, String davl, String dcondi, String dcolour, String dseat, String dday, String deday, String dweek, String deweek, String dmonth, String demonth, String dkm, String dtank,String fuel, String driver, String milage) {
        String exception = "";
        try {
            String q = "INSERT INTO vehicle(reg_no,make,model,type,year,transmission,availability,condi,colour,seats,day,eday,week,eweek,month,emonth,km,tank,fuel_ch,driver_ch,milage)VALUES('" + dreg_no + "','" + dmake + "','" + dmodel + "','" + dtype + "','" + dyear + "','" + dtrans + "','" + davl + "','" + dcondi + "','" + dcolour + "','" + dseat + "','" + dday + "','" + deday + "','" + dweek + "','" + deweek + "','" + dmonth + "','" + demonth + "','" + dkm + "','" + dtank + "','"+fuel+"','"+driver+"' ,'"+milage+"' )";

            pst = con.prepareStatement(q);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
            exception = e.getMessage();

        }
        return exception;

    }

    public void editVehicle(String dreg_no, String dmake, String dmodel, String dtype, String dyear, String dtrans, String davl, String dcondi, String dcolour, String dseat, String dday, String deday, String dweek, String deweek, String dmonth, String demonth, String dkm, String dtank,String fuel, String driver, String milage) {

        try {

            String sql = "UPDATE vehicle SET reg_no='" + dreg_no + "',make='" + dmake + "',model='" + dmodel + "',type='" + dtype + "',year='" + dyear + "',transmission='" + dtrans + "',availability='" + davl + "',condi='" + dcondi + "',colour='" + dcolour + "',seats='" + dseat + "',day='" + dday + "',eday='" + deday + "',week='" + dweek + "',eweek='" + deweek + "',month='" + dmonth + "',emonth='" + demonth + "',km='" + dkm + "',tank='" + dtank + "',fuel_ch='"+fuel+"',driver_ch='"+driver+"',milage='"+milage+"'  WHERE reg_no='" + dreg_no + "'";

            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public void deleteVehicle(String dreg_no) {

        try {
            String sql = "DELETE from vehicle WHERE reg_no='" + dreg_no + "'";
            pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

     public void nxt_Reserved(String sys) {

        String nxt = "";
        String vehi = "";
        String avl = "Reserved";
        String query = "select nsDate,vehi_reg  from maintenance ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {

                nxt = (rs.getString("nsDate"));
                vehi = (rs.getString("vehi_reg"));

                if (nxt.equals(sys)) {

                    String qq = "update vehicle set availability='" + avl + "'   where    reg_no='" + vehi + "'   ";
                    pst = con.prepareStatement(qq);
                    pst.execute();

                }

            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }


    
    
    
    
    
    
    
    
    
    
    
    
}
