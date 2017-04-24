/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.intfc.validation.RegisterValidator;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.regx.RegX;
import com.vaydeal.member.req.mod.Register;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class RegisterConstraints implements RegisterValidator{
    
    private final Register req;
    private final DBConnect dbc;

    public RegisterConstraints(Register rp) throws Exception {
        this.req = rp;
        this.dbc = DB.getConnection();
    }
    
    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String name = req.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }
    
    @Override
    public String validateEmail() throws Exception {
        String valid = ErrMsg.ERR_EMAIL;
        String regX = RegX.REGX_EMAIL;
        String email = req.getEmail();
        if (validate(email, regX)) {
            if(dbc.checkRegEmail(email)){
                valid = CorrectMsg.CORRECT_EMAIL;
            }else{
                valid = ErrMsg.ERR_EMAIL_EXISTS;
            }
        }
        return valid;
    }
    
    @Override
    public String validateMobile() throws Exception {
        String valid = ErrMsg.ERR_MOBILE;
        String regX = RegX.REGX_MOBILE;
        String mobile = req.getMobile();
        if (validate(mobile, regX)) {
            if(dbc.checkRegMobile(mobile)){
                valid = CorrectMsg.CORRECT_MOBILE;
            }else{
                valid = ErrMsg.ERR_MOBILE_EXISTS;
            }
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
        dbc.closeConnection();
    }

}

