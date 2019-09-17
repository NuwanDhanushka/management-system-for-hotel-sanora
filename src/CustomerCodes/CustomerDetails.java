/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerCodes;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author THARINDU
 */
public class CustomerDetails {

    private final StringProperty custID;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty nic;
    private final StringProperty aLine2;
    private final StringProperty tel;
    private final StringProperty custType;
    private final FloatProperty custPoints;

    public CustomerDetails(String custID, String fname, String lname, String nic, String aLine2, String tel, String custType, Float custPoints) {
        this.custID = new SimpleStringProperty(custID);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.nic = new SimpleStringProperty(nic);
        this.aLine2 = new SimpleStringProperty(aLine2);
        this.tel = new SimpleStringProperty(tel);
        this.custType = new SimpleStringProperty(custType);
        this.custPoints = new SimpleFloatProperty(custPoints);
    }
    
    //Getters
    public String getcustID(){
        return custID.get();
    }
    public String getfname(){
        return fname.get();
    }
    public String getlname(){
        return lname.get();
    }
    public String getCustID(){
        return custID.get();
    }
    public String getnic(){
        return nic.get();
    }
    public String getaLine2(){
        return aLine2.get();
    }
    public String gettel(){
        return tel.get();
    }
    public String getcustType(){
        return custType.get();
    }
    public float getcustPoints(){
        return custPoints.get();
    }
    
    //Property Values
    public StringProperty custIDProperty(){
        return custID;
    }
    public StringProperty fnameProperty(){
        return fname;
    }
    public StringProperty lnameProperty(){
        return lname;
    }
    public StringProperty CustIDProperty(){
        return custID;
    }
    public StringProperty nicProperty(){
        return nic;
    }
    public StringProperty aLine2Property(){
        return aLine2;
    }
    public StringProperty telProperty(){
        return tel;
    }
    public StringProperty custTypeProperty(){
        return custType;
    }
    public FloatProperty custPointsProperty(){
        return custPoints;
    }
}
