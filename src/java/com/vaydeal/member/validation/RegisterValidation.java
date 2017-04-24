/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.intfc.validation.Validation;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.req.mod.Register;

/**
 * @company techvay
 * @author rifaie
 */
public class RegisterValidation implements Validation{
    
    private final Register req;
    private String paramValue = "";
    private String paramName = "";

    public RegisterValidation(Register ve) {
        this.req = ve;
    }

    @Override
    public void validation() throws Exception {
        RegisterConstraints reqc = new RegisterConstraints(req);
        String valid = "";
        valid += reqc.validateName();
        valid += "#"+reqc.validateEmail();
        valid += "#"+reqc.validateMobile();
        reqc.closeConnection();
        int count = 0;
        for (String str : valid.split("#")) {
            paramName += str.split(" ")[1].toLowerCase() + "#";
            if (!str.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                count++;
                paramValue += str + "#";
            } else {
                paramValue += CorrectMsg.CORRECT_MESSAGE + "#";
            }
        }
        paramName += "reqValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_REQUEST_PROMOTION;
        } else {
            paramValue += ErrMsg.ERR_REQUEST_PROMOTION;;
        }
    }
    
    @Override
    public String toString() {
        String[] paramsN = paramName.split("#");
        String[] paramV = paramValue.split("#");
        String json = "";
        for (int i = 0; i < paramsN.length; i++) {
            json += "\"" + paramsN[i] + "\"" + ":" + "\"" + paramV[i] + "\" ,";
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
    

}
