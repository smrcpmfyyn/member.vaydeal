/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class LogSuccessResponse {
    private final String status;
    private final String accessToken;
    private String userType;
    private final String us;

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LogSuccessResponse(String status, String accessToken, String userType, String us) {
        this.status = status;
        this.accessToken = accessToken;
        this.userType = userType;
        this.us = us;
    }

    @Override
    public String toString() {
        if(userType.endsWith("associate")){
            userType = "associate";
        }
        String us1 = "1";
        if(us.equals("updated")){
            us1 = "2";
        }else if(us.startsWith("profile")){
            us1 = "3";
        }
        return "{\"status\":\"" + status  +"\",\"ut\":\""+userType+"\",\"us\":\""+us1+"\"}";
    }
    
}
