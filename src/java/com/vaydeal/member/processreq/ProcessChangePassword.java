/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.processreq;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.hash.Hashing;
import com.vaydeal.member.intfc.processreq.ChangePasswordProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.ChangePassword;
import com.vaydeal.member.resp.mod.ChangePasswordSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessChangePassword implements ChangePasswordProcessor{
    
    private final ChangePassword req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessChangePassword(ChangePassword gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getMember_id(), accessToken);
    }

    @Override
    public boolean changePassword() throws Exception {
        return dbc.changePassword(req);
    }

    @Override
    public ChangePasswordSuccessResponse processRequest() throws Exception {
        ChangePasswordSuccessResponse obj = null;
        if (generateToken()) {
            if (changePassword()) {
                obj = generateResponse(true);
            } else {
                obj = generateResponse(false);
            }
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public ChangePasswordSuccessResponse generateResponse(boolean status) {
        ChangePasswordSuccessResponse resp;
        if (status) {
            resp = new ChangePasswordSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new ChangePasswordSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}

