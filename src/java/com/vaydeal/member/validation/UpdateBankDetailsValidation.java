/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.validation;

import com.vaydeal.member.intfc.validation.Validation;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.req.mod.UpdateBankDetails;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateBankDetailsValidation implements Validation {

    private final UpdateBankDetails req;
    private String paramValue = "";
    private String paramName = "";

    public UpdateBankDetailsValidation(UpdateBankDetails gu) {
        this.req = gu;
    }

    @Override
    public void validation() throws Exception {
        UpdateBankDetailsConstraints reqC = new UpdateBankDetailsConstraints(req);
        String valid = "";
        valid += reqC.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            valid += "#" + reqC.validatePan();
            valid += "#" + reqC.validateAccountNo();
            valid += "#" + reqC.validateBank();
            valid += "#" + reqC.validateBranch();
            valid += "#" + reqC.validateIfsc();
        }
        reqC.closeConnection();
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
            paramValue += CorrectMsg.CORRECT_GET_PAYMENTS;
        } else {
            paramValue += ErrMsg.ERR_GET_PAYMENTS;
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

