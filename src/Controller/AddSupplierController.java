/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataAccessLayer.supplier;
import DataGateway.supplierDataGateway;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class AddSupplierController implements Initializable {

    @FXML
    private TextField tfSupplierName;
    @FXML
    private TextArea taContactNumbers;
    @FXML
    private TextArea taSupplierAddress;
    @FXML
    private TextArea taSupplierDescription;
    
    supplier supplier = new supplier();
    supplierDataGateway dataGateway = new supplierDataGateway();
    private String userId="1";
    @FXML
    private Button btnClose;
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
       if (isNotNull()) {
       supplier.supplierName = tfSupplierName.getText();
       supplier.supplierContactNumber = taContactNumbers.getText();
       supplier.supplierAddress = taSupplierAddress.getText();
       supplier.supplierDescription = taSupplierDescription.getText();
       supplier.creatorId = userId;
       dataGateway.Add(supplier);

            clrAll();
        }
    }
 
    public boolean isNotNull() {
        boolean isNotNull;
        if (tfSupplierName.getText().trim().isEmpty()
                || tfSupplierName.getText().trim().isEmpty()
                || taSupplierAddress.getText().trim().isEmpty()
                || taSupplierAddress.getText().trim().isEmpty()) {

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
      private void clrAll() {
        tfSupplierName.clear();
        taContactNumbers.clear();
        taSupplierAddress.clear();
        taSupplierDescription.clear();
    }
}
