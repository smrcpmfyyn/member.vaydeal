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
public class ResetPassword {
    private final String token;
    private String member_id;
    private String toe;

    public ResetPassword(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getToe() {
        return toe;
    }
}
