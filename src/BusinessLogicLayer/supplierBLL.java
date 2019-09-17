/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.supplier;
import DataGateway.supplierDataGateway;

/**
 *
 * @author Nuwan
 */
public class supplierBLL {
        
    supplierDataGateway dataGateway = new supplierDataGateway();

   /* public void Add(){
        
    }*/
    
    public Object delete(supplier supplier){
        if(dataGateway.isNotUse(supplier)){
            dataGateway.delete(supplier);
        }else{
            
        }
        return supplier;
    }
}

