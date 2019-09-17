/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataList;

/**
 *
 * @author Nuwan
 *  getters and setters
 */
public class productList {
    public String productId;
    public String productName;
    public String quantity;
    public String sellPrice;
    public String brand;
    public String suppliedBy;
    public String category;
    public String user;
    public String date;
    public String description;
    public String priority;
    public String minimumQuantityLevel;
    
    
    
    
  public productList(String productId,String productName,String quantity,String sellPrice,String brand,String suppliedBy,String category,String user,String date,String priority,String minimumQuantityLevel,String description){
      this.productId=productId;
      this.productName=productName;
      this.quantity=quantity;
      this.sellPrice=sellPrice;
      this.brand=brand;
      this.suppliedBy=suppliedBy;
      this.category=category;
      this.user=user;
      this.date=date;
      this.description=description;
      this.priority=priority;
      this.minimumQuantityLevel=minimumQuantityLevel;
  }
  
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

   public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
      public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
   public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getMinimumQuantityLevel(){
       return minimumQuantityLevel;
    }
    public void setMinimumQuantityLevel(String MinimumQuantityLevel){
        this.minimumQuantityLevel = MinimumQuantityLevel;
    } 
}
