package billing_system;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class UpdateStockController implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs=null;

    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField text6;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> itemcode;
    @FXML
    private TableColumn<User, String> itemname;
    @FXML
    private TableColumn<User, String> price;
    @FXML
    private TableColumn<User, String> seller;
    @FXML
    private TableColumn<User, String> total;
  
    private ObservableList<User> data;
    private DBconnect conn;
    @FXML
    private Button button;
    @FXML
    private Label label2;
    @FXML
    private Button button1;
    @FXML
    private TextField text11;
    @FXML
    private Button button11;
    @FXML
    private ComboBox<String> comb;
    ObservableList<String> com1 = FXCollections.observableArrayList("ItemCode","itemName");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comb.setItems(com1);
        
    }
    
    
    @FXML
    public void reAssignValues(ActionEvent event){
       try{
            
        User user=(User)table.getSelectionModel().getSelectedItem();
        String qu="SELECT * FROM stock WHERE ItemCode = ?";
        pst = con.prepareStatement(qu);
        pst.setString(1,user.getItemcode());
        rs=pst.executeQuery();
        while(rs.next()){
            label2.setText("You have selected details regarding ItemCode ="+rs.getString("ItemCode"));
            text1.setText(rs.getString("ItemCode"));
            text2.setText(rs.getString("ItemName"));
            text3.setText(rs.getString("Price"));
            text4.setText(rs.getString("Seller"));
            text6.setText(rs.getString("Quantity"));
           
        }
          pst.close();
          rs.close();
     }catch(SQLException ex){
            System.err.println("Error"+ex);
     } 
    }

    @FXML
    public void update(ActionEvent event){
            if(validateFields()==true && validateItemCode()==true && validateItemNameField()==true && validateSupplierFeild()==true && validateQuantityFeild()==true && validatePriceFeild()==true)
        {
        
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Are u Sure u want to update?");
            alert.showAndWait();
                      
            String itemcode = text1.getText();
            String itemName = text2.getText();
            double price    = Double.parseDouble(text3.getText());
            String seller   = text4.getText();
            int quantity =    Integer.parseInt(text6.getText());
            
            //if(alert==){
            
            String q3="UPDATE stock SET ItemCode='"+itemcode+"',ItemName='"+itemName+"',Price='"+price+"',Seller='"+seller+"',Quantity='"+quantity+"' WHERE ItemCode='"+itemcode+"'";
            try{
            pst = con.prepareStatement(q3);
            pst.execute();
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text6.setText("");
            
            label2.setText("Values are susscessfully Updated!");
            
            }catch(Exception e){
                System.out.println(e);
                
            }
            }}
   // }
    
    @FXML
    public void loadData(ActionEvent event) {
        try {
            Connection con = conn.connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From stock");

            while (rs.next()) {
                data.add(new User(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        itemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        itemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        total.setCellValueFactory(new PropertyValueFactory<>("quantity"));
      
        
        table.setItems(null);
        table.setItems(data);
        
        
    }

    public UpdateStockController() {
       
        con = DBconnect.connect();

    }
                  
      
    @FXML
    public void insertValues(ActionEvent event) {
        
        validations v=new validations();
        
        String itemCode = text1.getText();
        String itemName = text2.getText();
         double price = Double.parseDouble(text3.getText());
        String seller = text4.getText();
        int quantity = Integer.parseInt(text6.getText());
        
        
       if(validateFields()==true && validateItemCode()==true && validateItemNameField()==true && validateSupplierFeild()==true && validateQuantityFeild()==true && validatePriceFeild()==true){
        try {
            String q = "INSERT INTO stock(ItemCode,ItemName,Price,Seller,Quantity) values ('" + itemCode + "','" + itemName + "','" + price+ "','" + seller + "','" + quantity + "')";
            pst = con.prepareStatement(q);
            pst.execute();

            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text6.setText("");
            
            label2.setText("Values are susscessfully Inserted to the Stock!");

        } catch (Exception e) {
            System.out.println(e);

        }
       }
    }
    

    

   
    @FXML
    private void deleteData(ActionEvent event) {
        
        User user=(User)table.getSelectionModel().getSelectedItem();
        String d="DELETE  FROM stock WHERE ItemCode = ?";
        try{
        pst = con.prepareStatement(d);
        pst.setString(1,user.getItemcode());
        pst.execute();
        label2.setText("Values are susscessfully Deleted!");
                       
        ObservableList<User>UserSelected,allUser;
        allUser=table.getItems();
        UserSelected=table.getSelectionModel().getSelectedItems();
        UserSelected.forEach(allUser::remove);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void search(ActionEvent event) {
        table.setItems(null);
        
        try {
            String itemcode=text11.getText();
            Connection con = conn.connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From stock where ItemCode = '"+itemcode+"'");
            
            
            while (rs.next()) {
                data.add(new User(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5)));
            }
            label2.setText("Values are dispalyed under ItemCode = "+itemcode);
        } 
        
        catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        itemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        itemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        total.setCellValueFactory(new PropertyValueFactory<>("quantity"));
      
        table.setItems(data);
        
    }

    @FXML
    private void Clear(ActionEvent event) {
        
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text6.setText("");
    }
    
    
    //Validations
    
  private boolean validateItemCode(){
       Pattern p=Pattern.compile("[P][0-9][0-9][0-9][0-9]");
        Matcher m=p.matcher(text1.getText());
        if(m.find()&& m.group().equals(text1.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Error in Item Code feild!!");
           alert.setHeaderText(null);
           alert.setContentText("Please Enter a Valid ItemCode!! \n First digit should be P in Upper Case \n and other 4 digits should be Numeric!");
           alert.showAndWait();
           return false;
        
    }
        
    }
    
    
  private boolean validateFields(){
        if(text1.getText().isEmpty() |text2.getText().isEmpty() | text3.getText().isEmpty() | text4.getText().isEmpty() | text6.getText().isEmpty()){
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Empty  Fileds!!");
           alert.setHeaderText(null);
           alert.setContentText("Please fill all the fields!!");
           alert.showAndWait();
           return false;
        }
        
       else
            return true;
    }
    
    private boolean validateItemNameField(){
        
        Pattern p=Pattern.compile("[a-zA-Z]+");
        Matcher m=p.matcher(text2.getText());
        if(m.find()&& m.group().equals(text2.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(AlertType.WARNING);
            alert.setTitle("Error in Item Name feild!!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter valid name for Item Name! \n It should be only conatin charactors! ");
            alert.showAndWait();
            return false;
        
    }
    }
    
    private boolean validateSupplierFeild(){
        
        Pattern p=Pattern.compile("[a-zA-Z]+");
        Matcher m=p.matcher(text4.getText());
        if(m.find()&& m.group().equals(text4.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(AlertType.WARNING);
            alert.setTitle("Error in Supplier feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid Supplier ");
            alert.showAndWait();
            return false;
        
    }
    }
    
    private boolean validateQuantityFeild(){
        Pattern p=Pattern.compile("[0-9]+");
        Matcher m=p.matcher(text6.getText());
        if(m.find()&& m.group().equals(text6.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Quantity feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid quantity! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;
        
    }
    }
    
    private boolean validatePriceFeild(){
        Pattern p=Pattern.compile("[0-9]+[.][0-9]+");
        Matcher m=p.matcher(text3.getText());
        if(m.find()&& m.group().equals(text3.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Price feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid price! \n it should be only contain numeric values with decimal points!! ");
            alert.showAndWait();
            return false;
        
    }
    }
} 


