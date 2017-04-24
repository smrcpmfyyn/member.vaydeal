/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.intfc.validation.UpdateProfileValidator;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.regx.RegX;
import com.vaydeal.member.req.mod.UpdateProfile;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateProfileConstraints implements UpdateProfileValidator {

    private final UpdateProfile req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateProfileConstraints(UpdateProfile req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
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
    public String validateAddress1() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS1;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAddress1();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS1;
        }
        return valid;
    }

    @Override
    public String validateAddress2() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS2;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAddress2();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS2;
        }
        return valid;
    }

    @Override
    public String validatePin() throws Exception {
        String valid = ErrMsg.ERR_PIN;
        String regX = RegX.REGX_DIGIT;
        String param = req.getPin();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_PIN;
        }
        return valid;
    }

    @Override
    public String validatePlace() throws Exception {
        String valid = ErrMsg.ERR_PLACE;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String param = req.getPlace();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_PLACE;
        }
        return valid;
    }

    @Override
    public String validateCity() throws Exception {
        String valid = ErrMsg.ERR_CITY;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String param = req.getPlace();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_CITY;
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        MemberID mem = mdbc.getMemberID(at);
        if (!mem.getMember_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBMemberID(mem.getMember_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setMember_id(mem.getMember_id());
                req.setMember_type(mem.getMember_type());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
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
        mdbc.closeConnection();
    }
}


