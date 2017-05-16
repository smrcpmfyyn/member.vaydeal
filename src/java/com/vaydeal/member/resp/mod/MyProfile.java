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
public class MyProfile {
    private final String mid;
    private final String mname;
    private final String madd1;
    private final String madd2;
    private final String mplace;
    private final String mpin;
    private final String mcity;
    private final String memail;
    private final String mmobile;
    private final String mtype;

    public MyProfile(String mid, String mname, String madd1, String madd2, String mplace, String mpin, String mcity, String memail, String mmobile, String mtype) {
        this.mid = mid;
        this.mname = mname;
        this.madd1 = madd1;
        this.madd2 = madd2;
        this.mplace = mplace;
        this.mpin = mpin;
        this.mcity = mcity;
        this.memail = memail;
        this.mmobile = mmobile;
        this.mtype = mtype;
    }

    public MyProfile() {
        this.mid = "invalid";
        this.mname = "invalid";
        this.madd1 = "invalid";
        this.madd2 = "invalid";
        this.mplace = "invalid";
        this.mpin = "invalid";
        this.mcity = "invalid";
        this.memail = "invalid";
        this.mmobile = "invalid";
        this.mtype = "invalid";
    }
    
    public String getMid() {
        return mid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label> User ID </label>\n" +
"                                        <input id=\"mid\" type=\"text\" name=\"userid\" value=\""+mid+"\" readonly>\n" +
"                                        <label> Member Type </label>\n" +
"                                        <input id=\"mtype\" type=\"text\" name=\"type\" value=\""+mtype+"\" readonly>\n" +
"                                        <label> Name *</label>\n" +
"                                        <input id=\"mn\" type=\"text\" name=\"name\" value=\""+mname+"\" required>\n" +
"                                        <label> Address 1 *</label>\n" +
"                                        <input id=\"madd1\" type=\"text\" name=\"address1\" value=\""+madd1+"\" required>\n" +
"                                        <label> Address 2 </label>\n" +
"                                        <input id=\"madd2\" type=\"text\" name=\"address2\" value=\""+madd2+"\">\n" +
"                                        <label> Place *</label>\n" +
"                                        <input id=\"mpl\" type=\"text\" name=\"place\" value=\""+mplace+"\" required>\n" +
"                                        <label> Pin *</label>\n" +
"                                        <input id=\"mpin\" type=\"text\" name=\"pin\" value=\""+mpin+"\" required>\n" +
"                                        <label> City *</label>\n" +
"                                        <input id=\"mcity\" type=\"text\" name=\"city\" value=\""+mcity+"\" required>\n" +
"                                        <label> Email </label>\n" +
"                                        <input id=\"memail\" type=\"email\" name=\"email\" value=\""+memail+"\" readonly>\n" +
"                                        <label> Mobile </label>\n" +
"                                        <input id=\"mmob\" type=\"text\" name=\"mobile\" value=\""+mmobile+"\" readonly>\n" +
"                                        <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Update </button>";
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
