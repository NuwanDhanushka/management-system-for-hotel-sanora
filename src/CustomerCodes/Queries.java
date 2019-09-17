/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerCodes;

import static Controllers.MainHomeController.subHome;
import Controllers.SubHomeController;
import Main.DBconnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 *
 * @author THARINDU
 */
public class Queries {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private String fname;
    private String lname;
    private String nic;
    private String gender;
    private String dob;
    private String aLine1;
    private String aLine2;
    private String aPcode;
    private String tel;
    private String email;
    private String custID;
    private String custType;
    private String custPoints;
    
    public static String selectedCustomerID;
    public static String selectedAgentID;
    
    public void setCustID(TextField idField){
        
        con = DBconnect.dbconnect();
        
        String previous="CUST1000";
        int tempNo=0;
        int nextNo=0;
        String tempNum="";
        String next = "";
        try {           
            String qry = "Select custID from customer order by custID DESC";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            rs.next();
            previous=rs.getString("custID");
                       
        } catch (Exception e) {
            System.out.print(e);
        }
        for(int x=0;x<previous.length();x++){
            if(Character.isDigit(previous.charAt(x)))
                tempNum=tempNum+previous.charAt(x)+"";
        }
        tempNo = Integer.parseInt(tempNum);
        nextNo = tempNo+1;
        next = "CUST"+nextNo+"";
        idField.setText(next);
        
    }
    
    public void setAgentID(TextField idField){
        
        con = DBconnect.dbconnect();
        
        String previous="AGNT1000";
        int tempNo=0;
        int nextNo=0;
        String tempNum="";
        String next = "";
        try {           
            String qry = "Select agntID from agent order by agntID DESC";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            rs.next();
            previous=rs.getString("agntID");
                       
        } catch (Exception e) {
            System.out.print(e);
        }
        for(int x=0;x<previous.length();x++){
            if(Character.isDigit(previous.charAt(x)))
                tempNum=tempNum+previous.charAt(x)+"";
        }
        tempNo = Integer.parseInt(tempNum);
        nextNo = tempNo+1;
        next = "AGNT"+nextNo+"";
        idField.setText(next);
        
    }
    
    public String insertNewCustomerData(String custID,String fname,String lname,String nic,String gender,String dob,String aLine1,String aLine2,String aPcode,String tel,String email,String custType,String custPoints,String joinedDate ){
        String exception="";
        con = DBconnect.dbconnect();
        try {
                String qry = "INSERT INTO customer VALUES('"+custID+"','"+fname+"','"+lname+"','"+nic+"','"+gender+"','"+dob+"','"+aLine1+"','"+aLine2+"','"+aPcode+"','"+tel+"','"+email+"','"+custType+"','"+custPoints+"','"+joinedDate+"','0')";
                pst = con.prepareStatement(qry);
                pst.execute();
            } catch (Exception e) {
                System.out.println(e);
                exception = e.toString();    
            }
        return exception;
    }
    
    public String insertNewAgentData(String agentID,String fname,String lname,String nic,String gender,String company,String aLine1,String aLine2,String aPcode,String tel,String email,String joinedDate ){
        String exception="";
        con = DBconnect.dbconnect();
        try {
                String qry = "INSERT INTO agent VALUES('"+agentID+"','"+fname+"','"+lname+"','"+nic+"','"+gender+"','"+company+"','"+aLine1+"','"+aLine2+"','"+aPcode+"','"+email+"','"+tel+"','"+joinedDate+"')";
                pst = con.prepareStatement(qry);
                pst.execute();
            } catch (Exception e) {
                System.out.println(e);
                exception = e.toString();    
            }
        return exception;
    }
    
    public String updateCustomerData(String custID,String fname,String lname,String nic,String gender,String dob,String aLine1,String aLine2,String aPcode,String tel,String email){
        String exception="";
        con = DBconnect.dbconnect();
        try {
                String qry = "UPDATE customer set fName='"+fname+"',Lname='"+lname+"',NIC='"+nic+"',gender='"+gender+"',DOB='"+dob+"',aLine1='"+aLine1+"',aLine2='"+aLine2+"',postalCode='"+aPcode+"',telephone='"+tel+"',email='"+email+"' where custID ='"+custID+"'";
                pst = con.prepareStatement(qry);
                pst.execute();
            } catch (Exception e) {
                System.out.println(e);
                exception = e.toString();    
            }
        return exception;
    }
    
    public void deleteCustomer(String ID,boolean back){
        con = DBconnect.dbconnect();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Required");
        alert.setHeaderText("Do you really want to delete this Customer?");
        alert.setContentText("This will erase this Customer`s details!");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                try {
                String sql = "Delete FROM customer where custID = '"+ID+"'";
                pst = con.prepareStatement(sql);
                pst.execute();
                
                } catch (Exception e) {
                    System.out.println(e);
                }
                if(back){
                    SubHomeController subHomeC = subHome.getController();
                    subHomeC.subContent.getChildren().clear();
                    try {
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSearchCustomer.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
    }
    
    public String updateAgentData(String agentID,String fname,String lname,String nic,String gender,String cmp,String aLine1,String aLine2,String aPcode,String tel,String email){
        String exception="";
        con = DBconnect.dbconnect();
        try {
                String qry = "UPDATE agent set fName='"+fname+"',Lname='"+lname+"',NIC='"+nic+"',gender='"+gender+"',company='"+cmp+"',aLine1='"+aLine1+"',aLine2='"+aLine2+"',postalCode='"+aPcode+"',telephone='"+tel+"',email='"+email+"' where agntID ='"+agentID+"'";
                pst = con.prepareStatement(qry);
                pst.execute();
            } catch (Exception e) {
                System.out.println(e);
                exception = e.toString();    
            }
        return exception;
    }
    
    public void deleteAgent(String ID,boolean back){
        con = DBconnect.dbconnect();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Required");
        alert.setHeaderText("Do you really want to delete this Agent?");
        alert.setContentText("This will erase this Agent`s details!");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                try {
                String sql = "Delete FROM agent where agntID = '"+ID+"'";
                pst = con.prepareStatement(sql);
                pst.execute();
                
                } catch (Exception e) {
                    System.out.println(e);
                }
                if(back){
                    SubHomeController subHomeC = subHome.getController();
                    subHomeC.subContent.getChildren().clear();
                    try {
                        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSearchAgent.fxml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
    }
    
    //update Customer Type
    public void updateCustType(String cID,String type){
        con = DBconnect.dbconnect();
        try {
                String qry = "UPDATE customer set custType='"+type+"' where custID ='"+cID+"'";
                pst = con.prepareStatement(qry);
                pst.execute();
            } catch (Exception e) {
                System.out.println(e);   
            }
    }
    //update Min Required Points for priorty level
    public void updateMinPoints(String type,int value){
        con = DBconnect.dbconnect();
        try {
                String qry = "UPDATE cust_point_sys set minPoint='"+value+"' where custType ='"+type+"'";
                pst = con.prepareStatement(qry);
                pst.execute();
            } catch (Exception e) {
                System.out.println(e);   
            }
    }
    
}
