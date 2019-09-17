/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationEventsControllers;

import Controllers.CustHomeController;
import Main.DBconnect;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
//import static ReservationEventsControllers.ManageEventsSubMenuListController.accListState;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Anushka_Hapukotuwa
 */
//Handles all details regarding managing an event
public class ManageEventsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static int accListState;
    Connection con = null;
    PreparedStatement pst = null;

    @FXML
    private ComboBox packagesel;
    @FXML
    private ColorPicker color;
    @FXML
    private ComboBox extra1;
    @FXML
    private ComboBox extra2;
    @FXML
    private ComboBox extra3;
    @FXML
    private ComboBox extra4;
    @FXML
    private ComboBox extra5;
    @FXML
    private ComboBox extra6;
    @FXML
    private Label vehiclelbl;
    @FXML
    private Label extraslbl;
    @FXML
    private Label musiclbl;
    @FXML
    private Label defaultlbl;
    @FXML
    private Label imagelbl;
    @FXML
    private Label perhead;
    @FXML
    private Label total;
    @FXML
    private TextField extrastxt;
    @FXML
    private TextField platenum;
    @FXML
    private TextArea extraTextArea;

    @FXML
    private Button next;
    @FXML
    private Button checkCostBtn;
    
  
    
    private DBconnect conn;

    String pname, vehicle, extras, music, defaultcontent;
    double perplate, defaultpackagecost;
    double defaultcostperplate;
    double appcost, soupcost, maincost, sidecost, dessertcost, drinkcost;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Reservations and Events - Events");
        subHomeC.backBtn.setVisible(true);//Set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/EventHome.fxml";

        ColorPicker colorPicker = new ColorPicker();
       
        
        colorPicker.setValue(Color.WHITE);
       // int xv=Calendar.HOUR_OF_DAY;
      //  System.out.println(String.valueOf(xv));
        packagesel.getItems().removeAll(packagesel.getItems());
        packagesel.getItems().addAll("PLATINUM", "GOLD", "SILVER");
        packagesel.getSelectionModel().select("PLATINUM");

        extra1.getItems().removeAll(extra1.getItems());
        extra1.getItems().addAll("None", "Devilled Crab Dip", "Fried Mozzarella Cheese Sticks", "Grilled Bacon Wraps", "Grilled Marinated Shrimps", "Toasted Garlic Bread", "Vegetable Spring Rolls");
        extra1.getSelectionModel().select("None");

        extra2.getItems().removeAll(extra2.getItems());
        extra2.getItems().addAll("None", "Chilled Cucumber Soup", "Cream of Wild Mushroom Soup", "French Onion Soup", "Roasted-Chicken Noodle Soup", "Spicy Chicken Soup", "Tomato Basil Soup");
        extra2.getSelectionModel().select("None");

        extra3.getItems().removeAll(extra3.getItems());
        extra3.getItems().addAll("None", "Chicken Biriyani", "Mushroom Bolognese", "Shrimp Fried Rice", "Steamed Basmathi Yellow Rice", "Shanghai Pork Noodles", "Spicy Mixed Seafood Fried Rice");
        extra3.getSelectionModel().select("None");

        extra4.getItems().removeAll(extra4.getItems());
        extra4.getItems().addAll("None", "Bakes Mushrooms & Potatoes with Spinach", "Grilled Tuna", "Lemon Pepper Roasted Chicken", "Warm Corn Salad");
        extra4.getSelectionModel().select("None");

        extra5.getItems().removeAll(extra5.getItems());
        extra5.getItems().addAll("None", "Black Forest Cherry Cake", "Caramel Pudding", "Chocolate Mousse", "Pineapple Upside-Down Cake", "Vanilla Cheesecake with Cherry Topping", "Watalappan");
        extra5.getSelectionModel().select("None");

        extra6.getItems().removeAll(extra6.getItems());
        extra6.getItems().addAll("None", "Apple, Mint & Grape Punch", "Almond Iced Coffee", "Bourbon Slush", "Cranberry & Pineapple Punch", "Grapefruit & Lime Gin", "Sparkling Cherry Cocktail");
        extra6.getSelectionModel().select("None");
    }

    @FXML
    public void CheckEventCostButtonAction(ActionEvent event) {
        if (emptyExtrasField() == true && emptyPlatesField() == true && validateExtrasField() == true && validatePlatesField() == true) {
            String appetizer = extra1.getSelectionModel().getSelectedItem().toString();
            String soup = extra2.getSelectionModel().getSelectedItem().toString();
            String mainCourse = extra3.getSelectionModel().getSelectedItem().toString();
            String sideDish = extra4.getSelectionModel().getSelectedItem().toString();
            String dessert = extra5.getSelectionModel().getSelectedItem().toString();
            String drink = extra6.getSelectionModel().getSelectedItem().toString();
            int extrasNo = Integer.parseInt(extrastxt.getText());
            int plateNo = Integer.parseInt(platenum.getText());
            String themeCol = "WHITE";
            if (!appetizer.equals("None")) {
                appcost = 50 * extrasNo;
            }
            if (!soup.equals("None")) {
                soupcost = 60 * extrasNo;
            }
            if (!mainCourse.equals("None")) {
                maincost = 60 * extrasNo;
            }
            if (!sideDish.equals("None")) {
                sidecost = 60 * extrasNo;
            }
            if (!dessert.equals("None")) {
                dessertcost = 60 * extrasNo;
            }
            if (!drink.equals("None")) {
                drinkcost = 90 * extrasNo;
            }

            //float costPerHead = Float.parseFloat(perhead.getText());
            //float costTotal = Float.parseFloat(total.getText());
            String packagetype = packagesel.getSelectionModel().getSelectedItem().toString();
            try {

                Connection con = conn.dbconnect();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM packagedet");

                while (rs.next()) {
                    if (packagetype.equals(rs.getString("pname"))) {
                        vehicle = rs.getString("vehicle");
                        extras = rs.getString("extras");
                        music = rs.getString("music");
                        defaultcontent = rs.getString("defaultcontent");
                        perplate = Double.parseDouble(rs.getString("perplate"));
                        defaultpackagecost = Double.parseDouble(rs.getString("defaultpackagecost"));

                    }
                }
            } catch (SQLException ex) {
                System.err.println("An error occured during retrieval of records for preview from reservation table! " + ex);
            }

            vehiclelbl.setText(vehicle);
            extraslbl.setText(extras);
            musiclbl.setText(music);
            defaultlbl.setText(defaultcontent);
            defaultcostperplate = perplate;
            double totprice = defaultpackagecost + drinkcost + dessertcost + sidecost + maincost + soupcost + appcost;

            
            total.setText(String.valueOf(totprice));
            // the following is wrong 
            perhead.setText(String.valueOf(defaultcostperplate));
        }

    }

    @FXML
    public void packageselevent(ActionEvent event) {
        getpackagedet();

    }

    @FXML
    public void getpackagedet() {
        String packagetype = packagesel.getSelectionModel().getSelectedItem().toString();
        try {
            Connection con = conn.dbconnect();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM packagedet");

            while (rs.next()) {
                if (packagetype.equals(rs.getString("pname"))) {
                    vehicle = rs.getString("vehicle");
                    extras = rs.getString("extras");
                    music = rs.getString("music");
                    defaultcontent = rs.getString("defaultcontent");
                    perplate = Double.parseDouble(rs.getString("perplate"));
                    defaultpackagecost = Double.parseDouble(rs.getString("defaultpackagecost"));

                }
            }
        } catch (SQLException ex) {
            System.err.println("An error occured during retrieval of records for preview from reservation table! " + ex);
        }

        vehiclelbl.setText(vehicle);
        extraslbl.setText(extras);
        musiclbl.setText(music);
        defaultlbl.setText(defaultcontent);
        defaultcostperplate = perplate;

        vehicle = "";
        extras = "";
        music = "";
        defaultcontent = "";

    }
    @FXML
    public void SubmitEventButtonAction(ActionEvent event) {
        if (emptyExtrasField() == true && emptyPlatesField() == true && validateExtrasField() == true && validatePlatesField() == true) {
            String appetizer = extra1.getSelectionModel().getSelectedItem().toString();
            String soup = extra2.getSelectionModel().getSelectedItem().toString();
            String mainCourse = extra3.getSelectionModel().getSelectedItem().toString();
            String sideDish = extra4.getSelectionModel().getSelectedItem().toString();
            String dessert = extra5.getSelectionModel().getSelectedItem().toString();
            String drink = extra6.getSelectionModel().getSelectedItem().toString();
            int extrasNo = Integer.parseInt(extrastxt.getText());
            int plateNo = Integer.parseInt(platenum.getText());
            String themeCol = "WHITE";
            if (!appetizer.equals("None")) {
                appcost = 50 * extrasNo;
            }
            if (!soup.equals("None")) {
                soupcost = 60 * extrasNo;
            }
            if (!mainCourse.equals("None")) {
                maincost = 60 * extrasNo;
            }
            if (!sideDish.equals("None")) {
                sidecost = 60 * extrasNo;
            }
            if (!dessert.equals("None")) {
                dessertcost = 60 * extrasNo;
            }
            if (!drink.equals("None")) {
                drinkcost = 90 * extrasNo;
            }

            
            String packagetype = packagesel.getSelectionModel().getSelectedItem().toString();
            
            Connection con = conn.dbconnect();
            
            //Confirmation alert box for adding a new reservation
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("CONFIRMATION");
            confirm.setHeaderText("Add new event?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    
                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM packagedet");

                    while (rs.next()) {
                        if (packagetype.equals(rs.getString("pname"))) {
                            vehicle = rs.getString("vehicle");
                            extras = rs.getString("extras");
                            music = rs.getString("music");
                            defaultcontent = rs.getString("defaultcontent");
                            perplate = Double.parseDouble(rs.getString("perplate"));
                            defaultpackagecost = Double.parseDouble(rs.getString("defaultpackagecost"));
                        }
                    }
                } catch (SQLException ex) {
                    System.err.println("An error occured during retrieval of record details from packagedet table! " + ex);
                }

                vehiclelbl.setText(vehicle);
                extraslbl.setText(extras);
                musiclbl.setText(music);
                defaultlbl.setText(defaultcontent);
                defaultcostperplate = perplate;
                double totprice = defaultpackagecost + drinkcost + dessertcost + sidecost + maincost + soupcost + appcost;
                try {
                    //Connection con = conn.dbconnect();
                    String q = "INSERT INTO eventdet(Appetizer,Soup,MainCourse,SideDishes,Dessert,Drink,NumberOfExtras,NumberOfPlates,ThemeColor,PerHeadCost,TotalCost) values ('" + appetizer + "','" + soup + "','" + mainCourse + "','" + sideDish + "','" + dessert + "','" + drink + "','" + extrasNo + "','" + plateNo + "','" + themeCol + "','" + perplate + "','" + totprice + "')";
                    pst = con.prepareStatement(q);
                    pst.execute();
                    System.out.println("Insertion to eventdet table successful!");
                } catch (Exception e) {
                    System.out.println("An error occured during insertion of values to eventdet table! " + e);
                }

                try {
                    //Connection con = conn.dbconnect();
                    String q = "INSERT INTO temppackage(TotalCost) values ('" + totprice + "')";
                    pst = con.prepareStatement(q);
                    pst.execute();
                    System.out.println("Insertion to tempackage table successful!");
                    
                    //Alert box to show reservation was successful
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setHeaderText("New Event Confirmation");
                    success.setContentText("Event Successful!");
                    Optional<ButtonType> sresult = success.showAndWait();
                    if (sresult.get() == ButtonType.OK) {
                        System.out.println("Confirmation Successful!");
                    } else {

                    }
                } catch (Exception e) {
                    System.out.println("An error occured during insertion of value to temppackage table! " + e);
                }

            } else {
                //User chose not to add a new event
            }
        }
    }
    @FXML
    public void nextevent(ActionEvent event) throws IOException {

   
        ReservationEventsSubMenuListController.resListState = 3;
        ReservationEventsSubMenuListController.resListState = 1;
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Reservations and Events - Payments");
        subHomeC.subContent.getChildren().clear();
        subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ManagePayments.fxml")));
        subHomeC.subMenuList.getChildren().clear();
        subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/ReservationEventsGUIs/ReservationEventsSubMenuList.fxml")));

    }
    
    public void ExtraTableMouseClickAction(MouseEvent event){
        
        
    }

    //Validation for empty fields
    private boolean emptyExtrasField() {
        if (extrastxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Number of Extras Field Empty!");
            alert.setContentText("Please enter a valid number to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Validation for empty fields
    private boolean emptyPlatesField() {
        if (platenum.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Number of Plates Field Empty!");
            alert.setContentText("Please enter a valid number to proceed!");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Validation for allowing only positive integer values for SearchreservationID field
    private boolean validateExtrasField() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(extrastxt.getText());
        if (m.find() && m.group().equals(extrastxt.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in Number of Extras Field");
            alert.setContentText("Please enter a valid number! \n It should contain only positive integer values!");
            alert.showAndWait();
            return false;

        }
    }
    
    

    //Validation for allowing only positive integer values for SearchreservationID field
    private boolean validatePlatesField() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(platenum.getText());
        if (m.find() && m.group().equals(platenum.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Error in Number of Plates Field");
            alert.setContentText("Please enter a valid number! \n It should contain only positive integer values!");
            alert.showAndWait();
            return false;

        }
    }

}
