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
public class MemberReport {
    private String mid;
    // activity report
    private String pu;
    private String pa;
    private String pp;
    private String pr;
    private String pmu;
    private String pup;
    private String pupa;
    private String pupp;
    private String pupr;
    private String pdup;
    //payment report
    private String ap;
    private String ar;
    private String lr;
    private String lrd;

    public MemberReport() {
        pa = "invalid";
        pdup = "invalid";
        pmu = "invalid";
        pp = "invalid";
        pr = "invalid";
        pu = "invalid";
        pup = "invalid";
        pupa = "invalid";
        pupp = "invalid";
        pupr = "invalid";
        ap = "invalid";
        ar = "invalid";
        lr = "invalid";
        lrd = "invalid";
        mid = "invalid";
    }

    public MemberReport(String mid, String pu, String pa, String pp, String pr, String pmu, String pup, String pupa, String pupp, String pupr, String pdup, String ap, String ar, String lr, String lrd) {
        this.mid = mid;
        this.pu = pu;
        this.pa = pa;
        this.pp = pp;
        this.pr = pr;
        this.pmu = pmu;
        this.pup = pup;
        this.pupa = pupa;
        this.pupp = pupp;
        this.pupr = pupr;
        this.pdup = pdup;
        this.ap = ap;
        this.ar = ar;
        this.lr = lr;
        this.lrd = lrd;
    }

    public String getMid() {
        return mid;
    }

    @Override
    public String toString() {
        String memberDetails = "\"activities\":{\"pmu\":\"" + pmu + "\", \"pp\":\"" + pp + "\", \"pu\":\"" + pu + "\", \"pa\":\"" + pa + "\", \"pr\":\"" + pr + "\", \"pdup\":\"" + pdup + "\", \"pup\":\"" + pup + "\", \"pupa\":\"" + pupa + "\", \"pupp\":\"" + pupp + "\", \"pupr\":\"" + pupr + "\"},";
        memberDetails += "\"payment\":{\"ap\":\"" + ap + "\", \"ar\":\"" + ar + "\", \"lr\":\"" + lr + "\", \"lrd\":\"" + lrd + "\"}";
        return memberDetails;
    }
    
    
}
