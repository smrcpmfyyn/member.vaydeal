/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.resp.mod;

import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.req.mod.Register;

/**
 * @company techvay
 * @author rifaie
 */
public class RegisterSuccessResponse {
    private final String status;
    private final Register req;

    public RegisterSuccessResponse(String status,Register req) {
        this.status = status;
        this.req = req;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (status.equals(ResponseMsg.RESP_OK)) {
            sb.append(
                    "            <div id=\"indexErr\">" + "<div id=\"msgStatus\" class=\"msg-status error\"> Promotion request posted Successfully. </div>" + "</div>\n"
                    + "            <div class=\"col-6\"></div>\n"
                    + "            <div class=\"col-6\" id=\"reqPCon\">"
                    + "<div class=\"form register-form\">\n"
                    + "                <h2>Request Promotion</h2>\n"
                    + "                <form onsubmit=\"return register()\">\n"
                    + "                    <label> Name</label>\n"
                    + "                    <input required id='name' type=\"text\" name=\"name\">\n"
                    + "                    <label> Email </label>\n"
                    + "                    <input type=\"email\" id=\"email\" required name=\"email\">\n"
                    + "                    <label> Mobile </label>\n"
                    + "                    <input type=\"text\" name=\"mobile\" id=\"mob\" pattern=\"[7-9]{1}[0-9]{9}\" required>\n"
                    + "                    <label></label>\n"
                    + "                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Register </button>\n"
                    + "                </form>\n"
                    + "            </div>\n"
                    + "       </div>");
        } else {
            sb.append("<div id=\"indexErr\"><div id=\"msgStatus\" class=\"msg-status error\">");
            sb.append("An error Occured! Please try again");
            sb.append("</div>"
                    + "         </div>\n"
                    + "            <div class=\"col-6\"></div>\n"
                    + "            <div class=\"col-6\" id=\"reqPCon\">"
                    + "              <div class=\"form register-form\">\n"
                    + "                <h2>Request Promotion</h2>\n"
                    + "                <label id=\"rpmsg\"></label>\n"
                    + "                <form onsubmit=\"return reqPromo()\">\n"
                    + "                    <label> Name</label>\n"
                    + "                    <input required id='name' type=\"text\" name=\"name\"  value='" + req.getName()+ "' >\n"
                    + "                    <label> Email </label>\n"
                    + "                    <input type=\"email\" id=\"email\"   value='" + req.getEmail() + "'required name=\"email\">\n"
                    + "                    <label> Mobile </label>\n"
                    + "                    <input type=\"text\" name=\"mobile\" id=\"mob\" pattern=\"[7-9]{1}[0-9]{9}\"   value='" + req.getMobile() + "' required>\n"
                    + "                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Request Promotion </button>\n"
                    + "                </form>\n"
                    + "            </div>\n"
                    + "       </div>\n");
        }

        return sb.toString();
    }
}


