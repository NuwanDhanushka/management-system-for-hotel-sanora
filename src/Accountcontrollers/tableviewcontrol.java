/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accountcontrollers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author U Computers
 */
public class tableviewcontrol {
      private StringProperty date1;
    private StringProperty des1;
    private  StringProperty amount1;
     private  StringProperty type;
    /*
    private  StringProperty seller;
    private  StringProperty avalabi;
    private  StringProperty expDate; 
    */
     public tableviewcontrol(String itemcode1, String itemname1, String price1,String type) {
        this.date1= new SimpleStringProperty(itemcode1);
        this.des1 = new SimpleStringProperty(itemname1);
        this.amount1   = new SimpleStringProperty(price1);
         this.type   = new SimpleStringProperty(type);
        /*
        this.seller = new SimpleStringProperty(seller);
        this.avalabi = new SimpleStringProperty(avalabi);
        this.expDate = new SimpleStringProperty(expDate);
        */
    }

    tableviewcontrol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getdate() {
        
        return date1.get();
    }

    public String getdes() {
        return des1.get();
    }

    public String getamount() {
        return amount1.get();
    }
      public String gettype() {
        return type.get();
    }
/*
    public String getSeller() {
        return seller.get();
    }

    public String getAvalabi() {
        return avalabi.get();
    }

    public String getExpDate() {
        return expDate.get();
    }
    
    
    public void setItemcode(String value) {
        itemcode.set(value);
    }

    public void setItemname(String value ) {
        itemname.set(value);
    }

    public void setPrice(String value) {
        price.set(value);
    }

    public void setSeller(String value) {
        seller.set(value);
    }

    public void setAvalabi(String value) {
       avalabi.set(value);
    }

    public void setExpDate(String value) {
        expDate.set(value);
    }
    
   */ 
    public StringProperty dateProperty (){
         
        return date1; 
    }
    
    public StringProperty desProperty (){
        return des1; 
    }
    
    public StringProperty amountProperty (){
        return amount1; 
    }
     public StringProperty typeProperty (){
        return type; 
    }
    /*
    public StringProperty sellerProperty (){
        return seller; 
    }
    
    public StringProperty avalabiProperty (){
        return avalabi;
    }
    
    public StringProperty expDateProperty (){
        return expDate;
    }
*/
}
