/************************************************************
 * Copyright ©2015-2024 Boutiqaat. All rights reserved
 * —————————————————————————————————
 * NOTICE: All information contained herein is a property of Boutiqaat.
 *************************************************************/
package com.boutiqaat.customer.modification.repository;

import com.boutiqaat.customer.modification.model.CustomerInfoModel;
import com.boutiqaat.customer.modification.statement.SQLStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * @author r.elamin
 */

@Repository
public class CustomerEntityRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<CustomerInfoModel> findAll() {
        return jdbcTemplate.query(SQLStatement.sqlForGetCustomerInfo,
                (rs, rowNum) -> new CustomerInfoModel(
                        rs.getInt("entity_id"),
                        rs.getString("email") == null ? "" : rs.getString("email"),
                        rs.getString("firstname") == null ? "" : rs.getString("firstname"),
                        rs.getString("middlename") == null ? "" : rs.getString("middlename"),
                        rs.getString("lastname") == null ? "" : rs.getString("lastname"),
                        rs.getString("value")
                ));
    }
    
    private Integer generateRandom() {
        long timeStamp = System.currentTimeMillis();
        Random random = new Random(timeStamp);
        return random.nextInt();
    }
    
    
    public String updateAll(){
        var customerEntities = findAll();
        customerEntities.forEach(entity -> {
            String firstName = "test".concat(generateRandom().toString());
            String lastName = "test".concat(String.valueOf((generateRandom() + 1)));
            String email = firstName.concat("_").concat(lastName).concat("@btqTest.com");
            String phoneNumber = "99887766";
            jdbcTemplate.update(SQLStatement.sqlForUpdateCustomerInfo, firstName, lastName, email,
                    entity.getEntityId());
            jdbcTemplate.update(SQLStatement.sqlForUpdatePhoneNumber, phoneNumber, entity.getEntityId());
            jdbcTemplate.update(SQLStatement.sqlForUpdateCustomerAddressEntity,firstName,lastName,phoneNumber,
                    entity.getEntityId());
        });
        return "Success";
    }
}