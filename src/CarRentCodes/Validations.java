
package CarRentCodes;

import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;

public class Validations {
   
     //-------------------------------Confirmations--------------------------------------------
     //cancel confirmation
    
    //confirm adding another new record
    public static boolean nextNewRecordConfirm(String rec){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Look, One or more required fields are empty !");
        alert.setContentText("Ooops, there was an error!");

        alert.showAndWait();
     }
     //Notifying other error unique
     public static void WrongMsg(String field){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(field);
        alert.setContentText("Ooops, there was an error!");

        alert.showAndWait();
     }
     //Notify if value is negative
      public static void NegativeType(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please enter Positive values!");
      
        alert.showAndWait();
     }
     
     
     //Notifying wrong double typr field values
      public static void wrongDoubleType(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please enter Numaric values!");
      
        alert.showAndWait();
     }
     //notify search is empty errors
      public static void Search_error(){
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Please Select a record to proceed!");
            alert.setContentText("Careful with this step!");

            alert.showAndWait();
          
          
      }
      public static void successfull_msg(){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("you have sucessfully updated records");

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
      public static void positiveOnly(KeyEvent event){
         String k=event.getCharacter();
        if (k.equals("-"))
             event.consume();
     }
     
    //--------------------------------Other Validations---------------------------------------
  
   
    public static boolean isDouble(String a){
        
        try{
             double  aa = Double.parseDouble(a);
        }
         catch(NumberFormatException nfe){
             return false;
         }
        return true;
        
        
        
    }
    public static boolean isInteger(String a){
        
       try{
              int  aa = Integer.parseInt(a);
        }
         catch(NumberFormatException e){
             return false;
         }
        return true; 
        
        
        
    }
        public static boolean isPositive(String a){
        
       
              double  aa = Double.parseDouble(a);
        if(aa<0){
            
             return false; 
        }
        return true; 
        
        
        
    }
    
 //------------------registration number validation----------------------------------------------
    public static boolean regNO_valid(String valid) {

        int l = valid.length();
    
        int no = l - 5;
        if (no > 0) {

            boolean value = upper(no, valid);
            if (value == true) {

                for (int j = l - 1; j >= (no); j--) {

                    if (j == (no)) {
                        if (valid.charAt(j) != '-') {

                            return false;
                        }

                    } else if (!Character.isDigit(valid.charAt(j))) {

                        return false;
                    }
                }  //end of for
            }//end of value if
            if (value == false) {
                return false;
            }

        }
        if (no < 0) {
            return false;
        }

        return true;
    }

    public static boolean upper(int no, String valid) {

        for (int i = 0; i < no; i++) {

            if (Character.isAlphabetic(valid.charAt(i))) {
                if (!Character.isUpperCase(valid.charAt(i))) {
                    return false;
                }

            } else if (!Character.isDigit(valid.charAt(i))) {
                return false;
            }

        }

        return true;

    }

 //////////////////////////////////////////////////////////////////////////////////   
    
    public static boolean Datevali(LocalDate lst,LocalDate nxt) {
        

        int year_nxt = nxt.getYear();
        int month_nxt = nxt.getMonthValue();
        int day_nxt = nxt.getDayOfMonth();
        
        int year_lst = lst.getYear();
        int month_lst = lst.getMonthValue();
        int day_lst = lst.getDayOfMonth();
        
        int year = (year_nxt - year_lst);
        int months = (month_nxt - month_lst);
        int Days = (day_nxt - day_lst);
        
         int nof_days = year * 365 + months * 30 + Days * 1;
         
         if(nof_days>=0){
              return true;
         }
         else
         {
             return false;
         }
            
          
    }   
   
   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
