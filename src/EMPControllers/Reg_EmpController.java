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
import EMPcodes.Validations;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author SaKU
 */
public class Reg_EmpController implements Initializable {
    
    private ObservableList<Dbval> data;
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;
    
    @FXML
    private TableColumn<Dbval, Integer> E_idT;
    @FXML
    private TableColumn<Dbval, String> E_depT;
    @FXML
    private TableColumn<Dbval, String> E_desT;
    @FXML
    private TableColumn<Dbval, String> E_nameT;
    @FXML
    private TableColumn<Dbval, String> E_nicT;
    @FXML
    private TableColumn<Dbval, String> E_genderT;
    @FXML
    private TableColumn<Dbval, Integer> E_noT;
    @FXML
    private Label regerror;
    @FXML
    private TableView<Dbval> EmpTable;
    
    ObservableList<String> typeD_name = FXCollections.observableArrayList("Account","Vehicle Rent","Operations","Restuarent","HR","IT");
    ObservableList<String> typeDesi = FXCollections.observableArrayList("Manager","Sectatrory","Woker");
    ObservableList<String> typeGender = FXCollections.observableArrayList("Female","Male");
    @FXML
    private Button clearEmpbtn;
    @FXML
    private Button redet;
    @FXML
    private Button demobtn;
    
    public Reg_EmpController(){
      
        con =DBconnect.dbconnect();
         
        
    }
    
    @FXML
    private TextField empIdText;
    @FXML
    private Button searchEmpBtn;
    @FXML
    private Button deleteEmpBtn;
    @FXML
    private Button updateEmpBtn;
    @FXML
    private Button addEmpBtn;
    @FXML
    private Button searchEmpAllBtn;
    @FXML
    private Label E_id;
    @FXML
    private TextField M_no;
    @FXML
    private TextField name;
    @FXML
    private TextField NIC;
    @FXML
    private ComboBox<String> D_name;
    @FXML
    private ComboBox<String> Desi;
    @FXML
    private ComboBox<String> Gender;
    @FXML
    private Button clearbtn;
    
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCustID(E_id);
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Employee - Add Employee");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/EMPGUIs/EmpSubMenuList.fxml")));
        } catch (IOException ex) {
            
        }
        SubHomeController.backUrl = "/GUIs/EmpHome.fxml";
        
        Desi.setItems(typeDesi);
       D_name.setItems(typeD_name);
       Gender.setItems(typeGender);
     
    }    


    @FXML
    private void deleteEmployee(ActionEvent event) {
        
        
         Dbval user=(Dbval)EmpTable.getSelectionModel().getSelectedItem();
        String d="DELETE  FROM register WHERE E_id = ?";
        try{
        pst = con.prepareStatement(d);
        pst.setString(1,user.getE_id());
        pst.execute();
        regerror.setText("Values are susscessfully Deleted!");
                       
        ObservableList<Dbval>UserSelected,allUser;
        allUser=EmpTable.getItems();
        UserSelected=EmpTable.getSelectionModel().getSelectedItems();
        UserSelected.forEach(allUser::remove);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void updateEmployee(ActionEvent event) {
        
           String ename = name.getText();
            String ID = E_id.getText();
            //String enic = NIC.getText();
            String edep = D_name.getValue();
            String edesi = Desi.getValue();
            String egen = Gender.getValue();
            String eEno=  M_no.getText();
            
            
            String q3="UPDATE register SET E_name='"+ename+"',M_no='"+eEno+"',D_name='"+edep+"',gender='"+egen+"',Desi='"+edesi+"' WHERE E_id='"+ID+"'";
            try{
            pst = con.prepareStatement(q3);
            pst.execute();
            
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Are u Sure u want to update?");
            alert.showAndWait();
                 
            
          regerror.setText("Values are susscessfully Updated!");
            E_id.setText("");
            name.setText("");
            NIC.setText("");
            D_name.setValue("");
            Desi.setValue("");
            Gender.setValue("");
            M_no.setText("");
            
            }catch(Exception e){
                System.out.println(e);
            }
            
            }
       
    @FXML
    private void insertEmployee(ActionEvent event) {
        
            String Ename = name.getText();
            String Eid = E_id.getText();
            String Nic = NIC.getText();
            String dep = D_name.getValue();
            String desi = Desi.getValue();
            String gen = Gender.getValue();
            String Eno=  M_no.getText();
            
            
        String excep="";
        String duplicateExcep = "Duplicate entry '"+Nic+"' for key 'NIC'";
        
        boolean validation = true;
        boolean nextNew;
        
        if (!Ename.matches("[a-zA-Z_]+\\.?")) {
            showAlertname();
            validation = false; 
        }
        
        if(Ename.isEmpty()||Eid.isEmpty()||Nic.isEmpty()||dep.isEmpty()||desi.isEmpty()||gen.isEmpty()||Eno.isEmpty()){
            Validations.fieldEmptyMsg();
            validation = false;    
        }
        if(!Nic.isEmpty() && !Validations.NICvalidate(Nic)){
            validation = false;
        }
        if(!Eno.isEmpty() && !Validations.TPvalidate(Eno)){
            validation = false;
        }
       
        

         if(Ename.isEmpty()||Eid.isEmpty()||Nic.isEmpty()||dep.isEmpty()||desi.isEmpty()||gen.isEmpty()||Eno.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to cancel this?");
            alert.setContentText("This will loss you entered values!");
              
          }else
               {
            
          try{
           String qry =  "INSERT INTO register(E_name,E_nic,E_id,M_no,gender,D_name,Desi) VALUES ('" + Ename + "','" + Nic + "','" + Eid + "','"+ Eno +"','"+ gen +"','"+ dep +"','"+ desi +"')";
           pst =con.prepareStatement(qry);
           pst.execute();
           showAlertreg();    
             
            
           setCustID(E_id);
           name.setText("");
           NIC.setText("");
           D_name.setValue("Select...");
           Desi.setValue("Select...");
           Gender.setValue("Select...");
           M_no.setText("");
                   }catch(Exception e)
                   {
                       System.out.println(e);
                   }
        } 
       
    }
    

    @FXML
    private void searchAllEmployees(ActionEvent event) {
         try {

          Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From register");

           while (rs.next()) {
            data.add(new Dbval(rs.getString(3), rs.getString(1), rs.getString(6), rs.getString(2), rs.getString(7), rs.getString(5),rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        E_idT.setCellValueFactory(new PropertyValueFactory<>("E_id"));
        E_nameT.setCellValueFactory(new PropertyValueFactory<>("E_name"));
        E_depT.setCellValueFactory(new PropertyValueFactory<>("E_dip"));
        E_nicT.setCellValueFactory(new PropertyValueFactory<>("E_nic"));
        E_desT.setCellValueFactory(new PropertyValueFactory<>("E_desi"));
        E_genderT.setCellValueFactory(new PropertyValueFactory<>("E_gender"));
        E_noT.setCellValueFactory(new PropertyValueFactory<>("E_no"));
        
        EmpTable.setItems(null);
        EmpTable.setItems(data); 
        
        
    }

    
    
    

    @FXML
    private void clearDetails(ActionEvent event) {
          
           name.setText("");
           NIC.setText("");
           D_name.setValue("Select...");
           Desi.setValue("Select...");
           Gender.setValue("Select...");
           M_no.setText("");
    }


   private void InitComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void redeatils(ActionEvent event) {
        try{
            
        Dbval user=(Dbval)EmpTable.getSelectionModel().getSelectedItem();
        String qu="SELECT * FROM register WHERE E_id = ?";
        pst = con.prepareStatement(qu);
        pst.setString(1,user.getE_id());
        rs=pst.executeQuery();
        while(rs.next()){
           // regerror.setText("You have selected details regarding ItemCode ="+rs.getString("ItemCode"));
            E_id.setText(rs.getString("E_id"));
            name.setText(rs.getString("E_name"));
            NIC.setText(rs.getString("E_nic"));
            D_name.setValue(rs.getString("D_name"));
            Desi.setValue(rs.getString("Desi"));
           Gender.setValue(rs.getString("gender"));
           M_no.setText(rs.getString("M_no"));
        }
          pst.close();
          rs.close();
     }catch(SQLException ex){
            System.err.println("Error"+ex);
     }
    }

    @FXML
    private void searchEmp(ActionEvent event) throws SQLException {
        
       EmpTable.setItems(null);
        
        try {
            String sid=empIdText.getText();
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From register where E_id = '"+sid+"'");
            
            
            while (rs.next()) {
                data.add(new Dbval(rs.getString(3), rs.getString(1), rs.getString(6), rs.getString(2), rs.getString(7), rs.getString(5),rs.getString(4)));
            }
            regerror.setText("Values are dispalyed under E ID = "+sid);
        } 
        
        catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        E_idT.setCellValueFactory(new PropertyValueFactory<>("E_id"));
        E_nameT.setCellValueFactory(new PropertyValueFactory<>("E_name"));
        E_depT.setCellValueFactory(new PropertyValueFactory<>("E_dip"));
        E_nicT.setCellValueFactory(new PropertyValueFactory<>("E_nic"));
        E_desT.setCellValueFactory(new PropertyValueFactory<>("E_desi"));
        E_genderT.setCellValueFactory(new PropertyValueFactory<>("E_gender"));
        E_noT.setCellValueFactory(new PropertyValueFactory<>("E_no"));
        
        EmpTable.setItems(data);

    }

    @FXML
    private void clearDetailsEmp(ActionEvent event) {
         empIdText.setText("");
         regerror.setText("");
    }

    @FXML
    private void onclicktable(MouseEvent event) {
        
        
        
        
    }
    
    public void showAlertreg() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Register");
    alert.setHeaderText("New Employee Registerd");
    alert.setContentText("You have sucess Registerd New Employee");
    alert.showAndWait();
}
    
    public void showAlertname() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Register");
    alert.setHeaderText("Check your Name Again");
    alert.setContentText("Name Field Only Charactors!");
    alert.showAndWait();
    }
    
    public void showAlertnoitem(String sid) { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Search");
    alert.setHeaderText("Check your ID Again");
    alert.setContentText("There is no '"+sid+"'!");
    alert.showAndWait();
    }
    
    public void setCustID(Label idField){
        
        con = DBconnect.dbconnect();
        
        String previous="1000";
        int tempNo=0;
        int nextNo=0;
        String tempNum="";
        String next = "";
        try {           
            String qry = "Select E_id from register order by E_id DESC";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            rs.next();
            previous=rs.getString("E_id");
                       
        } catch (Exception e) {
            System.out.print(e);
        }
        for(int x=0;x<previous.length();x++){
            if(Character.isDigit(previous.charAt(x)))
                tempNum=tempNum+previous.charAt(x)+"";
        }
        tempNo = Integer.parseInt(tempNum);
        nextNo = tempNo+1;
        next = ""+nextNo+"";
        idField.setText(next);
        
    }

    @FXML
    private void demobtn(ActionEvent event) {
        
           name.setText("Sadun");
           NIC.setText("941234567V");
           D_name.setValue("IT");
           Desi.setValue("Manager");
           Gender.setValue("Male");
           M_no.setText("0712345678");
        
        
    }
    
    
}
