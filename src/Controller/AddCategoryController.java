/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogicLayer.categoryBLL;
import DataAccessLayer.category;
import DataGateway.categoryDataGateway;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class AddCategoryController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnAdd;
    @FXML
    private TextArea taCategoryDescription;
    @FXML
    private TextField tfCategoryName;
    @FXML
    private ComboBox<String> cmbSupplier;
    @FXML
    private ComboBox<String> cmbBrandName;

    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    category category = new category();
    categoryDataGateway dataGateway = new categoryDataGateway();
    categoryBLL categoryBLL = new categoryBLL();
    
    private String userId="1";
    public String supplierId;
    public String supplierName;
    public String categoryId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
              if(isNotNull()){
            category.brandName = cmbBrandName.getSelectionModel().getSelectedItem();
            category.supplierName = cmbSupplier.getSelectionModel().getSelectedItem();
            category.categoryName = tfCategoryName.getText().trim();
            category.categoryDescription = taCategoryDescription.getText().trim();
            category.creatorId = userId;
            categoryBLL.Add(category);
    }
    }

    @FXML
    private void cmbSupplierOnClick(MouseEvent event) {
        cmbBrandName.getItems().clear();
        cmbBrandName.setPromptText(null);
        try {
            pst = connection.prepareStatement("select * from supplier");
            rs = pst.executeQuery();
            while (rs.next()){
                supplierName = rs.getString(2);
                cmbSupplier.getItems().remove(supplierName);
                cmbSupplier.getItems().add(supplierName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cmbBrandNameOnClick(MouseEvent event) throws SQLException {
        cmbBrandName.getItems().clear();
        supplierName = cmbSupplier.getSelectionModel().getSelectedItem();
        supplierId = sql.getIdNo(supplierName,supplierId,"supplier","supplierName");

        pst = connection.prepareStatement("select * from brands where supplierId=?");
        pst.setString(1, supplierId);
        rs = pst.executeQuery();
        while (rs.next()){
            cmbBrandName.getItems().add(rs.getString(2));
        }   
    }
    public boolean isNotNull(){
        boolean isNotNull;
        if(tfCategoryName.getText().trim().isEmpty()
                || cmbSupplier.getSelectionModel().isEmpty()
                && cmbSupplier.getPromptText().isEmpty()
                || cmbBrandName.getSelectionModel().isEmpty()
                && cmbBrandName.getPromptText().isEmpty()){

      Notifications.create()
              .title("One of the fields is null")
              .text("Please enter values to the fields!")
              .showWarning();

            isNotNull = false;
        }else{
            isNotNull = true;
        }
        return isNotNull;
    }
}
