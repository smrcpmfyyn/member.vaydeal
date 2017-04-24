/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.req.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateBankDetails {
    private final String at;
    private String member_id;
    private String member_type;
    private final String accountNo;
    private final String bank;
    private final String branch;
    private final String ifsc;
    private final String pan;

    public UpdateBankDetails(String at, String pan, String accountNo, String bank, String branch, String ifsc) {
        this.at = at;
        this.accountNo = accountNo;
        this.bank = bank;
        this.branch = branch;
        this.ifsc = ifsc;
        this.pan = pan;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getAt() {
        return at;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_type() {
        return member_type;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getBank() {
        return bank;
    }

    public String getBranch() {
        return branch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getPan() {
        return pan;
    }

    
}

