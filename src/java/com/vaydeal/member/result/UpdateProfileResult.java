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
public class UpdateProfileResult implements Result {
    private String at;
    private String name;
    private String add1;
    private String add2;
    private String pin;
    private String place;
    private String city;
    private String reqValidation;

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
            if (name.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "name#";
            }
            if (add1.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "add1#";
            }
            if (add2.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "add2#";
            }
            if (pin.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "pin#";
            }
            if (place.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "place#";
            }
            if (city.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "city#";
            }
        }
        return error.substring(0, error.length() - 1);
    }
}
