/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.processreq;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.intfc.processreq.RegisterProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.Register;
import com.vaydeal.member.resp.mod.RegisterSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessRegister implements RegisterProcessor {

    private final Register req;
    private final DBConnect dbc;

    public ProcessRegister(Register req) throws Exception {
        this.req = req;
        dbc = DB.getConnection();
    }

    @Override
    public boolean addRegistrationRequest() throws Exception {
        int c = dbc.addRegistrationRequest(req);
        return c==1;
    }

    @Override
    public RegisterSuccessResponse processRequest() throws Exception {
        RegisterSuccessResponse response = null;
        try {
            if (addRegistrationRequest()) {
                response = generateResponse(true);
            } else {
                response = generateResponse(false);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    @Override
    public RegisterSuccessResponse generateResponse(boolean status) {
        RegisterSuccessResponse resp;
        if (status) {
            resp = new RegisterSuccessResponse(ResponseMsg.RESP_OK);
        } else {
            resp = new RegisterSuccessResponse(ResponseMsg.RESP_NOT_OK);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
    }

}
