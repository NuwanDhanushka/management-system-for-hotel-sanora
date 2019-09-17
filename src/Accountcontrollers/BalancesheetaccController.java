/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accountcontrollers;

import Controllers.MainHomeController;
import Controllers.SubHomeController;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author U Computers
 */
public class BalancesheetaccController implements Initializable {

 
    @FXML
    private Button viewloanbutton;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private TableView<tableprofitandloss> pltable;
    @FXML
    private TableColumn<tableprofitandloss, String> description;
    @FXML
    private TableColumn<tableprofitandloss, String> profit;
    private ObservableList<tableprofitandloss> data;
    private String des;
    private double taxamount = 0;
    private double loanamount = 0;
    private double caramount = 0;
    private double delveryamount = 0;
    private double utilityamount = 0;
    private double supplieramount = 0;
    private double eventamount = 0;
    private double reservationamount = 0;
    private double salaryamount = 0;
    private double totoperatingincome;
    private double totaloperatingexpense;
    private double grossprofit;
    private double netprofit;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void viewbuttonevent(ActionEvent event) {
        calculations();
    }
    //calculating profit,loss and inserting to the DB
    
    @FXML
    public void calculations(){
    
           Connection con = DBconnect.dbconnect();
 
        try {

            ResultSet rs = con.createStatement().executeQuery("SELECT * from acc");

            while (rs.next()) {
                
             
                    
                des = rs.getString("des");
                if (des.equals("tax")) {
                    taxamount = Double.parseDouble(rs.getString("amount")) + taxamount;
                } else if (des.equals("loan")) {
                    loanamount = Double.parseDouble(rs.getString("amount")) + loanamount;
                } else if (des.equals("car")) {
                    caramount = Double.parseDouble(rs.getString("amount")) + caramount;
                } else if (des.equals("delivery")) {
                    delveryamount = Double.parseDouble(rs.getString("amount")) + delveryamount;
                } else if (des.equals("utility")) {
                    utilityamount = Double.parseDouble(rs.getString("amount")) + utilityamount;
                } else if (des.equals("supplier")) {
                    supplieramount = Double.parseDouble(rs.getString("amount")) + supplieramount;
                } else if (des.equals("event")) {
                    eventamount = Double.parseDouble(rs.getString("amount")) + eventamount;
                } else if (des.equals("reservation")) {
                    reservationamount = Double.parseDouble(rs.getString("amount")) + reservationamount;

                } else if (des.equals("salary")) {
                    salaryamount = Double.parseDouble(rs.getString("amount")) + salaryamount;

                }
                // transamount=Double.parseDouble(rs.getString("amount"));
            
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        //-------------operating income
        
        totoperatingincome = reservationamount + eventamount + delveryamount + caramount;

        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "OPERATING INCOME" + "','" + totoperatingincome + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     RESERVATION INCOME" + "','" + reservationamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     EVENT INCOME" + "','" + eventamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     DELIVERY INCOME" + "','" + delveryamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     CAR RENTAL INCOME" + "','" + caramount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        // -----------------------------expenses-------------------------
        totaloperatingexpense = salaryamount + loanamount + utilityamount + supplieramount;
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "TOTAL OPERATING EXPENSES" + "','" + totaloperatingexpense + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     SALARIES PAID" + "','" + salaryamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     LOAN AMOUNTS PAID" + "','" + loanamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     UTILITY BILLS PAID" + "','" + utilityamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     PAID AMOUNTS TO SUPPLIERS " + "','" + supplieramount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }

        //---------------------gross profit
        grossprofit =   totoperatingincome-totaloperatingexpense;
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "GROSS PROFIT " + "','" + grossprofit + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        //---------------net profit
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "     TAXES PAID " + "','" + taxamount + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        netprofit = grossprofit - taxamount;
        try {
            String q = "INSERT INTO profitloss(description,profit) values ('" + "NET PROFIT " + "','" + netprofit + "')";
            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);

        }
        outputtotable();
    
    
    }
    @FXML
    public void outputtotable(){
         pltable.setItems(null);
       try {
            Connection con = DBconnect.dbconnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * From profitloss");

            while (rs.next()) {
                data.add(new tableprofitandloss(rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        

       pltable.setItems(null);
       pltable.setItems(data); 
    
    
    
    
    
    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Accounts - PROFIT AND LOSS");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        } catch (IOException ex) {
            System.out.print(ex);
        }
        SubHomeController.backUrl = "/GUIs/AccountsHome.fxml";
    }

}
