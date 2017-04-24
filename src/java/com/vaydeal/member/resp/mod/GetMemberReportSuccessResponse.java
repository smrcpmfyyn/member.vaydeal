/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.resp.mod;

import com.vaydeal.member.message.ResponseMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class GetMemberReportSuccessResponse {
    private final String status;
    private final String accessToken;
    private MemberReport memReport;

    public GetMemberReportSuccessResponse(String status, String accessToken, MemberReport memReport) {
        this.status = status;
        this.accessToken = accessToken;
        this.memReport = memReport;
    }

    public GetMemberReportSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "";
        if(status.equals(ResponseMsg.RESP_OK)){
            response = "{\"status\":\"" + status + "\", \"ap\":" + memReport + "}";
        }else{
            response = "{\"status\":\""+status+"\"}";
        }
        return response;
    }
}
