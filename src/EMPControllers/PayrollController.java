/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMPControllers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Main.DBconnect;
import EMPcodes.Dbpay;
import EMPcodes.Dbval;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SaKU
 */
public class PayrollController implements Initializable {
    
    private ObservableList<Dbpay> data;
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;
    
    @FXML
    private TextField PE_id;
    @FXML
    private Button searchEmp;
    @FXML
    private Button clearEmpbtn;
    @FXML
    private Label BasicSalary;
    @FXML
    private Label BonusSalary;//allowments
    @FXML
    private Label payWday;//OT payments
    @FXML
    private Label payWhrs;//OT hrs
    @FXML
    private Button paySave;
    @FXML
    private Button clearpay;
    @FXML
    private Label paytot;
    @FXML
    private Label payerror;
    @FXML
    private TextField EidS;
    @FXML
    private Label nameS;
    @FXML
    private Button deleteEmpBtn;
    @FXML
    private Button updateEmpBtn;
    @FXML
    private Button searchEmpAllBtn;
    @FXML
    private Button resalary;
    @FXML
    private TableColumn<Dbpay, String> EidPT;
    @FXML
    private TableColumn<Dbpay, String> EnamePT;
    @FXML
    private TableColumn<Dbpay, String> bsalPT;
    @FXML
    private TableColumn<Dbpay, String> bosalPT;
    @FXML
    private TableColumn<Dbpay, String> wdPT;
    @FXML
    private TableColumn<Dbpay, String> whPT;
    @FXML
    private TableColumn<Dbpay, String> totPT;
    @FXML
     private TableView<Dbpay> payTable;
    @FXML
    private Label totsal;
    @FXML
    private Label ETFlbl;
    @FXML
    private Label EPFlbl;
    @FXML 
    private Label noofemp;
    
    
    
    public  PayrollController(){
     con =DBconnect.dbconnect();
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Employee - Pay Roll");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml")));
        } catch (IOException ex) {
            
        }
        SubHomeController.backUrl = "/GUIs/EmpHome.fxml"; 
       
    }    

    @FXML
    private void searchEmployee(ActionEvent event) // Search Employee 
    {
        payTable.setItems(null);
        
        try {
            String eid=PE_id.getText();
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From payroll where E_id = '"+eid+"'");
            
            
            while (rs.next()) {
                 data.add(new Dbpay(rs.getString(5), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7),rs.getString(6)));
            }
            payerror.setText("Values are dispalyed under E ID = "+eid);
        } 
        
        catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        EidPT.setCellValueFactory(new PropertyValueFactory<>("E_id"));
        EnamePT.setCellValueFactory(new PropertyValueFactory<>("nameS"));
        bsalPT.setCellValueFactory(new PropertyValueFactory<>("BasicSalary"));
        bosalPT.setCellValueFactory(new PropertyValueFactory<>("BonusSalary"));
        wdPT.setCellValueFactory(new PropertyValueFactory<>("payWday"));
        whPT.setCellValueFactory(new PropertyValueFactory<>("payWhrs"));
        totPT.setCellValueFactory(new PropertyValueFactory<>("TotSalary"));
        
        payTable.setItems(data);

    }


    @FXML
    private void SavePay(ActionEvent event)             // Save Salary Details
    {
        
        boolean val = usernameValid();
             
        if(val==true){
            
            
            double Bsal=bsalcal();
            double hrspayrate = 350;                    // hourse pay rate  
            Integer noofemp = 5;                        //number of registerd Employee
            double Whr =othrs();                        //attendance OT hourse
            double Wday = hrspayrate*Whr;
            double NOofoverleaves =leaves();            //othrs form otalnleav
            double deductsal=NOofoverleaves*500;
            double profit=150000.00;                    //accountan
            double Bosal = ((profit/100)*10)/noofemp;
            double Sal = (Bsal+Wday+Bosal)-deductsal;
            double ETF = (Sal/100)*3;
            double EPF = (Sal/100)*8;
            double payTot = Sal-(ETF+EPF);
            String ids = EidS.getText();
            String Name = Empname();
            nameS.setText(Name);
           
           
            try{
           String qry =  "INSERT INTO payroll(BasicSalary,BonusSalary,payWday,payWhrs,Eids,nameS,TotSalary) VALUES ('" + Bsal + "','" + Bosal + "','" + Wday + "','"+ Whr +"','"+ ids +"','"+ Name +"','"+ payTot +"')";
           pst =con.prepareStatement(qry);
           pst.execute();
           savepaymeny(); 
           
           BasicSalary.setText("");
           BonusSalary.setText("");
           payWday.setText("");
           payWhrs.setText("");
           EidS.setText("");
           nameS.setText("");
           paytot.setText("");
           ETFlbl.setText("");
           EPFlbl.setText("");
           
           
            }
            catch(Exception e){
            System.out.println(e);
            }
        }
            else
             InvalidID();
         
    }    

    @FXML
    private void ClearPay(ActionEvent event) // Clear Details form labels
    {
        BasicSalary.setText("");
        BonusSalary.setText("");
        payWday.setText("");
        payWhrs.setText("");
        EidS.setText("");
        nameS.setText("");
        paytot.setText("");
        ETFlbl.setText("");
        EPFlbl.setText("");
    }

   @FXML
    private void deleteEmployee(ActionEvent event) // Delete Save Payment Details
    {
        Dbpay user=(Dbpay)payTable.getSelectionModel().getSelectedItem();
        String d="DELETE  FROM payroll WHERE EidS = ?";
        try{
        pst = con.prepareStatement(d);
        pst.setString(1,user.getPE_id());
        pst.execute();
        payerror.setText("Values are susscessfully Deleted!");
                       
        ObservableList<Dbpay>UserSelected,allUser;
        allUser=payTable.getItems();
        UserSelected=payTable.getSelectionModel().getSelectedItems();
        UserSelected.forEach(allUser::remove);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }

     @FXML
    private void updateEmployee(ActionEvent event) {
            String Bsal = BasicSalary.getText();
            String Bosal = BonusSalary.getText();
            String Wday = payWday.getText();
            String Whr = payWhrs.getText();
            String Tot = paytot.getText();
            String ids = EidS.getText();
            String Name = nameS.getText();
            
            
            String q3="UPDATE payroll SET BasicSalary='"+Bsal+"',BonusSalary='"+Bosal+"',payWday='"+Wday+"',payWhrs='"+Whr+"',Eids='"+ids+"',nameS='"+Name+"',TotSalary='"+Tot+"' WHERE Eids='"+ids+"'";
            try{
            pst = con.prepareStatement(q3);
            pst.execute();
            
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Are u Sure u want to update?");
            alert.showAndWait();
                 
            
          payerror.setText("Values are susscessfully Updated!");
            BasicSalary.setText("");
            BonusSalary.setText("");
            payWday.setText("");
            payWhrs.setText("");
            EidS.setText("");
            nameS.setText("");
            paytot.setText("");
            
            }catch(Exception e){
                System.out.println(e);
            }
            
    } 

    @FXML
    private void searchAllEmployees(ActionEvent event) // Retreave  data to table
    {
        
        try {

          Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From payroll");

           while (rs.next()) {
            data.add(new Dbpay(rs.getString(5), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        EidPT.setCellValueFactory(new PropertyValueFactory<>("EidS"));
        EnamePT.setCellValueFactory(new PropertyValueFactory<>("nameS"));
        
        bsalPT.setCellValueFactory(new PropertyValueFactory<>("BasicSalary"));
        bosalPT.setCellValueFactory(new PropertyValueFactory<>("BonusSalary"));
        
        wdPT.setCellValueFactory(new PropertyValueFactory<>("payWday"));
        
        whPT.setCellValueFactory(new PropertyValueFactory<>("payWhrs"));
        
        totPT.setCellValueFactory(new PropertyValueFactory<>("TotSalary"));
        
        payTable.setItems(null);
        payTable.setItems(data); 
    }

    @FXML
    private void resalary(ActionEvent event) // Reassing Salary
    {
        
        try{
            
        Dbpay user=(Dbpay)payTable.getSelectionModel().getSelectedItem();
        String qu="SELECT * FROM payroll WHERE EidS = ?";
        pst = con.prepareStatement(qu);
        pst.setString(1,user.getPE_id());
        rs=pst.executeQuery();
        while(rs.next()){
          
            EidS.setText(rs.getString("EidS"));
            nameS.setText(rs.getString("nameS"));
            BasicSalary.setText(rs.getString("BasicSalary"));
            BonusSalary.setText(rs.getString("BonusSalary"));
            payWday.setText(rs.getString("payWday"));
           payWhrs.setText(rs.getString("payWhrs"));
           paytot.setText(rs.getString("TotSalary"));
        }
          pst.close();
          rs.close();
     }catch(SQLException ex){
            System.err.println("Error"+ex);
     }
        
    }

    @FXML
    private void clearDetailssr(ActionEvent event) {
         PE_id.setText("");
    }
    
    void basicsalary(){
    
        
    
    }
    
    public double bsalcal(){
       
        double basicSal=0;
        String empId=EidS.getText();
         boolean val = usernameValid();
        if(val==true){
        try{
            
            String q="SELECT Desi from register where E_id='"+empId+"'";
            
           pst =con.prepareStatement(q);
           rs= pst.executeQuery();
           while(rs.next()){
              
              String desig=rs.getString("Desi");
              if(desig.equals("Manager")){
                 basicSal=25000.00;  
                
              }
              if(desig.equals("Worker")){
                  basicSal=15000.00;   
              }
              if(desig.equals("Sectatrory")){
                 basicSal=20000.00;   
              }
          }
           pst.close();
        rs.close();
        }catch(Exception e){
           System.out.println(e); 
        }
       return basicSal;
   
    }
        else
            InvalidID();
            return 0.00;
            
    }
    
    @FXML
    private void cslculateSal(ActionEvent event) {
        //salary details
            boolean val = usernameValid();
            if(val==true){
            double Bsal=bsalcal();
            if(Bsal>0){
            double hrspayrate = 350;  // hourse pay rate  
            Integer noofemp = 5; //number of registerd Employee
            double Whr =othrs();//attendance OT hourse
            double Wday = hrspayrate*Whr;
            double NOofoverleaves =leaves();//othrs form otalnleav
            double deductsal=NOofoverleaves*500;
            double profit=150000.00;//accountan
            double Bosal = ((profit/100)*10)/noofemp;
            double Sal = (Bsal+Wday+Bosal)-deductsal;
            double ETF = (Sal/100)*3;
            double EPF = (Sal/100)*8;
            double payTot = Sal-(ETF+EPF);
            String ids = EidS.getText();
            String Name = Empname();
            nameS.setText(Name);
            
            BasicSalary.setText(String.valueOf(Bsal));
            BonusSalary.setText(String.valueOf(Bosal));
            payWday.setText(String.valueOf(Wday));
            payWhrs.setText(String.valueOf(Whr));
            ETFlbl.setText(String.valueOf(ETF));
            EPFlbl.setText(String.valueOf(EPF));    
            paytot.setText(String.valueOf(payTot));
            
    }
             else   {
            BasicSalary.setText("");
            BonusSalary.setText("");
            payWday.setText("");
            payWhrs.setText("");
            EidS.setText("");
            nameS.setText("");
            paytot.setText("");
            
            }
             }
            else
             InvalidID(); 
    }
    
     public boolean usernameValid(){       
        boolean value = false;
        String user = EidS.getText();
        String Sql ="select E_id from register where E_id=?";      
        try{
            pst=con.prepareStatement(Sql);
            pst.setString(1,user);
            rs = pst.executeQuery();
            if(rs.next()){
                value=true;               
            }
            else
                value=false;
        }
        catch(Exception c){
            System.out.println(c);
            return value;
        }        
        return value;       
    }
     
     
     
      public double leaves(){
                    double noOfleaves=0;
                    String noofleaves="";
        String empId=EidS.getText(); 
        try{ 
            String q="SELECT leaves from otandleav where E_id='"+empId+"'";               
           pst =con.prepareStatement(q);
           rs= pst.executeQuery();
           while(rs.next()){
           noofleaves=rs.getString("leaves");
           }
         double leaves=Double.parseDouble(noofleaves);
           if(leaves>2){
           noOfleaves=leaves-2;        
           }
           else{
               noOfleaves=0;
           }  
           pst.close();
        rs.close();
        }catch(Exception e){
           System.out.println(e); 
        }
       
   return noOfleaves;    
    
      }
 public double othrs(){
        double othrs=0;
        double othr=0;
        
        String empId=EidS.getText(); 
       
            String q="SELECT othrs from otandleav where E_id='"+empId+"'";  
            try{
           pst =con.prepareStatement(q);
           rs= pst.executeQuery();
           while(rs.next()){
           String ot=rs.getString("othrs");
           
            othr=Double.parseDouble(ot);
           }
           if(othr<20){
           othrs=othr;        
           }
           else{
               othrs=20;
           }  
           pst.close();
        rs.close();
        }catch(Exception e){
           System.out.println(e); 
        }
       
   return othrs;    

 }

    public  String Empname() {
        String nameemp = "";
        String empId=EidS.getText(); 
         
            String q5="SELECT E_name from register where E_id='"+empId+"'"; 
               try{ 
           pst =con.prepareStatement(q5);
           rs= pst.executeQuery();
           while(rs.next()){
           nameemp = rs.getString("E_name");
         System.out.println(nameemp);
           } 
         }catch(Exception e){
           System.out.println(e); 
        }
      System.out.println(nameemp);  
      return nameemp;   
        
        
    }
   
    public void InvalidID() { //
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Employee");
    alert.setHeaderText("Employee ID");
    alert.setContentText("Invalid Employee id");
    alert.showAndWait();  
    
                            }
     public void savepaymeny() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Pay Roll");
    alert.setHeaderText("Monthly Pay");
    alert.setContentText("Values are susscessfully Inserted!");
    alert.showAndWait();  
     }
     
    
     
}
