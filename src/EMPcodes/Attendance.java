/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMPcodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SaKU
 */
public class Attendance {
    private StringProperty eatt;
    private SimpleStringProperty inA;
    private StringProperty outA;
    private StringProperty dateA;
    private StringProperty Aid;
    private StringProperty levtype;
    private StringProperty levdetails;
    
    public Attendance(String Aid,String eatt, String dateA, String inA, String outA, String levtype, String levdetails ) {
        this.Aid = new SimpleStringProperty(Aid);
        this.eatt = new SimpleStringProperty(eatt);
        this.dateA = new SimpleStringProperty(dateA);
        this.inA    = new SimpleStringProperty(inA);
        this.outA = new SimpleStringProperty(outA);
        this.levtype = new SimpleStringProperty(levtype);
        this.levdetails = new SimpleStringProperty(levdetails);
        
    }
    public String getAid() {
        return Aid.get();
    }
    
     public String getEid() {
        return eatt.get();
    }

    public String getdate() {
        return dateA.get();
    }

    public String getin() {
        return inA.get();
    }

    public String getout() {
        return outA.get();
    }
     public String getlv() {
        return levtype.get();
    }
      public String getlvd() {
        return levdetails.get();
    }

   
    
    public void setAid(String value) {
        Aid.set(value);
    }
    
    public void seteatt(String value) {
        eatt.set(value);
    }

    public void setdateA(String value ) {
        dateA.set(value);
    }

    public void setinA(String value) {
        inA.set(value);
    }

    public void setoutA(String value) {
        outA.set(value);
    }

    public void setlevtype(String value) {
        levtype.set(value);
    }
    
    public void setlevdetails(String value) {
        levdetails.set(value);
    }

    
    
    public StringProperty AidProperty (){
        return Aid; 
    }
    
    public StringProperty eattProperty (){
        return eatt; 
    }
    
    public StringProperty dateAProperty (){
        return dateA; 
    }
    
    public StringProperty inAProperty (){
        return inA; 
    }
    
    public StringProperty outAProperty (){
        return outA; 
    }
    
    public StringProperty levtypeProperty (){
        return levtype; 
    }
    public StringProperty levdetailsProperty (){
        return levdetails; 
    }
    
    
}
