/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.req.mod;

import com.vaydeal.member.hash.Hashing;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class NewPassword {
    private final String token;
    private String member_id;
    private String newPassword;
    private String salt;
    
    public NewPassword(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }
    
    /**
     * this will change the password
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public void changePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Random ran = new Random();
        int length = ran.nextInt(10) + 30;
        salt = Hashing.generateSalt(length);
        System.out.println(newPassword);
        System.out.println(salt);
        newPassword = Hashing.hashPassword(newPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }
}
