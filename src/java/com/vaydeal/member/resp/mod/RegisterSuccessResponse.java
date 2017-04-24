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
public class RegisterSuccessResponse {
    private final String status;

    public RegisterSuccessResponse(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\"}";
    }
}


