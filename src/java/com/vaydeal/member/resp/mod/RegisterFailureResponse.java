/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.resp.mod;

import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.Register;
import com.vaydeal.member.result.RegisterResult;

/**
 * @company techvay
 * @author rifaie
 */
public class RegisterFailureResponse {

    private final RegisterResult reqR;
    private final String error;
    private final Register req;

    public RegisterFailureResponse(RegisterResult reqR, String error,Register req) {
        this.reqR = reqR;
        this.error = error;
        this.req = req;
    }

    @Override
    public String toString() {
        String[] errors = error.split("#");
        String err = "";
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[i];
            switch (parameter) {
                case "name":
                    err += "Invalid Name\n";
                    break;
                case "email":
                    err += "Invalid Email\n";
                    break;
                case "mobile":
                    err += "Invalid Mobile Number";
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"indexErr\"><div id=\"msgStatus\" class=\"msg-status error\">");
        sb.append(err);
        sb.append("</div>"+
"         </div>\n" +
"            <div class=\"col-6\"></div>\n" +
"            <div class=\"col-6\" id=\"reqPCon\">"+
"              <div class=\"form register-form\">\n" +
"                <h2>Request Promotion</h2>\n" +
"                <label id=\"rpmsg\"></label>\n" +
"                <form onsubmit=\"return reqPromo()\">\n" +
"                    <label> Name </label>\n" +
"                    <input type=\"text\" id=\"name\"  value='"+req.getName()+"' required name=\"confoer\">\n" +
"                    <label> Email </label>\n" +
"                    <input type=\"email\" id=\"email\"   value='"+req.getEmail()+"'required name=\"email\">\n" +
"                    <label> Mobile </label>\n" +
"                    <input type=\"text\" name=\"mobile\" id=\"mob\" pattern=\"[7-9]{1}[0-9]{9}\"   value='"+req.getMobile()+"' required>\n" +
"                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Request Promotion </button>\n" +
"                </form>\n" +
"            </div>\n" +
"       </div>\n");
        
        return sb.toString();
    }

}

