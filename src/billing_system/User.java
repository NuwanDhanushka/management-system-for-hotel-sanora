
package billing_system;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    
    
    private StringProperty itemcode;
    private StringProperty itemname;
    private  double price;
    private  StringProperty seller;
    private  int quantity;
 
    public User(String itemcode, String itemname, double price, String seller, int quantity) {
        this.itemcode = new SimpleStringProperty(itemcode);
        this.itemname = new SimpleStringProperty(itemname);
        this.price    = new Double(price);
        this.seller     = new SimpleStringProperty(seller);
        this.quantity    = quantity;
     
    }

   

      

    public String getItemcode() {
        return itemcode.get();
    }

    public String getItemname() {
        return itemname.get();
    }

    public double getPrice() {
        return price;
           }

    public String getSeller() {
        return seller.get();
    }

    public int getQuantity() {
        return quantity;
    }

       
    public void setItemcode(String value) {
        itemcode.set(value);
    }

    public void setItemname(String value ) {
        itemname.set(value);
    }

    public void setPrice(double value) {
        this.price=value;
    }

    public void setSeller(String value) {
        seller.set(value);
    }

    public void setQuantity(int value) {
       this.quantity=value;
    }

  
    
    
    public StringProperty itemcodeProperty (){
        return itemcode; 
    }
    
    public StringProperty itemnameProperty (){
        return itemname; 
    }
    
  //  public StringProperty priceProperty (){
    //    return price; 
    //}
    
    public StringProperty sellerProperty (){
        return seller; 
    }
    
 //  public StringProperty avalabiProperty (){
   //    return quantity;
  // }
   
}



