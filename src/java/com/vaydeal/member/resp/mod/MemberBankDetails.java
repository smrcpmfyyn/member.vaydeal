/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class MemberBankDetails {
    private final String mid;
    private final String pan;
    private final String accno;
    private final String bank;
    private final String branch;
    private final String ifsc;

    public MemberBankDetails() {
        this.mid = "invalid";
        this.pan = "invalid";
        this.accno = "invalid";
        this.bank = "invalid";
        this.branch = "invalid";
        this.ifsc = "invalid";
    }

    public MemberBankDetails(String mid, String pan, String accno, String bank, String branch, String ifsc) {
        this.mid = mid;
        this.pan = pan;
        this.accno = accno;
        this.bank = bank;
        this.branch = branch;
        this.ifsc = ifsc;
    }
    
    public String getMid() {
        return mid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label> User ID </label>\n" +
"                                    <input id=\"mid\" type=\"text\" name=\"id\" value=\""+mid+"\" readonly>\n" +
"                                    <label> Pan Card Number </label>\n" +
"                                    <input id=\"mpan\" type=\"text\" name=\"pan\" value=\""+pan+"\">\n" +
"                                    <label> Account Number *</label>\n" +
"                                    <input id=\"macc\" type=\"text\" name=\"account\" value=\""+accno+"\" required>\n" +
"                                    <label> Bank *</label>\n" +
"                                    <input id=\"bank\" type=\"text\" name=\"bank\" value=\""+bank+"\" required>\n" +
"                                    <label> Branch *</label>\n" +
"                                    <input id=\"br\" type=\"text\" name=\"branch\" value=\""+branch+"\" required>\n" +
"                                    <label> IFSC *</label>\n" +
"                                    <input id=\"ifsc\" type=\"text\" name=\"ifsc\" value=\""+ifsc+"\" required>\n" +
"                                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Update </button>";
        res = res.replaceAll("null", "");
        sb.append(res);
        return sb.toString();
    }
    
    public String failiureToString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label onclick = location.reload(true);> Please reload </label>\n";
        sb.append(res);
        return sb.toString();
    }
}
