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
public class GetNewMembersSuccessResponse {
    private final String status;
    private final int newMembers;

    public GetNewMembersSuccessResponse(String status) {
        this.status = status;
        newMembers = 0;
    }

    public GetNewMembersSuccessResponse(String status, int newMembers) {
        this.status = status;
        this.newMembers = newMembers;
    }
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\",\"nm\":\""+newMembers+"\"}";
    }
}
