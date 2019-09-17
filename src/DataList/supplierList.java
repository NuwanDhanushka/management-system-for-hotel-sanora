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
public class supplierList {
    
    public String supplierId;
    public String supplierName;
    public String supplierPhoneNumber;
    public String supplierAddress;
    public String supplierDescription;
    public String creatorId;
    public String dataOfjoining;

    public supplierList(String supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }



    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public String getDataOfjoining() {
        return dataOfjoining;
    }

    public supplierList(String supplierId, String supplierName, String supplierPhoneNumber, String supplierAddress, String supplierDescription, String dataOfjoining) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
        this.supplierAddress = supplierAddress;
        this.supplierDescription = supplierDescription;
        this.dataOfjoining = dataOfjoining;
    }





    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

}
