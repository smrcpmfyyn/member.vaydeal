/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.intfc.validation;

/**
 *
 * @author rifaie
 */
public interface UpdateProfileValidator extends LogValidator{
    public String validateName() throws Exception;
    public String validateAddress1() throws Exception;
    public String validateAddress2() throws Exception;
    public String validatePlace() throws Exception;
    public String validatePin() throws Exception;
    public String validateCity() throws Exception;
    public String validatePage() throws Exception;
}
