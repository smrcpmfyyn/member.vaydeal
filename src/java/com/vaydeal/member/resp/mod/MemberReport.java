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
        StringBuilder sb = new StringBuilder();
        sb.append("<li><span class=\"reports-list-lable\"> Member ID</span> <span class=\"reports-list-value\"> " + mid + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Uploaded</span> <span class=\"reports-list-value\"> " + pu + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Approved</span> <span class=\"reports-list-value\"> " + pa + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Pending</span> <span class=\"reports-list-value\"> " + pp + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Rejected</span> <span class=\"reports-list-value\"> " + pr + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Uploaded this month</span> <span class=\"reports-list-value\"> " + pmu + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Updated</span> <span class=\"reports-list-value\"> " + pup + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Update Approved</span> <span class=\"reports-list-value\"> " + pupa + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Update Pending</span> <span class=\"reports-list-value\"> " + pupp + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Update Rejected</span> <span class=\"reports-list-value\"> " + pupr + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> No of Product Update Today</span> <span class=\"reports-list-value\"> " + pdup + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> Total Amount Pending</span> <span class=\"reports-list-value\"> " + ap + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> Total Amount Received</span> <span class=\"reports-list-value\"> " + ar + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> Last Received Amount</span> <span class=\"reports-list-value\"> " + lr + " </span> </li>\n"
                + "                            <li><span class=\"reports-list-lable\"> Last Received Date</span> <span class=\"reports-list-value\"> " + lrd + " </span> </li>");
        return sb.toString();
    }
    
    public String failiureToString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label onclick = location.reload(true);> Please reload </label>\n";
        sb.append(res);
        return sb.toString();
    }

}
