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
public class GetMemberBankDetails {
    private final String at;
    private String member_id;
    private String member_type;

    public GetMemberBankDetails(String at) {
        this.at = at;
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
}

