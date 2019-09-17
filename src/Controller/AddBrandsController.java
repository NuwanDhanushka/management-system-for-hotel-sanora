/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogicLayer.brandBLL;
import DataAccessLayer.brands;
import DataGateway.brandDataGateway;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class AddBrandsController implements Initializable {

    @FXML
    private ComboBox<String> cmbSupplier;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfBrandName;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnAdd;
    
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    brands brands = new brands();
    brandDataGateway dataGateway = new brandDataGateway();
    brandBLL brandBLL = new brandBLL();
    
    public String brandId;
    private String usrId="1";
    private String supplierName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cmbSupplierOnClick(MouseEvent event) {
        cmbSupplier.getItems().clear();
        connection = dbConnection.getConnection();
        try {
            pst = connection.prepareStatement("select * from supplier order by SupplierName");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplierName = rs.getString(2);
               
                cmbSupplier.getItems().add(supplierName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBrandsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbSupplierOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage  stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
          System.out.println(usrId);
        if(isNotNull()){
            brands.creatorId = usrId;
            brands.brandName = tfBrandName.getText();
            brands.brandDescription = taDescription.getText();
            brands.supplierName = cmbSupplier.getSelectionModel().getSelectedItem();
            brandBLL.Add(brands);
         
    }
    }
    
    public boolean isNotNull() {
        System.out.println(cmbSupplier.getPromptText());
//        System.out.println(cbSupplyer.getSelectionModel().getSelectedItem().isEmpty());
        System.out.println(tfBrandName.getText());
        boolean isNotNull;
        if (tfBrandName.getText().trim().isEmpty()
                || cmbSupplier.getSelectionModel().isEmpty()
                && cmbSupplier.getPromptText().isEmpty()) {
         Notifications.create()
              .title("One of the fields is null")
              .text("Please enter values to the fields!")
              .showWarning();
            isNotNull = false;

        } else {
            isNotNull = true;
        }
        return isNotNull;
    }
}
