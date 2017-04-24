/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.intfc.processreq;

/**
 *
 * @author rifaie
 */
public interface RegisterProcessor extends Processor{
    public boolean addRegistrationRequest() throws Exception;
}
