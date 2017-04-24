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
import com.vaydeal.member.intfc.processreq.GetMemberReportProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.GetMemberReport;
import com.vaydeal.member.resp.mod.GetMemberReportSuccessResponse;
import com.vaydeal.member.resp.mod.MemberReport;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetMemberReport implements GetMemberReportProcessor{
    
    private final GetMemberReport req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private MemberReport memReport;

    public ProcessGetMemberReport(GetMemberReport gu) throws Exception{
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
    public boolean getMyReport() throws Exception {
        memReport = dbc.getMemberReport(req.getMember_id());
        return !memReport.getMid().equals("invalid");
    }

    @Override
    public GetMemberReportSuccessResponse processRequest() throws Exception {
        GetMemberReportSuccessResponse obj = null;
        if (generateToken()) {
            if (getMyReport()) {
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
    public GetMemberReportSuccessResponse generateResponse(boolean status) {
        GetMemberReportSuccessResponse resp;
        if (status) {
            resp = new GetMemberReportSuccessResponse(ResponseMsg.RESP_OK, accessToken, memReport);
        } else {
            resp = new GetMemberReportSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
    
}

