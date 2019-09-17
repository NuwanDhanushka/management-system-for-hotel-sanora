/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataList;

/**
 *
 * @author Nuwan
 */
public class BrandList {
    
 public String id;
    public String brandName;
    public String brandDescription;
    public String supplierName;
    public String creatorId;
    public String date;

    public BrandList(String id, String brandName, String brandDescription, String supplierName, String creatorId, String date) {
        this.id = id;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.supplierName = supplierName;
        this.creatorId = creatorId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplyerName) {
        this.supplierName = supplyerName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

