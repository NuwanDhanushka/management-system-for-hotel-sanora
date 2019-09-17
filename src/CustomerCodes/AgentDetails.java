/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerCodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author THARINDU
 */
public class AgentDetails {
    private final StringProperty agentID;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty nic;
    private final StringProperty aLine2;
    private final StringProperty tel;
    private final StringProperty company;
    
    public AgentDetails(String agntID, String fname, String lname, String nic, String aLine2, String tel, String agntCompany) {
        this.agentID = new SimpleStringProperty(agntID);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.nic = new SimpleStringProperty(nic);
        this.aLine2 = new SimpleStringProperty(aLine2);
        this.tel = new SimpleStringProperty(tel);
        this.company = new SimpleStringProperty(agntCompany);
    }
    //Getters
    public String getagentID(){
        return agentID.get();
    }
    public String getfname(){
        return fname.get();
    }
    public String getlname(){
        return lname.get();
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
    public String getcustcompany(){
        return company.get();
    }
    
    //Property Values
    public StringProperty agentIDProperty(){
        return agentID;
    }
    public StringProperty fnameProperty(){
        return fname;
    }
    public StringProperty lnameProperty(){
        return lname;
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
    public StringProperty companyProperty(){
        return company;
    }    
}
