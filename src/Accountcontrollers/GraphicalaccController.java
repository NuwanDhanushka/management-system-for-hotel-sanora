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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.chart.*;
import javafx.scene.Group;

/**
 * FXML Controller class
 *
 * @author U Computers
 */
public class GraphicalaccController implements Initializable {
    
    @FXML
    private Button aaa;
    @FXML
    private Button profitgraphs;
    @FXML
    private PieChart chart;
    Connection con = null;
    PreparedStatement pst = null;
    @FXML
    private Label bx;
    
    @FXML
    public  void profitgraphsevent(ActionEvent event) {
            double expense=0;
        double income=0;
        int num=0;
         Scene scene = new Scene(new Group());
         Stage stage=new Stage();
        stage.setTitle("TAX");
        stage.setWidth(500);
        stage.setHeight(500);
        int x=20;
         try {
             
            Connection con = DBconnect.dbconnect();
           
            ResultSet rs = con.createStatement().executeQuery("SELECT * From acc");
            
            while (rs.next()) {
                String def=rs.getString("type");
                if(def.equals("expense")){
                    expense=expense+Double.parseDouble(rs.getString("amount"));
                
                }else
                {
                    income=income+Double.parseDouble(rs.getString("amount"));
                    
                }
               
            }
            
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("expense", expense),
                new PieChart.Data("income",income)
             
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("TAX");
        
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    
    
    
    }

    @FXML
    public  void tax(ActionEvent event) {
       
        double taxamountvat=0;
        double taxamountnbt=0;
        int num=0;
         Scene scene = new Scene(new Group());
         Stage stage=new Stage();
        stage.setTitle("TAX");
        stage.setWidth(500);
        stage.setHeight(500);
        int x=20;
         try {
             
            Connection con = DBconnect.dbconnect();
           
            ResultSet rs = con.createStatement().executeQuery("SELECT * From paidtax");
            
            while (rs.next()) {
                String def=rs.getString("name");
                if(def.equals("NBT")){
                    taxamountnbt=taxamountnbt+Double.parseDouble(rs.getString("amoun"));
                
                }else
                {
                    taxamountvat=taxamountvat+Double.parseDouble(rs.getString("amoun"));
                    
                }
               
            }
            
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("NBT", taxamountnbt),
                new PieChart.Data("VAT",taxamountvat)
             
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("TAX");
        
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Accounts - Graphical Representation");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/AccountsGUIs/AccSubMenuList.fxml")));
        } catch (IOException ex) {
            System.out.print(ex);
        }
        SubHomeController.backUrl = "/GUIs/AccountsHome.fxml";
    }

    /**
     * Initializes the controller class.
     */
}
