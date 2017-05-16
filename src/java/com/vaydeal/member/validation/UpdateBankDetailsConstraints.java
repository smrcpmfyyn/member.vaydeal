/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.intfc.validation.UpdateBankDetailsValidator;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.regx.RegX;
import com.vaydeal.member.req.mod.UpdateBankDetails;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateBankDetailsConstraints implements UpdateBankDetailsValidator {

    private final UpdateBankDetails req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateBankDetailsConstraints(UpdateBankDetails req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validatePan() throws Exception {
        String valid = ErrMsg.ERR_PAN;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER_PAN;
        String name = req.getPan();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_PAN;
        }
        return valid;
    }

    @Override
    public String validateAccountNo() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS1;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAccountNo();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ACCOUNT_NO;
        }
        return valid;
    }

    @Override
    public String validateBank() throws Exception {
        String valid = ErrMsg.ERR_BANK;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getBank();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_BANK;
        }
        return valid;
    }

    @Override
    public String validateBranch() throws Exception {
        String valid = ErrMsg.ERR_BRANCH;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String param = req.getBranch();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_BRANCH;
        }
        return valid;
    }

    @Override
    public String validateIfsc() throws Exception {
        String valid = ErrMsg.ERR_IFSC;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getIfsc();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_IFSC;
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        MemberID mem = mdbc.getMemberID(at);
        if (!mem.getMember_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            mem.setMember_update_status(dbc.getMemberUpdateStatus(mem.getMember_id()));
            if (dbc.checkNBMemberID(mem.getMember_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setMember_id(mem.getMember_id());
                req.setMember_type(mem.getMember_type());
                req.setUpdateStatus(mem.getMember_update_status());
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

    @Override
    public String validatePage() throws Exception {
        String param = req.getPage();
        String valid = ErrMsg.ERR_PAGE;
        if (param.equals("pu") && req.getUpdateStatus().startsWith("update")) {
            valid = CorrectMsg.CORRECT_PAGE;
        } else if (param.equals("ad") && req.getUpdateStatus().startsWith("profile")) {
            valid = CorrectMsg.CORRECT_PAGE;
        }
        return valid;
    }
}
