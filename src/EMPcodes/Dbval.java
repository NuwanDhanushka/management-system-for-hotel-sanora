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
public class Dbval {
    private StringProperty E_id;
    private StringProperty E_name;
    private StringProperty E_dip;
    private StringProperty E_nic;
    private StringProperty E_desi;
    private StringProperty E_gender;
    private StringProperty E_no;

    public Dbval(String E_id, String E_name, String E_dip, String E_nic, String E_desi, String E_gender,String E_no) {
        this.E_id = new SimpleStringProperty(E_id);
        this.E_name = new SimpleStringProperty(E_name);
        this.E_dip    = new SimpleStringProperty(E_dip);
        this.E_nic = new SimpleStringProperty(E_nic);
        this.E_desi = new SimpleStringProperty(E_desi);
        this.E_gender = new SimpleStringProperty(E_gender);
        this.E_no = new SimpleStringProperty(E_no);
    }

   

    

    public String getE_id() {
        return E_id.get();
    }

    public String getE_name() {
        return E_name.get();
    }

    public String getE_dip() {
        return E_dip.get();
    }

    public String getE_nic() {
        return E_nic.get();
    }

    public String getE_desi() {
        return E_desi.get();
    }

    public String getE_gender() {
        return E_gender.get();
    }
    public String getE_no() {
        return E_no.get();
    }
    
    
    public void setE_id(String value) {
        E_id.set(value);
    }

    public void setE_name(String value ) {
        E_name.set(value);
    }

    public void setE_dip(String value) {
        E_dip.set(value);
    }

    public void setE_desi(String value) {
        E_desi.set(value);
    }

    public void setE_gender(String value) {
       E_gender.set(value);
    }

    public void setE_no(String value) {
        E_no.set(value);
    }
    public void setE_nic(String value) {
        E_nic.set(value);
    }
    
    
    public StringProperty E_idProperty (){
        return E_id; 
    }
    
    public StringProperty E_nameProperty (){
        return E_name; 
    }
    
    public StringProperty E_dipProperty (){
        return E_dip; 
    }
    
    public StringProperty E_desiProperty (){
        return E_desi; 
    }
    
    public StringProperty E_genderProperty (){
        return E_gender;
    }
    
    public StringProperty E_noProperty (){
        return E_no;
    }
    
    public StringProperty E_nicProperty (){
        return E_nic;
    }
}
    

