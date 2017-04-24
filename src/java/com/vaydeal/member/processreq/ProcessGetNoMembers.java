/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.processreq;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.intfc.processreq.GetNoMembersProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.resp.mod.GetNoMembersSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetNoMembers implements GetNoMembersProcessor{
    private final DBConnect dbc;
    private int noOfAffiliates;

    public ProcessGetNoMembers() throws Exception{
        this.dbc = DB.getConnection();
    }

    @Override
    public GetNoMembersSuccessResponse processRequest() throws Exception {
        GetNoMembersSuccessResponse response = null;
        getNoMembers();
        response = generateResponse(true);
        return response;
    }

    @Override
    public GetNoMembersSuccessResponse generateResponse(boolean status) {
        GetNoMembersSuccessResponse SResp = new GetNoMembersSuccessResponse(ResponseMsg.RESP_OK, noOfAffiliates);
        return SResp;
   }

    @Override
    public void getNoMembers() throws Exception {
        noOfAffiliates = dbc.getNoMembers();
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
    }
    
}

