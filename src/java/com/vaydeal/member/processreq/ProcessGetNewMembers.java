/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.processreq;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.intfc.processreq.GetNewMembersProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.resp.mod.GetNewMembersSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetNewMembers implements GetNewMembersProcessor{
    private final DBConnect dbc;
    private int newMembers;

    public ProcessGetNewMembers() throws Exception{
        this.dbc = DB.getConnection();
    }

    @Override
    public GetNewMembersSuccessResponse processRequest() throws Exception {
        GetNewMembersSuccessResponse response = null;
        getNewMembers();
        response = generateResponse(true);
        return response;
    }

    @Override
    public GetNewMembersSuccessResponse generateResponse(boolean status) {
        GetNewMembersSuccessResponse SResp = new GetNewMembersSuccessResponse(ResponseMsg.RESP_OK, newMembers);
        return SResp;
   }

    @Override
    public void getNewMembers() throws Exception {
        newMembers = dbc.getNewMembers();
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
    }
    
}


