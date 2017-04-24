/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.support.controller;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class BlockMember {
    private final String member_id;
    private final DBConnect dbc;

    public BlockMember(String member_id) throws ClassNotFoundException, SQLException {
        this.member_id = member_id;
        this.dbc = DB.getConnection();
    }
    
    public boolean block() throws SQLException{
        return dbc.blockMember(member_id);
    }
}
