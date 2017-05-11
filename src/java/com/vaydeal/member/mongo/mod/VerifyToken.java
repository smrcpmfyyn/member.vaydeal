/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.mongo.mod;

import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class VerifyToken {
    private String member_id;
    private String toe;
    private String status;

    public VerifyToken() {
        this.status = "invalid";
        this.member_id = "invalid";
        this.toe = "invalid";
    }
    
    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getToe() {
        return toe;
    }

    public void setToe(String toe) {
        this.toe = toe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "VerifyToken{" + "user_id=" + member_id + ", toe=" + toe + ", status=" + status + '}';
    }

    public String getValidation() {
        String valid = ErrMsg.ERR_TOKEN;
        if(status.equals("verified")){
            valid = ErrMsg.ERR_TOKEN_USED;
        } else if (status.equals("invalid")) {
            valid = ErrMsg.ERR_TOKEN;
        }else if(Long.parseLong(toe)<System.currentTimeMillis()){
            valid = ErrMsg.ERR_TOKEN_EXPIRED;
        }else if(status.equals("not changed")&&Long.parseLong(toe)>System.currentTimeMillis()){
            valid = CorrectMsg.CORRECT_TOKEN;
        }
        return valid;
    }
}
