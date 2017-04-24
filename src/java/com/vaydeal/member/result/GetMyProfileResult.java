/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.result;

import com.vaydeal.member.intfc.vres.Result;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.message.ValidationMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class GetMyProfileResult implements Result {
    private String at;
    private String reqValidation;

    public void setAt(String at) {
        this.at = at;
    }

    public void setReqValidation(String reqValidation) {
        this.reqValidation = reqValidation;
    }

    public String getAt() {
        return at;
    }

    public String getReqValidation() {
        return reqValidation;
    }
    
    @Override
    public String getValidationResult() {
        String result;
        if (isRequestValid()) {
            result = ValidationMsg.VALID;
        } else {
            result = getAllErrors();
        }
        return result;
    }

    @Override
    public boolean isRequestValid() {
        boolean flag = false;
        if (reqValidation.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getAllErrors() {
        String error = ErrMsg.ERR_ERR + "#";
        if (at.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "at#";
        } 
        return error.substring(0, error.length() - 1);
    }
}


