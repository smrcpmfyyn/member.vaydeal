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
public class GetNoMembersSuccessResponse {
    private final String status;
    private final int noOfMembers;

    public GetNoMembersSuccessResponse(String status) {
        this.status = status;
        noOfMembers = 0;
    }

    public GetNoMembersSuccessResponse(String status, int noOfMembers) {
        this.status = status;
        this.noOfMembers = noOfMembers;
    }
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\",\"nm\":\""+noOfMembers+"\"}";
    }
}