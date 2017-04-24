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
        return "{\"mid\":\"" + mid + "\",\" pan\":\"" + pan + "\",\" accno\":\"" + accno + "\",\" bank\":\"" + bank + "\",\" branch\":\"" + branch + "\",\" ifsc\":\"" + ifsc + '}';
    }
}
