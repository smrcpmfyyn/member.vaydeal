/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.intfc.validation.GetMemberBankDetailsValidator;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.req.mod.GetMemberBankDetails;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class GetMemberBankDetailsConstraints implements GetMemberBankDetailsValidator {

    private final GetMemberBankDetails req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public GetMemberBankDetailsConstraints(GetMemberBankDetails req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
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
