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
import com.vaydeal.member.intfc.processreq.GetMyProfileProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.GetMyProfile;
import com.vaydeal.member.resp.mod.GetMyProfileSuccessResponse;
import com.vaydeal.member.resp.mod.MyProfile;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetMyProfile implements GetMyProfileProcessor{
    
    private final GetMyProfile req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private MyProfile myProfile;

    public ProcessGetMyProfile(GetMyProfile gu) throws Exception{
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
    public boolean getMyProfile() throws Exception {
        myProfile = dbc.getMyProfile(req.getMember_id());
        return !myProfile.getMid().equals("invalid");
    }

    @Override
    public GetMyProfileSuccessResponse processRequest() throws Exception {
        GetMyProfileSuccessResponse obj = null;
        if (generateToken()) {
            if (getMyProfile()) {
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
    public GetMyProfileSuccessResponse generateResponse(boolean status) {
        GetMyProfileSuccessResponse resp;
        if (status) {
            resp = new GetMyProfileSuccessResponse(ResponseMsg.RESP_OK, accessToken, myProfile);
        } else {
            resp = new GetMyProfileSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
    
}


