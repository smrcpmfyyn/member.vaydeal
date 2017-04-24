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
public class UpdateProfile {
    private final String at;
    private String member_id;
    private String member_type;
    private final String name;
    private final String address1;
    private final String address2;
    private final String pin;
    private final String place;
    private final String city;

    public UpdateProfile(String at, String name, String address1, String address2, String pin, String place, String city) {
        this.at = at;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.pin = pin;
        this.place = place;
        this.city = city;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
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

    public String getName() {
        return name;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPin() {
        return pin;
    }

    public String getPlace() {
        return place;
    }

    public String getCity() {
        return city;
    }
    
}
