/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.mongo.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class MemberID {
    private String member_id;
    private String member_type;
    private String member_update_status;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getMember_update_status() {
        return member_update_status;
    }

    public void setMember_update_status(String member_update_status) {
        this.member_update_status = member_update_status;
    }
}
