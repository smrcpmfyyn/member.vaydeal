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
public class GetMemberBankDetailsSuccessResponse {
    private final String status;
    private final String accessToken;
    private MemberBankDetails bankDeatails;

    public GetMemberBankDetailsSuccessResponse(String status, String accessToken, MemberBankDetails myProfile) {
        this.status = status;
        this.accessToken = accessToken;
        this.bankDeatails = myProfile;
    }

    public GetMemberBankDetailsSuccessResponse(String status, String accessToken) {
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
            response = "{\"status\":\"" + status + "\"\",\" \"bd\":" + bankDeatails + "}";
        }else{
            response = "{\"status\":\""+status+"\"}";
        }
        return response;
    }
}

