/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.intfc.validation.ChangePasswordValidator;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.regx.RegX;
import com.vaydeal.member.req.mod.ChangePassword;
import java.sql.SQLException;
import java.util.List;

/**
 * @company techvay
 * @author rifaie
 */
public class ChangePasswordConstraints implements ChangePasswordValidator {

    private final ChangePassword req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public ChangePasswordConstraints(ChangePassword req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateCurrentPassword() throws Exception {
        String valid = ErrMsg.ERR_PASSWORD;
        String regX = RegX.REGX_B64ENCODE;
        String uname = req.getMember_id();
        List<String> passDSalt = dbc.getPassDSalt(uname);
        req.setSalt(passDSalt.get(0));
        req.changeCurrentPassword();
        req.changeNewPassword();
        String apassword = passDSalt.get(1);
        String password = req.getCurrentPassword();
        String newPass = req.getNewPassword();
        if (validate(password, regX)) {
            if (apassword.equals(password)) {
                if (validate(newPass, regX)) {
                    valid = CorrectMsg.CORRECT_PASSWORD;
                }else{
                    valid = ErrMsg.ERR_NEW_PASSWORD;
                }
            }
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

