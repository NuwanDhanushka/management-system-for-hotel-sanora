package billing_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
 
     @FXML
    public void handleButton(ActionEvent event) throws Exception {
       
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("calBillPub.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleButton2(ActionEvent event) throws Exception {
        
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("calBillTakeAway.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
