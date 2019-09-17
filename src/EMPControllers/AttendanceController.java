/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMPControllers;


  
import Main.DBconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import CustomerControllers.CustSubMenuListController;
import EMPcodes.Attendance;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SaKU
 */
public class AttendanceController implements Initializable {

    private ObservableList<Attendance> data;
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;
    
    
    @FXML
    private TextField AE_id;
    private TableColumn<Attendance, String> eid;
    @FXML
    private TableColumn<Attendance, String> in;
    @FXML
    private TableColumn<Attendance, String> out;
    @FXML
    private TableColumn<Attendance, String> workingdays;
    @FXML
    private TableColumn<Attendance, String> Attendid;
    @FXML
    private Button inbtn;
    @FXML
    private Button outbtn;
    @FXML
    private Button leavebtn;
    @FXML
    private Label MLbDate;
    @FXML
    private Label MLbTime;
    @FXML
    private TableView<Attendance> attTable;
    @FXML
    private Button viewatt;
    @FXML
    private TextArea leavedetails;
    @FXML
    private ComboBox<String> leavebox;
    
    ObservableList<String> Typeleavebox = FXCollections.observableArrayList("Half-Day","Full-Day");
    @FXML
    private TableColumn<Attendance, String> eid1;
    @FXML
    private TableColumn<Attendance, String> lvtype;
    @FXML
    private TableColumn<Attendance, String> lvdetails;
    
    public AttendanceController() {
    con =DBconnect.dbconnect();
 
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
      con =DBconnect.dbconnect();
      showDate();
      tickTock();
      leavebox.setItems(Typeleavebox);
     
        }                        
    
        

    @FXML
    private void in(ActionEvent event) {
               
        boolean val = usernameValid();
          System.out.println(val);     
        if(val==true){
            
            try {
                
                String Date = MLbDate.getText();
                String username = AE_id.getText();
                String Time = MLbTime.getText();
                
                
                String sql = "SELECT E_id,Date FROM attendance_mark WHERE E_id=? AND Date=? ";
                pst = con.prepareStatement(sql);
                pst.setString(1,username);
                pst.setString(2,Date);
                rs = pst.executeQuery();
                if(rs.next()){
                  alradyin(); 
                    
                }
                else{
                try {
                    String sql2="INSERT INTO attendance_mark(E_id,Date,IN_time)values('"+AE_id.getText()+"','"+MLbDate.getText()+"','"+MLbTime.getText()+"')";
                    pst=con.prepareStatement(sql2);
                    pst.execute();
                    showAlertin();
                } catch (SQLException ex) {
                    System.out.println(ex);
                                          }
                
                }
            } catch (SQLException ex) {
                 System.out.println(ex);
            }
     
        }
        else
           InvalidID();
    }
    

    @FXML
    private void out(ActionEvent event) {
     boolean val = usernameValid();
        System.out.println(val);
        if(val==true){
            
            try {
                
                String Date = MLbDate.getText();
                String username = AE_id.getText();
                
                String sql = "SELECT E_id,Date,Out_time FROM attendance_mark WHERE E_id=? AND Date=? ";
                pst = con.prepareStatement(sql);
                pst.setString(1,username);
                pst.setString(2,Date);
                rs = pst.executeQuery();
                if(rs.next()){
                    
                    String Out_time = rs.getString("Out_time");
                    System.out.println(Out_time);
                    if(Out_time==null){
                        String sql2="UPDATE attendance_mark SET Out_time='"+MLbTime.getText()+"' WHERE E_id='"+username+"'AND Date='"+Date+"'";
                        pst=con.prepareStatement(sql2);
                        pst.executeUpdate();
                        showAlertout();           
                    }
                    else{
                        alradyout();
                    }
    
                }
                else{               
                   haventmarkedin();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                                      }
      
        }
        else{
            InvalidID();
        }
    }   
  
    
    public boolean usernameValid(){
        
        boolean value = false;
        String user = AE_id.getText();
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
    
    public void showAlertin() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Attendance IN");
    alert.setContentText("Success ! Your IN time is '"+MLbTime.getText()+"'");
    alert.showAndWait();
}
    public void showAlertout() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Attendance OUT");
    alert.setContentText("Success ! Your OUT time is '"+MLbTime.getText()+"'");
    alert.showAndWait();
}
    public void InvalidID() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Employee");
    alert.setHeaderText("Employee ID");
    alert.setContentText("Invalid Employee id");
    alert.showAndWait();  
  }
    public void haventmarkedin() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Attendance");
    alert.setContentText("You Haven't marked in");
    alert.showAndWait();  
  }
    public void alradyin() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Attendance");
    alert.setContentText("You Already marked in");
    alert.showAndWait();  
  }
    public void alradyout() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Attendance");
    alert.setContentText("You Already marked Out");
    alert.showAndWait();  
  }
    
     public void leave() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Day Leaves");
    alert.setContentText("You Already marked in or Marked in leaves");
    alert.showAndWait();  
  }
     
      public void leavesucess() { 
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance");
    alert.setHeaderText("Day Leaves");
    alert.setContentText("You have Sucess placed Leave");
    alert.showAndWait();  
  }

    @FXML
    private void leavedtls(ActionEvent event) {
          boolean val = usernameValid();
            
        if(val==true){
            
            try {
                
                String Date = MLbDate.getText();
                String username = AE_id.getText();
                String Time = MLbTime.getText();
                String leavestype = leavebox.getValue();
                String leavedetail = leavedetails.getText();
                
                
                String sql9 = "SELECT E_id,Date,ltype,ldetails FROM attendance_mark WHERE E_id=? AND Date=? AND ltype=? AND ldetails=? ";
                pst = con.prepareStatement(sql9);
                pst.setString(1,username);
                pst.setString(2,Date);
                pst.setString(3,leavestype);
                pst.setString(4,leavedetail);
                rs = pst.executeQuery();
                if(rs.next()){
                leave();
                    
                AE_id.setText("");
                leavebox.setValue("");
                leavedetails.setText("");
                }
                else{
                try {
                   String sqll = "INSERT INTO attendance_mark (E_id,Date,ltype,ldetails)values('"+username+"','"+Date+"','"+leavestype+"','"+leavedetail+"')";
                     pst = con.prepareStatement(sqll);
                     pst.execute();
                     leavesucess();   
                } 
                catch (SQLException ex){
                    System.out.println(ex);
                }
                
                }
            } catch (SQLException ex) {
                 System.out.println(ex);
            }
     
        }
        else
           InvalidID();
    }

    //extr
    public void showDate(){
        
        Date d =new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd ");
        MLbDate.setText(s.format(d));
       
    
    }
     public void tickTock() {
        Date d =new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm");
        MLbTime.setText(s.format(d));
        }

    @FXML
    private void viweAtt(ActionEvent event) {
        try {
                
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From attendance_mark");

           while (rs.next()) {
            data.add(new Attendance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        Attendid.setCellValueFactory(new PropertyValueFactory<>("Aid"));
        eid1.setCellValueFactory(new PropertyValueFactory<>("EidA"));
        workingdays.setCellValueFactory(new PropertyValueFactory<>("dateA"));
        in.setCellValueFactory(new PropertyValueFactory<>("inA"));
        out.setCellValueFactory(new PropertyValueFactory<>("outA"));
        lvtype.setCellValueFactory(new PropertyValueFactory<>("levtype"));
        lvdetails.setCellValueFactory(new PropertyValueFactory<>("levdetails"));
        
        attTable.setItems(null);
        attTable.setItems(data); 
        }
       
        
    }
    
   
    
    
