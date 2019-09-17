package billing_system;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class CalBillPubController implements Initializable {

    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    private TextField text2;
    @FXML
    private Label label6;
    @FXML
    private Label label61;
    @FXML
    private Label label;
    private TextField text3;
    @FXML
    private Label label51;
   
    @FXML
    private TextField text1;
    
    private ObservableList<takeAwayStockDetails> data;

    private DBconnect conn;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    
    @FXML
    private TableView<takeAwayStockDetails> table;
    @FXML
    private TableColumn<takeAwayStockDetails, String> itemcode;
    @FXML
    private TableColumn<takeAwayStockDetails, String> itemname;
    @FXML
    private TableColumn<takeAwayStockDetails, String> price;
    @FXML
    private TableColumn<takeAwayStockDetails, String> seller;
    @FXML
    private TableColumn<takeAwayStockDetails, String> total;
    
    
    @FXML
    private Label label42;
    @FXML
    private TextField text11;
    @FXML
    private ComboBox comb;
    private ObservableList<String> keyList = FXCollections.observableArrayList("ItemCode","ItemName");
    
    
    @FXML
    private TableView<takeAwayStockDetails> table1;
    private TableColumn<takeAwayStockDetails, String> itemname1;
    @FXML
    private TableColumn<takeAwayStockDetails, String> price1;
    @FXML
    private TableColumn<takeAwayStockDetails, String> quantity1;
    
     // private TableColumn<?, ?> itemcode1;
    @FXML
    private TableColumn<takeAwayStockDetails, String> ItemCodes;
    
    @FXML
    private TextField qun;
    @FXML
    private Label lb1;
    @FXML
    private Button cal;
    @FXML
    private Label lb;
  
  //  private TableColumn<?, ?> itemcode1;
    @FXML
    private Label ql;
    
     public CalBillPubController(){
       
    }
     
     //Initializing combo box values
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comb.setItems(keyList);
        comb.setValue("ItemCode");
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Billing System - Pub");
        subHomeC.backBtn.setVisible(true);//set back button
        SubHomeController.backUrl = "/GUIs/BillingHome.fxml";
         }    
    
    //load take away stock Details to the tabel by clicking the buttion
    
    @FXML
    public void loadTakeAwayStockTable(ActionEvent Event){
        char type = 'a';
        String keyTypeVal = comb.getValue().toString();
        if(keyTypeVal.equals("ItemCode")){
            type = 'i';
        }
        if(keyTypeVal.equals("ItemName")){
            type = 'n';
        }
        String keyString = text11.getText().toUpperCase();
        loadTakeAwayStockDataintoTable(keyString, type);
    }
    //auto suggections
    
    @FXML
    public void loadTakeAwayStockTableSuggestions(KeyEvent event){
        char type = 'a';
        String keyTypeVal = comb.getValue().toString();
        if(keyTypeVal.equals("ItemCode")){
            type = 'i';
        }
       
        if(keyTypeVal.equals("ItemName")){
            type = 'n';
        }
        String keySuggestion = text11.getText().toUpperCase();
        loadTakeAwayStockDataintoTable(keySuggestion+"%", type);
       
    }
         //load take away stock Details to the tabel 
        
        public void loadTakeAwayStockDataintoTable(String key,char keyField){
    
        Connection con = conn.connect();
       
        data = FXCollections.observableArrayList();
        
        String searchBy="";
      
        if(keyField=='n'){
            searchBy="ItemName";
        }
        if(keyField=='i'){
            searchBy="ItemCode";
        }
        
        try {           
            String qry = "Select * from stock where " + searchBy + " LIKE '" + key + "'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while(rs.next()){
                data.add(new takeAwayStockDetails (rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5)));
            }
                       
        } catch (Exception e) {
            System.out.print(e);
        }
        
        itemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        itemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        total.setCellValueFactory(new PropertyValueFactory<>("quantity"));
      
        
        table.setItems(null);
        table.setItems(data);
        
    }
           
    double tot=0;
    //add to the bill. this will not calculate the added items since user presed calu&print button
    @FXML
    public void addToBill(ActionEvent event){
         //checks wther user hasnt select a item to add to the bill
        if(!table.getSelectionModel().isEmpty()){
        //adds selected items to a temporary table called items
           try{
               
            Connection con = conn.connect();
            data = FXCollections.observableArrayList();   
            takeAwayStockDetails user=(takeAwayStockDetails)table.getSelectionModel().getSelectedItem();  
            String qu="SELECT * FROM stock WHERE ItemCode = ?";
            pst = con.prepareStatement(qu);
            pst.setString(1,user.getItemcode());
            rs=pst.executeQuery();
                 
                while (rs.next()) {
                    
                    String aq=rs.getString("Quantity");
                    int aqu=Integer.parseInt(aq);
                    String qua=qun.getText();
                    int quantity=Integer.parseInt(qua);
                    int q2=aqu-quantity;
                   
                    //checks whther the given quantity is greter tah or equals to 1
                    if(quantity<=0){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Invalid quantity!");
                     alert.setHeaderText("Please enter a valid Quantity!");
                     alert.setContentText("Ooops!! There was an error!");

                     alert.showAndWait();
                     
                     qun.setText("");
                    }
                    //checks wther the given quatity is available in the stock
                    else if(aqu<quantity ){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Invalid quantity!");
                     alert.setHeaderText("Out of stock!");
                     alert.setContentText("Ooops!! There was an error!");

                     alert.showAndWait();
                     qun.setText("");
                      }
                    
                    else{
                        //calculates the total price
                    String p=rs.getString("Price");
                    double price=Double.parseDouble(p);
                    String cusName=text1.getText();
                    String ItemCodes=rs.getString("ItemCode");
                    double aprice=(quantity*price); 
                    tot=tot+aprice;
                    label.setText(String.valueOf(tot));
                   
                 //inserting each items to a tempory table called addedItems
                   String q = "INSERT INTO addedItems(cusName,ItemCodes,price,quantity,totPrice) values ('"+cusName+"','"+ItemCodes+"','"+price+"','"+quantity+"','"+aprice+"')";
                   pst = con.prepareStatement(q);
                   pst.execute(); 
                    }
                
            }try {
         
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From addedItems");

            while (rs.next()) {
                int q=rs.getInt("quantity");
                double tot=rs.getDouble("totPrice");
                double price=rs.getDouble("price");
                String ItemCodes=rs.getString("ItemCodes");
                
               data.add(new  takeAwayStockDetails(ItemCodes,price,tot,q));
              // t.setText(t.getText()+"\n"+rs.getString("ItemCodes"));
             
             
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
            //each added items will preview in the table
        ItemCodes.setCellValueFactory(new PropertyValueFactory<>("icode"));
        //total1.setCellValueFactory(new PropertyValueFactory<>("tot"));
        price1.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
       
      
        
        table1.setItems(null);
        table1.setItems(data);
       
          pst.close();
          rs.close();
     }catch(SQLException ex){
            System.err.println("Error"+ex);
     }  
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Please Select a record to Add to the Bill!");
            alert.showAndWait();
        } 
       
    }
    
    //when a row is selected from the takeAway stock tabel, quantity field will set to 1 as the default value
    @FXML
    private void setQuantity(MouseEvent event) {
        qun.setText("1");
    }

    @FXML
    private void Increase(ActionEvent event) {
        String q=qun.getText();
        int q1=Integer.parseInt(q);
        q1=q1+1;
       qun.setText(String.valueOf(q1));
   
    }
    
    //this will allow to Decrease the quantity value to 1 by pressing the "-" button 
    @FXML
    private void Decrease(ActionEvent event) {
       
        String q=qun.getText();
        
        int q1=Integer.parseInt(q);
         while(q1>1){
        q1=q1-1;
         qun.setText(String.valueOf(q1));
         }
    }

    //when the add button is presse,selected row in the stock table will be unselected
    @FXML
    private void unselectRow(MouseEvent event) {
    table.getSelectionModel().clearSelection();
    }
    
    //this method will calculate the added items
    @FXML
    private void calandPrint(ActionEvent event) throws Exception {
        
            String cusname =text1.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Required!");
            alert.setHeaderText("Do you really want to calculate the bill?");
            alert.setContentText("This will calculate all added items and you cant undo it");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //user agrees to calculate
             try {
            
            Connection con = conn.connect();
           ResultSet rs = con.createStatement().executeQuery("SELECT * From addedItems");
            
            while (rs.next()) {
                
                //reduce quantity from the stock
               int q=rs.getInt("quantity");
                String ItemCod=rs.getString("ItemCodes");
               try {
            
          
            ResultSet rslt = con.createStatement().executeQuery("SELECT Quantity from stock where Itemcode='"+ItemCod+"'");

            while (rslt.next()) {
             
               int q1=rs.getInt("Quantity");
              //  int q1=Integer.parseInt(q);
                int newQuantity=q1-q;
                
                
                String qua1="update stock set Quantity='"+newQuantity+"' where ItemCode='"+ItemCod+"'";
                pst = con.prepareStatement(qua1);
               pst.execute();
               
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
               //clear all the records of items in the temporary table
               ql.setText(ql.getText()+","+rs.getString("ItemCodes"));
               String q1="DELETE FROM addedItems WHERE ItemCodes = '"+ItemCod+"'";
               pst = con.prepareStatement(q1);
               pst.execute();
               
                    
            }try {
         // this will clear the billing table
            data = FXCollections.observableArrayList();
            ResultSet rsc = con.createStatement().executeQuery("SELECT * From addedItems");

            while (rs.next()) {
                int q=rs.getInt("quantity");
                double tot=rs.getDouble("totPrice");
                double price=rs.getDouble("price");
                String ItemCodes=rs.getString("ItemCodes");
                
               data.add(new  takeAwayStockDetails(ItemCodes,price,tot,q));
             
            }
            
           
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        ItemCodes.setCellValueFactory(new PropertyValueFactory<>("ItemCodes"));
       // total1.setCellValueFactory(new PropertyValueFactory<>("tot"));
        price1.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
       
      
        
        table1.setItems(null);
        table1.setItems(data);
       
        } 
        catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
             String icodes=ql.getText();
             String cusName=text1.getText();
    
             //inserting data into the customer table
             try { 
              //insert customer details with itemCodes to a table called solditems 
            Connection con = conn.connect();
            String q = "INSERT INTO pubsoldItems (cusName,Items,totPrice) values ('" + cusName + "','" +icodes+ "','" + tot + "')";
            pst = con.prepareStatement(q);
            pst.execute();
            
                qun.setText("");
               text11.setText("");
               text1.setText("");
               ql.setText("");
               label.setText("");


        } catch (Exception e) {
            System.out.println(e);
        }
             
            } else {
                // ... user chose CANCEL S/He can add  new items to  the the bill
             }
         }



    @FXML
    private void deleteItem(ActionEvent event) {
        if(!table1.getSelectionModel().isEmpty()){
        try{
        Connection con = conn.connect();
        data = FXCollections.observableArrayList();   
        takeAwayStockDetails tasd=(takeAwayStockDetails)table1.getSelectionModel().getSelectedItem();
        String d="DELETE FROM addedItems WHERE ItemCodes = ?";
        
        pst = con.prepareStatement(d);
        pst.setString(1,tasd.getItemcode());
        pst.execute();
          
        ObservableList<takeAwayStockDetails>takeAwayStockDetailsSelected,alltakeAwayStockDetails;
        alltakeAwayStockDetails=table1.getItems();
        takeAwayStockDetailsSelected=table1.getSelectionModel().getSelectedItems();
        takeAwayStockDetailsSelected.forEach(alltakeAwayStockDetails::remove);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Please Select a record to delete!!");
            alert.showAndWait();
        } 
    }
    

    
        
      
     

   
}
