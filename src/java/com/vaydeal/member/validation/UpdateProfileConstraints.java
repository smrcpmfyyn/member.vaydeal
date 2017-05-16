/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.validation;

import com.vaydeal.member.db.DB;
import com.vaydeal.member.db.DBConnect;
import com.vaydeal.member.db.MongoConnect;
import com.vaydeal.member.intfc.validation.UpdateProfileValidator;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.message.URLs;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.regx.RegX;
import com.vaydeal.member.req.mod.UpdateProfile;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import org.json.JSONObject;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateProfileConstraints implements UpdateProfileValidator {

    private final UpdateProfile req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateProfileConstraints(UpdateProfile req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String name = req.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }

    @Override
    public String validateAddress1() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS1;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAddress1();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS1;
        }
        return valid;
    }

    @Override
    public String validateAddress2() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS2;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAddress2();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS2;
        }
        return valid;
    }

    @Override
    public String validatePin() throws Exception {
        String valid = ErrMsg.ERR_PIN;
        String regX = RegX.REGX_DIGIT;
        String param = req.getPin();
        if (validate(param, regX)) {
            if (dbc.checkPinChange(param, req.getMember_id())) {
                if (checkPin()) {
                    valid = CorrectMsg.CORRECT_PIN;
                }
            } else {
                valid = CorrectMsg.CORRECT_PIN;
            }
        }
        return valid;
    }

    @Override
    public String validatePlace() throws Exception {
        String valid = ErrMsg.ERR_PLACE;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String param = req.getPlace();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_PLACE;
        }
        return valid;
    }

    @Override
    public String validateCity() throws Exception {
        String valid = ErrMsg.ERR_CITY;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String param = req.getPlace();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_CITY;
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        MemberID mem = mdbc.getMemberID(at);
        if (!mem.getMember_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            mem.setMember_update_status(dbc.getMemberUpdateStatus(mem.getMember_id()));
            if (dbc.checkNBMemberID(mem.getMember_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setMember_id(mem.getMember_id());
                req.setMember_type(mem.getMember_type());
                req.setUpdateStatus(mem.getMember_update_status());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

    public boolean checkPin() throws Exception {
        String targetURL = URLs.getPIN_VALIDATION();
        String urlParameters = "pin=" + URLEncoder.encode("" + req.getPin(), "UTF-8");
        String response = excutePost(targetURL, urlParameters);
        return processResponse(response);

    }

    private String excutePost(String targetURL, String urlParameters) throws Exception {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            throw e;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private boolean processResponse(String response) throws Exception {
        if (response.contains("valid")) {
            JSONObject obj = new JSONObject(response);
            req.setTaluk(obj.getString("taluk"));
            req.setDistrict(obj.getString("district"));
            req.setState(obj.getString("state"));
            req.setCountry("India");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String validatePage() throws Exception {
        String param = req.getPage();
        String valid = ErrMsg.ERR_PAGE;
        if (param.equals("pu") && req.getUpdateStatus().startsWith("update")) {
            valid = CorrectMsg.CORRECT_PAGE;
        } else if (param.equals("ad") && req.getUpdateStatus().startsWith("not")) {
            valid = CorrectMsg.CORRECT_PAGE;
        }
        return valid;
    }
}
