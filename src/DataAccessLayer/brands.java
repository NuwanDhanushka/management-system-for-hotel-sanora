/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataList.BrandList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nuwan
 */
public class brands {
    
    public String id;
    public String brandName;
    public String brandDescription;
    public String supplierId;
    public String creatorId;
    public String date;
    public String supplierName;
    public String creatorName;

    public ObservableList<BrandList> brandList = FXCollections.observableArrayList();
}
