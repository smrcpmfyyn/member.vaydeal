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
import com.vaydeal.member.intfc.processreq.GetMemberBankDetailsProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.GetMemberBankDetails;
import com.vaydeal.member.resp.mod.GetMemberBankDetailsSuccessResponse;
import com.vaydeal.member.resp.mod.MemberBankDetails;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetMemberBankDetails implements GetMemberBankDetailsProcessor{
    
    private final GetMemberBankDetails req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private MemberBankDetails bankDetails;

    public ProcessGetMemberBankDetails(GetMemberBankDetails gu) throws Exception{
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
    public boolean getMemberBankDetails() throws Exception {
        bankDetails = dbc.getMemberBankDetails(req.getMember_id());
        return !bankDetails.getMid().equals("invalid");
    }

    @Override
    public GetMemberBankDetailsSuccessResponse processRequest() throws Exception {
        GetMemberBankDetailsSuccessResponse obj = null;
        if (generateToken()) {
            if (getMemberBankDetails()) {
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
    public GetMemberBankDetailsSuccessResponse generateResponse(boolean status) {
        GetMemberBankDetailsSuccessResponse resp;
        if (status) {
            resp = new GetMemberBankDetailsSuccessResponse(ResponseMsg.RESP_OK, accessToken, bankDetails);
        } else {
            resp = new GetMemberBankDetailsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
    
}



