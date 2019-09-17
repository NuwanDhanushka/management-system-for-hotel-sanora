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

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class UpdateSupplierController implements Initializable {

    @FXML
    private TextField tfSupplierName;
    @FXML
    private TextArea taContactNumbers;
    @FXML
    private TextArea taSupplierAddress;
    @FXML
    private TextArea taSupplierDescription;
    @FXML
    private Button btnUpdate;
    
    supplier supplier = new supplier();
    supplierDataGateway dataGateway = new supplierDataGateway();
    private String userId="1";
    public String supplierId;
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
    private void btnUpdateOnAction(ActionEvent event) {
               if(isNotNull()) {
            supplier.id = supplierId;
            supplier.supplierName = tfSupplierName.getText().trim();
            supplier.supplierContactNumber = taContactNumbers.getText().trim();
            supplier.supplierAddress = taSupplierAddress.getText().trim();
            supplier.supplierDescription = taSupplierDescription.getText().trim();
            dataGateway.update(supplier);
//            takeHistoy();
//            tfSearchOnType(event);
        }
    }
    public boolean isNotNull() {
        boolean isNotNull;
        if (tfSupplierName.getText().trim().isEmpty()
                || tfSupplierName.getText().trim().isEmpty()
                || taSupplierAddress.getText().trim().isEmpty()
                || taSupplierAddress.getText().trim().isEmpty()) {

          /*  Dialogs.create().title("")
                    .masthead("Null")
                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                    .message("Please fill all requre field")
                    .showWarning();*/
            isNotNull = false;

        } else {
            isNotNull = true;
        }
        return isNotNull;
    }   
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
       Stage stage = (Stage) btnClose.getScene().getWindow();
       stage.close();
    }
    public void showDetails(){
        supplier.id = supplierId;
        dataGateway.selectedView(supplier);
        tfSupplierName.setText(supplier.supplierName);
        taContactNumbers.setText(supplier.supplierContactNumber);
        taSupplierAddress.setText(supplier.supplierAddress);
        taSupplierDescription.setText(supplier.supplierDescription);
    }
    
}
