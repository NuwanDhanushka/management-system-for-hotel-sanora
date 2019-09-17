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
public class tabletaxcontroller {
    
  
    private StringProperty taxname;
    private StringProperty taxpercen;


    public tabletaxcontroller(String taxname, String taxpercen) {
        this.taxname = new SimpleStringProperty(taxname);
        this.taxpercen = new SimpleStringProperty(taxpercen);
       
    }

    tabletaxcontroller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String gettaxname() {
        return taxname.get();
    }

    public String gettaxpercen() {
        return taxpercen.get();
    }

    
    
    public void settaxname(String value) {
        taxname.set(value);
    }

    public void settaxpercen(String value ) {
        taxpercen.set(value);
    }

  
    
    
    public StringProperty taxnameProperty (){
        return taxname; 
    }
    
    public StringProperty taxpercenProperty (){
        return taxpercen; 
    }
    
   
}



