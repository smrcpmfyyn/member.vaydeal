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
        return "{\"mid\":\"" + mid + "\", \" mname\":\"" + mname + "\", \" madd1\":\"" + madd1 + "\", \" madd2\":\"" + madd2 + "\", \" mplace\":\"" + mplace + "\", \" mpin\":\"" + mpin + "\", \" mcity\":\"" + mcity + "\", \" memail\":\"" + memail + "\", \" mmobile\":\"" + mmobile + "\", \" mtype\":\"" + mtype + "\"}";
    }
    
    
}
