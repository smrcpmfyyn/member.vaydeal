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
public interface RPValidator extends Validator{
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateToken() throws Exception;
}
