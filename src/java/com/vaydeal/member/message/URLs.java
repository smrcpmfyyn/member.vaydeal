/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.message;

/**
 * @company techvay
 * @author rifaie
 */
public class URLs {
    
    private static final String MEMBER_PASSWORD_GENERATION = "http://localhost:8080/member.vaydeal/resetPassword.html?token=";
    private static final String PIN_VALIDATION = "http://localhost:8080/member.vaydeal/pincodeValidation";
    
    public static String getMEMBER_PASSWORD_GENERATION() {
        return MEMBER_PASSWORD_GENERATION;
    }

    public static String getPIN_VALIDATION() {
        return PIN_VALIDATION;
    }
    
}
