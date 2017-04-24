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
public interface UpdateBankDetailsValidator extends LogValidator{
    public String validatePan() throws Exception;
    public String validateAccountNo() throws Exception;
    public String validateBank() throws Exception;
    public String validateBranch() throws Exception;
    public String validateIfsc() throws Exception;
}
