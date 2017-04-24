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
public class RegisterResult implements Result{
    
    private String name;
    private String email;
    private String mobile;
    private String reqValidation;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setReqValidation(String reqValidation) {
        this.reqValidation = reqValidation;
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
        if (name.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "name#";
        }
        if (email.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "email#";
        }
        if (mobile.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "mobile#";
        }
        return error.substring(0, error.length() - 1);
    }

}

