/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerControllers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import CustomerCodes.Queries;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustAnalysisNReportsController implements Initializable {

    @FXML
    private PieChart chart;
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;
    Queries DBqry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBqry = new Queries();
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - Analysis & Reports");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/CustHome.fxml";
        loadChart();
    }
    
    public void loadChart(){
        //getting customer
        con = DBconnect.dbconnect();

        int begCount = 0;
        int prioCount = 0;
        int platCount = 0;
        
        //getting biginner`s count
        try {
            String qry = "SELECT * FROM customer where custType = 'Beginner'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                begCount++;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        //getting priority`s count
        try {
            String qry = "SELECT * FROM customer where custType NOT IN ('Beginner','Platinum')";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                prioCount++;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        //getting platinum`s count
        try {
            String qry = "SELECT * FROM customer where custType = 'Platinum'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                platCount++;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        //chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                    new PieChart.Data("Biginner", begCount),
                    new PieChart.Data("Priority",prioCount),
                    new PieChart.Data("Platinum",platCount)
                );
        chart.setData(pieChartData);
        chart.setTitle("Customer");
                        
    }
    
}
