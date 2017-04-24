/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.db;

import com.vaydeal.member.req.mod.NewPassword;
import com.vaydeal.member.req.mod.Register;
import com.vaydeal.member.req.mod.UpdateBankDetails;
import com.vaydeal.member.req.mod.UpdateProfile;
import com.vaydeal.member.resp.mod.MemberBankDetails;
import com.vaydeal.member.resp.mod.MemberReport;
import com.vaydeal.member.resp.mod.MyProfile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @company techvay
 * @author rifaie
 */
public class DBConnect {
    private Connection connect = null;
    private ResultSet rs;

    public DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://worddb.c8s8lmxdo3ux.ap-south-1.rds.amazonaws.com:3306/vaydeal", "worduser", "sooraj123");
    }

    /**
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        connect.close();
    }

    public int getNoMembers() throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM members");
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int getTotalCashout() throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT sum(amount_recieved) FROM member_report");
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int getNewMembers() throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM members WHERE member_date = CURDATE()");
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int checkMemberUname(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM member_logger WHERE member_id = ? OR member_email = ?");
        ps.setString(1, uname);
        ps.setString(2, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int checkNBMemberUname(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM member_logger_not_blocked WHERE member_id = ? OR member_email = ?");
        ps.setString(1, uname);
        ps.setString(2, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public List<String> getPassDSalt(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT salt,password,member_id,member_type FROM member_logger WHERE member_id = ? OR member_email = ?");
        List<String> proD = new ArrayList<>();
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            proD.add(rs.getString("salt"));
            proD.add(rs.getString("password"));
            proD.add(rs.getString("member_id"));
            proD.add(rs.getString("member_type"));
        }
        return proD;
    }

    public boolean updateLog(String uName) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE member_login SET last_logged = now(), log_count = log_count+1 WHERE member_id = ?");
        ps.setString(1, uName);
        int c = ps.executeUpdate();
        return c == 1;
    }

    public int changePassword(NewPassword req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE member_login SET password = ? ,salt = ? WHERE member_id = ?");
        ps.setString(1, req.getNewPassword());
        ps.setString(2, req.getSalt());
        ps.setString(3, req.getMember_id());
        return ps.executeUpdate();
    }

    public boolean checkRegEmail(String email) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM member_registrations WHERE member_reg_email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 0;
    }

    public boolean checkRegMobile(String mobile) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM member_registrations WHERE member_reg_mobile = ?");
        ps.setString(1, mobile);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 0;
    }

    public int addRegistrationRequest(Register req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("INSERT INTO member_registration(member_reg_name,member_reg_email,member_reg_mobile,member_reg_date) VALUES(?,?,?,CURDATE())");
        ps.setString(1, req.getName());
        ps.setString(2, req.getEmail());
        ps.setString(3, req.getMobile());
        int c = ps.executeUpdate();
        return c;
    }

    public boolean blockMember(String member_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE member SET member_status = 3 WHERE member_id = ?");
        ps.setString(1, member_id);
        int count = ps.executeUpdate();
        ps.close();
        return count == 1;
    }

    public boolean checkNBMemberID(String member_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM member_logger_not_blocked WHERE member_id = ? ");
        ps.setString(1, member_id);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public MemberReport getMemberReport(String member_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT * FROM member_report WHERE member_id = ?");
        ps.setString(1, member_id);
        rs = ps.executeQuery();
        MemberReport memReport;
        if (rs.next()) {
            memReport = new MemberReport(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15));
        } else {
            memReport = new MemberReport();
        }
        rs.close();
        ps.close();
        return memReport;
    }

    public MyProfile getMyProfile(String member_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT member_id,member_name,member_address1,member_address2,member_place,member_pin,member_city,member_email,member_mobile,member_type FROM member_report WHERE member_id = ?");
        ps.setString(1, member_id);
        rs = ps.executeQuery();
        MyProfile myProfile;
        if (rs.next()) {
            myProfile = new MyProfile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
        } else {
            myProfile = new MyProfile();
        }
        rs.close();
        ps.close();
        return myProfile;
    }

    public boolean updateProfile(UpdateProfile req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE member SET member_name = ?, member_address1 = ?, member_address2 = ?, member_pin = ?, member_place = ?, member_city = ? WHERE member _id = ?");
        ps.setString(1, req.getName());
        ps.setString(2, req.getAddress1());
        ps.setString(3, req.getAddress2());
        ps.setString(4, req.getPin());
        ps.setString(5, req.getPlace());
        ps.setString(6, req.getCity());
        ps.setString(7, req.getMember_id());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public MemberBankDetails getMemberBankDetails(String member_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT member_id,member_pan,bank_account_no,bank_name,bank_branch,bank_ifsc FROM member_profile WHERE member_id = ?");
        ps.setString(1, member_id);
        rs = ps.executeQuery();
        MemberBankDetails bankDeatails;
        if (rs.next()) {
            bankDeatails = new MemberBankDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        } else {
            bankDeatails = new MemberBankDetails();
        }
        rs.close();
        ps.close();
        return bankDeatails;
    }

    public boolean updateBankDetails(UpdateBankDetails req) throws SQLException {
        boolean f1 = updatePan(req);
        boolean f2 = updateBank(req);
        return f1&f2;
    }

    private boolean updatePan(UpdateBankDetails req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE member SET member_pan = ? WHERE member _id = ?");
        ps.setString(1, req.getPan());
        ps.setString(2, req.getMember_id());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    private boolean updateBank(UpdateBankDetails req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE member_bank SET bank_name = ?, bank_branch = ?, bank_account_no = ?, bank_ifsc = ? WHERE member _id = ?");
        ps.setString(1, req.getBank());
        ps.setString(2, req.getBranch());
        ps.setString(3, req.getAccountNo());
        ps.setString(4, req.getIfsc());
        ps.setString(5, req.getMember_id());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }
}
