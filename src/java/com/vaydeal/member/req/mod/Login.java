/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.req.mod;

import com.vaydeal.member.hash.Hashing;
/**
 * @company techvay
 * @author rifaie
 */
public class Login {
    private final String uName;
    private String password;
    private String salt;
    private String member_id;
    private String member_type;
    private String update_status;
    
    public Login(String uName, String password) {
        this.uName = uName;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getuName() {
        return uName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_type() {
        return member_type;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }
    
    public void changePassword() throws Exception{
        password = Hashing.hashPassword(password.toCharArray(), salt.getBytes(), 24000, 256);
    }
}
