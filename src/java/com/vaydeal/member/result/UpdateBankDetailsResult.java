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
public class UpdateBankDetailsResult implements Result {
    private String at;
    private String pan;
    private String accno;
    private String bank;
    private String branch;
    private String ifsc;
    private String reqValidation;

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getReqValidation() {
        return reqValidation;
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
        if (at.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "at#";
        } else {
            if (pan.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "pan#";
            }
            if (accno.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "accno#";
            }
            if (bank.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "bank#";
            }
            if (branch.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "branch#";
            }
            if (ifsc.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ifsc#";
            }
        }
        return error.substring(0, error.length() - 1);
    }
}
