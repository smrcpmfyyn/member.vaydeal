/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.intfc.validation.RPValidator;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.mongo.mod.VerifyToken;
import com.vaydeal.member.regx.RegX;
import com.vaydeal.member.req.mod.ResetPassword;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class RPConstraints implements RPValidator{
    
    private final ResetPassword req;
    private final MongoConnect mdbc;

    public RPConstraints(ResetPassword rp) throws Exception {
        this.req = rp;
        mdbc = DB.getMongoConnection();
    }

    @Override
    public String validateToken() throws Exception {
        String valid = ErrMsg.ERR_TOKEN;
        String regX = RegX.REGX_TOKEN;
        String token = req.getToken();
        VerifyToken vt = mdbc.verifyToken(token);
        if (validate(token, regX)) {
            valid = vt.getValidation();
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        mdbc.closeConnection();
    }

}

