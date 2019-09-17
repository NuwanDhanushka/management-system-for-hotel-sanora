/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerCodes;

import java.util.Optional;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author THARINDU
 */
public class Validations {
  
    //-------------------------------Confirmations--------------------------------------------
     //cancel confirmation
    
    //confirm adding another new record
    public static boolean nextNewRecordConfirm(String rec){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Add Another New "+rec);
        alert.setHeaderText("You have added one "+rec+" successfully..\n Do you want to add another new "+rec+" ?");
        alert.setContentText("Click on cancel to go to home");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
    
    //--------------------------------Alerts--------------------------------------------------
     //Notifying empty field
     public static void fieldEmptyMsg(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Look, One or more required fields are empty !");
        alert.setContentText("Ooops, there was an error!");

        alert.showAndWait();
     }
     //Notifying unselected comboboxes
     public static void unselectedComboBoxMsg(String field){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please select a "+field+" !");
        alert.setContentText("Ooops, there was an error!");

        alert.showAndWait();
     }
     //Notifying wrong text field values
     public static void wrongTextValueMsg(String field,String sample){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please enter a valid "+field+" !");
        alert.setContentText(""+field+" should be like (Eg:) "+sample+"");

        alert.showAndWait();
     }
    
    //--------------------------------Key Typing Validations----------------------------------
     //Prohibiting characters in numeric fields
     public static void integersOnly(KeyEvent event){
         String k=event.getCharacter();
        if (!Character.isDigit(k.charAt(0)))
             event.consume();
     }
     //Letters only
     public static void lettersOnly(KeyEvent event){
         String k=event.getCharacter();
        if (!Character.isLetter(k.charAt(0))&&!Character.isSpaceChar(k.charAt(0)))
             event.consume();
     }
     //Digits & V/v Letter only
     public static void lettersAndDigitsOnly(KeyEvent event){
         String k=event.getCharacter();
        if (!(Character.isDigit(k.charAt(0))||k.charAt(0)=='v'||k.charAt(0)=='V'))
             event.consume();
    } 
     
    //--------------------------------Other Validations---------------------------------------
     //NIC validation
     public static boolean NICvalidate(String NIC){
        if(NIC.length()!=10){
            wrongTextValueMsg("NIC","958899501V");
            return false;
        } 
        else{
            for(int x=0;x<10;x++){
                if(x==9){
                    if(NIC.charAt(x)!='V'){
                        wrongTextValueMsg("NIC","958899501V");
                        return false;
                    }
                }
                else{
                    if(!Character.isDigit(NIC.charAt(x))){
                        wrongTextValueMsg("NIC","958899501V");
                        return false;
                    }
                }
            }
        }
        return true;
     }
     
     //Telephone validation
     public static boolean TPvalidate(String TP){
        boolean validation = true;
        if((!(TP.length()==10||TP.length()==9) && !(TP.isEmpty()))){
               wrongTextValueMsg("Telephone NO","0119588635");
               validation = false;
        }
        else{
            for(int x=0;x<TP.length();x++){
                if(!Character.isDigit(TP.charAt(x))){
                        wrongTextValueMsg("Telephone NO","0119588635");
                        validation = false;
                    }
            }
        }
        return validation;
     }
     
     //Email Address Validation
     public static boolean emailValidate(String email){
        boolean validation = true;
        try {
            InternetAddress IA = new InternetAddress(email);
            IA.validate();
        }catch (AddressException ex) {
            wrongTextValueMsg("Email Address","abc@mail.com");
            validation = false;
        }
        return validation;
     }
     
}
