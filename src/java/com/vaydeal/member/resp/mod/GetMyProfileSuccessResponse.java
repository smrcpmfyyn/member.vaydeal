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
public class GetMyProfileSuccessResponse {
    private final String status;
    private final String accessToken;
    private MyProfile myProfile;

    public GetMyProfileSuccessResponse(String status, String accessToken, MyProfile myProfile) {
        this.status = status;
        this.accessToken = accessToken;
        this.myProfile = myProfile;
    }

    public GetMyProfileSuccessResponse(String status, String accessToken) {
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
            response = "{\"status\":\"" + status + "\", \"mp\":" + myProfile + "}";
        }else{
            response = "{\"status\":\""+status+"\"}";
        }
        return response;
    }
}

