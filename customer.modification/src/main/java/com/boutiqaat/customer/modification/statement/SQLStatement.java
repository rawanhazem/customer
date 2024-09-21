/************************************************************
 * Copyright ©2015-2024 Boutiqaat. All rights reserved
 * —————————————————————————————————
 * NOTICE: All information contained herein is a property of Boutiqaat.
 *************************************************************/
package com.boutiqaat.customer.modification.statement;

/**
 * @author r.elamin
 */


public interface SQLStatement {
    
    String sqlForGetCustomerInfo = "SELECT ce.entity_id, ce.email, ce.firstname, ce.middlename, ce.lastname, cev.value " +
            "FROM customer_entity ce Inner Join customer_entity_varchar cev on ce.entity_id = cev.entity_id " +
            "where cev.attribute_id = 311 and ce.entity_id in (234020,234502,234616,234618); ";
    
    String sqlForUpdateCustomerInfo = "UPDATE customer_entity Set firstname = ?, middlename = '.', lastname = ?, " +
            "email = ? where entity_id = ?";
    
    String sqlForUpdatePhoneNumber = "UPDATE customer_entity_varchar Set value = ? where attribute_id = 311 and " +
            "entity_id = ?";
    
    String sqlForUpdateCustomerAddressEntity = "UPDATE customer_address_entity Set firstname = ?, middlename = '.', " +
            "lastname = ?, telephone = ? where parent   _id = ?";
     
}