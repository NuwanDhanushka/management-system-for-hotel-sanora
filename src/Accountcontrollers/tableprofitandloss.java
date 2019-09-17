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
public class tableprofitandloss {
     private SimpleStringProperty description;
    private SimpleStringProperty profit;
    
    
     public tableprofitandloss (String description, String profit) {
        this.description = new SimpleStringProperty(description);
        this.profit = new SimpleStringProperty(profit);
       

    }
      public String getdescription() {
        return description.get();
    }

    public String getprofit() {
        return profit.get();
    }

    public void setdescription(String value) {
        description.set(value);
    }

    public void setprofit(String value) {
        profit.set(value);
    }
      public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty profitProperty() {
        return profit;
    }
}
