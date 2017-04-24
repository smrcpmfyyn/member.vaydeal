/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.jsn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.mongo.mod.VerifyToken;
import com.vaydeal.member.result.GetMemberBankDetailsResult;
import com.vaydeal.member.result.GetMemberReportResult;
import com.vaydeal.member.result.GetMyProfileResult;
import com.vaydeal.member.result.LogResult;
import com.vaydeal.member.result.RPResult;
import com.vaydeal.member.result.RegisterResult;
import com.vaydeal.member.result.UpdateBankDetailsResult;
import com.vaydeal.member.result.UpdateProfileResult;
import java.io.IOException;

/**
 * @company techvay
 * @author rifaie
 */
public class JSONParser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static LogResult parseJSONLog(String reqv) throws IOException {
        LogResult res;
        res = MAPPER.readValue(reqv, LogResult.class);
        return res;
    }

    public static VerifyToken parseJSONVT(String vt) throws IOException {
        VerifyToken res;
        res = MAPPER.readValue(vt, VerifyToken.class);
        return res;
    }

    public static RPResult parseJSONRP(String reqv) throws IOException {
        RPResult res;
        res = MAPPER.readValue(reqv, RPResult.class);
        return res;
    }

    public static RegisterResult parseJSONRR(String reqv) throws IOException {
        RegisterResult res;
        res = MAPPER.readValue(reqv, RegisterResult.class);
        return res;
    }

    public static MemberID parseJSONMID(String mid) throws IOException {
        MemberID res;
        res = MAPPER.readValue(mid, MemberID.class);
        return res;
    }

    public static GetMemberReportResult parseJSONGMRR(String reqv) throws IOException {
        GetMemberReportResult res;
        res = MAPPER.readValue(reqv, GetMemberReportResult.class);
        return res;
    }

    public static GetMyProfileResult parseJSONGMPR(String reqv) throws IOException {
        GetMyProfileResult res;
        res = MAPPER.readValue(reqv, GetMyProfileResult.class);
        return res;
    }

    public static UpdateProfileResult parseJSONUPR(String reqv) throws IOException {
        UpdateProfileResult res;
        res = MAPPER.readValue(reqv, UpdateProfileResult.class);
        return res;
    }

    public static GetMemberBankDetailsResult parseJSONGMBD(String reqv) throws IOException {
        GetMemberBankDetailsResult res;
        res = MAPPER.readValue(reqv, GetMemberBankDetailsResult.class);
        return res;
    }

    public static UpdateBankDetailsResult parseJSONUBDR(String reqv) throws IOException {
        UpdateBankDetailsResult res;
        res = MAPPER.readValue(reqv, UpdateBankDetailsResult.class);
        return res;
    }
}
