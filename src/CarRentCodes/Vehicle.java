package CarRentCodes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle {

    private StringProperty reg_no;
    private StringProperty make;
    private StringProperty model;
    private StringProperty type;
    private StringProperty year;
    private StringProperty transmission;
    private StringProperty availability;
    private StringProperty condition;
    private StringProperty colour;
    private StringProperty seats;
    private StringProperty day;
    private StringProperty eday;
    private StringProperty week;
    private StringProperty eweek;
    private StringProperty month;
    private StringProperty emonth;
    private StringProperty km;
    private StringProperty tank;
    private StringProperty fuel_ch;
    private StringProperty driver_ch;
    private StringProperty milage;
    
    public Vehicle(String reg_no,String make,String model,String type,String year,String transmission,String availability,String condition,String colour,String seats,String day,String eday,String week,String eweek,String month,String emonth,String km,String tank,String fuel_ch,String driver_ch,String milage) {
        this.reg_no = new SimpleStringProperty(reg_no);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.type = new SimpleStringProperty(type);
        this.year = new SimpleStringProperty(year);
        this.transmission = new SimpleStringProperty(transmission);
        this.availability = new SimpleStringProperty(availability);
        this.condition = new SimpleStringProperty(condition);
        this.colour = new SimpleStringProperty(colour);
        this.seats = new SimpleStringProperty(seats);
        this.day = new SimpleStringProperty(day);
        this.eday = new SimpleStringProperty(eday);
        this.week = new SimpleStringProperty(week);
        this.eweek = new SimpleStringProperty(eweek);
        this.month = new SimpleStringProperty(month);
        this.emonth = new SimpleStringProperty(emonth);
        this.km = new SimpleStringProperty(km);
        this.tank = new SimpleStringProperty(tank);
        this.fuel_ch = new SimpleStringProperty(fuel_ch);
        this.driver_ch= new SimpleStringProperty(driver_ch);
        this.milage = new SimpleStringProperty(milage);
        
      
        
    }
     Vehicle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getReg_no() {
        return reg_no.get();
    }

    public String getMake() {
        return make.get();
    }

    public String getModel() {
        return model.get();
    }

    public String getType() {
        return type.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getTransmission() {
        return transmission.get();
    }

    public String getAvailability() {
        return availability.get();
    }

    public String getCondition() {
        return condition.get();
    }

    public String getColour() {
        return colour.get();
    }

    public String getSeats() {
        return seats.get();
    }

    public String getday() {
        return day.get();
    }

    public String getEday() {
        return eday.get();
    }

    public String getWeek() {
        return week.get();
    }

    public String getEweek() {
        return eweek.get();
    }

    public String getMonth() {
        return month.get();
    }

    public String getEmonth() {
        return emonth.get();
    }

    public String getKm() {
        return km.get();
    
    }
    public String getTank() {
        return tank.get();
    }
     public String getFuel() {
        return fuel_ch.get();
    }
      public String getDriver() {
        return driver_ch.get();
    }
       public String getMilage() {
        return milage.get();
    }

    public void setReg_no(String value) {
        reg_no.set(value);
    }

    public void setMake(String value) {
        make.set(value);
    }

    public void setModel(String value) {
        model.set(value);
    }

    public void setType(String value) {
        type.set(value);
    }

    public void setYear(String value) {
        year.set(value);
    }

    public void setTransmission(String value) {
        transmission.set(value);
    }

    public void setAvailability(String value) {
        availability.set(value);
    }

    public void setCondition(String value) {
        condition.set(value);
    }

    public void setColour(String value) {
        colour.set(value);
    }

    public void setSeats(String value) {
        seats.set(value);
    }

    public void setDay(String value) {
        day.set(value);
    }

    public void setEday(String value) {
        eday.set(value);
    }

    public void setWeek(String value) {
        week.set(value);
    }

    public void setEweek(String value) {
        eweek.set(value);
    }

    public void setMonth(String value) {
        month.set(value);
    }

    public void setEmonth(String value) {
        emonth.set(value);
    }

    public void setKm(String value) {
        km.set(value);
    }
    public void setTank(String value) {
        tank.set(value);
    }
    public void setFuel(String value) {
        fuel_ch.set(value);
    }
    public void setDriver(String value) {
        driver_ch.set(value);
    }
    public void setMilage(String value) {
        milage.set(value);
    }
    
    public StringProperty reg_noProperty() {
        return reg_no;
    }

    public StringProperty makeProperty() {
        return make;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty yearProperty() {
        return year;
    }

    public StringProperty transmissionProperty() {
        return transmission;
    }

    public StringProperty availabilityProperty() {
        return availability;
    }

    public StringProperty conditionProperty() {
        return condition;
    }

    public StringProperty colourProperty() {
        return colour;
    }

    public StringProperty seatsProperty() {
        return seats;
    }

    public StringProperty dayProperty() {
        return day;
    }

    public StringProperty edayProperty() {
        return eday;
    }

    public StringProperty weekProperty() {
        return week;
    }

    public StringProperty eweekProperty() {
        return eweek;
    }

    public StringProperty monthProperty() {
        return month;
    }

    public StringProperty emonthProperty() {
        return emonth;
    }

    public StringProperty kmProperty() {
        return km;
    }
                 
    public StringProperty tankProperty() {
        return tank;
    }           
    public StringProperty fuel_chProperty(){
        
        return fuel_ch;
    }
    
    public StringProperty driver_chProperty() {
        return driver_ch;
    }          
     public StringProperty milageProperty() {
        return milage;
    }          
}
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


