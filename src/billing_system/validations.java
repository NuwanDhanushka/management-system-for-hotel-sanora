
package billing_system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class validations {
    private TextField text1;
    private TextField text2;
    private TextField text3;
    private TextField text4;
    private TextField text6;
      
    private boolean validateItemCode(){
       Pattern p=Pattern.compile("[A-Z][0-9][0-9][0-9][0-9]");
        Matcher m=p.matcher(text1.getText());
        if(m.find()&& m.group().equals(text1.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Error in Item Code feild!!");
           alert.setHeaderText(null);
           alert.setContentText("Please Enter a Valid ItemCode!! \n First digit should be a Charactor in Upper Case \n and other 4 digits should be Numeric!");
           alert.showAndWait();
           return false;
        
    }
        
    }
    
     boolean validateFields(){
        if(text1.getText().isEmpty() |text2.getText().isEmpty() | text3.getText().isEmpty() | text4.getText().isEmpty() | text6.getText().isEmpty()){
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Empty  Fileds!!");
           alert.setHeaderText(null);
           alert.setContentText("Please fill all the fields!!");
           alert.showAndWait();
           return false;
        }
        
       else
            return true;
    }
     private boolean validateItemNameField(){
        
        Pattern p=Pattern.compile("[a-zA-Z]+");
        Matcher m=p.matcher(text2.getText());
        if(m.find()&& m.group().equals(text2.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Item Name feild!!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter valid name for Item Name! \n It should be only conatin charactors! ");
            alert.showAndWait();
            return false;
        
    }
    }
    
     private boolean validateSupplierFeild(){
        
        Pattern p=Pattern.compile("[a-zA-Z]+");
        Matcher m=p.matcher(text4.getText());
        if(m.find()&& m.group().equals(text4.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Supplier feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid Supplier ");
            alert.showAndWait();
            return false;
        
    }
    }
     
     private boolean validateQuantityFeild(){
        Pattern p=Pattern.compile("[0-9]+");
        Matcher m=p.matcher(text6.getText());
        if(m.find()&& m.group().equals(text6.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Quantity feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid quantity! \n it should be only contain numeric values!! ");
            alert.showAndWait();
            return false;
        
    }
    }
     
      private boolean validatePriceFeild(){
        Pattern p=Pattern.compile("[0-9]+[.][0-9]+");
        Matcher m=p.matcher(text3.getText());
        if(m.find()&& m.group().equals(text3.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error in Price feild!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid price! \n it should be only contain numeric values  \n containing decimal points!! ");
            alert.showAndWait();
            return false;
        
    }
    }
}
