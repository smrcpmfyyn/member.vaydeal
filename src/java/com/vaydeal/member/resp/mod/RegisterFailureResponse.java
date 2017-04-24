/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.resp.mod;

import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.result.RegisterResult;

/**
 * @company techvay
 * @author rifaie
 */
public class RegisterFailureResponse {

    private final RegisterResult reqR;
    private final String error;

    public RegisterFailureResponse(RegisterResult reqR, String error) {
        this.reqR = reqR;
        this.error = error;
    }

    @Override
    public String toString() {
        String json = "\"status\":\""+ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        String resp;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[1];
            switch (parameter) {
                case "name":
                    String name = reqR.getName();
                    resp = name.substring(name.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "email":
                    String email = reqR.getEmail();
                    resp = email.substring(email.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "mobile":
                    String mobile = reqR.getMobile();
                    resp = mobile.substring(mobile.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }

}

