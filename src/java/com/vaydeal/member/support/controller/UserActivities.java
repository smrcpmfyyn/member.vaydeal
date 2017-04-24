/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.support.controller;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.MongoConnect;

/**
 * @company techvay
 * @author rifaie
 */
public class UserActivities {
    private final String member_id;
    private final String activity;
    private final String dateTime;
    private String entryStatus;
    private MongoConnect mdbc;

    public UserActivities(String user_id, String activity, String entryStatus) {
        this.member_id = user_id;
        this.activity = activity;
        this.dateTime = ""+System.currentTimeMillis();
        this.entryStatus = entryStatus;
    }
    
    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
    
    public void addActivity() throws Exception{
        this.mdbc = DB.getMongoConnection();
        mdbc.addActivity(this.toString());
        mdbc.closeConnection();
    }

    @Override
    public String toString() {
        return "{\"member_id\":\"" + member_id +   "\", \"activity\":\"" + activity + "\", \"dateTime\":\"" + dateTime + "\", \"entryStatus\":\"" + entryStatus + "\"}";
    }
    
}
