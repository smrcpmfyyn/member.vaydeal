/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.processreq;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.email.MemberEmail;
import com.vaydeal.member.intfc.processreq.ForgotPasswordProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.ForgotPassword;
import com.vaydeal.member.resp.mod.ForgotPasswordSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessForgotPassword implements ForgotPasswordProcessor {

    private final ForgotPassword req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessForgotPassword(ForgotPassword gu) throws Exception {
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean resetAffiliateUser() throws Exception {
        boolean flag = false;
        if (dbc.changePassword(req)) {
            sendSetPasswordEmail(req.getEmail(), req.getPasswordToken(), req.getName());
            flag = true;
        }
        return flag;
    }

    @Override
    public ForgotPasswordSuccessResponse processRequest() throws Exception {
        ForgotPasswordSuccessResponse obj = null;
        if (resetAffiliateUser()) {
            if (mdbc.updateAUPasswordToken(req.getMid(), req.getPasswordToken())) {
                obj = generateResponse(true);
            }
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public ForgotPasswordSuccessResponse generateResponse(boolean status) {
        ForgotPasswordSuccessResponse resp;
        if (status) {
            resp = new ForgotPasswordSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new ForgotPasswordSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    private void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        MemberEmail.sendMemberResetPassword(email, passwordToken, name);
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}