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
public class GetTotalCashoutSuccessResponse {
    private final String status;
    private final int totalCashout;

    public GetTotalCashoutSuccessResponse(String status) {
        this.status = status;
        totalCashout = 0;
    }

    public GetTotalCashoutSuccessResponse(String status, int totalCashout) {
        this.status = status;
        this.totalCashout = totalCashout;
    }
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\",\"tc\":\""+totalCashout+"\"}";
    }
}
