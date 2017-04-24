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
public class ChangePassword {

    private final String at;
    private String member_id;
    private String member_type;
    private String currentPassword;
    private String salt;
    private String newPassword;

    public ChangePassword(String at, String currentPassword, String newPassword) {
        this.at = at;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void changeCurrentPassword() throws Exception {
        currentPassword = Hashing.hashPassword(currentPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }

    public void changeNewPassword() throws Exception {
        newPassword = Hashing.hashPassword(newPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }
}
