/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.processreq;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.intfc.processreq.GetTotalCashoutProcessor;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.resp.mod.GetTotalCashoutSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetTotalCashout implements GetTotalCashoutProcessor{
    private final DBConnect dbc;
    private int totalCashout;

    public ProcessGetTotalCashout() throws Exception{
        this.dbc = DB.getConnection();
    }

    @Override
    public GetTotalCashoutSuccessResponse processRequest() throws Exception {
        GetTotalCashoutSuccessResponse response = null;
        getTotalCashout();
        response = generateResponse(true);
        return response;
    }

    @Override
    public GetTotalCashoutSuccessResponse generateResponse(boolean status) {
        GetTotalCashoutSuccessResponse SResp = new GetTotalCashoutSuccessResponse(ResponseMsg.RESP_OK, totalCashout);
        return SResp;
   }

    @Override
    public void getTotalCashout() throws Exception {
        totalCashout = dbc.getTotalCashout();
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
    }
    
}


