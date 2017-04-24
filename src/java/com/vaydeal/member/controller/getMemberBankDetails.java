/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.controller;

import com.vaydeal.member.jsn.JSONParser;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.processreq.ProcessGetMemberBankDetails;
import com.vaydeal.member.req.mod.GetMemberBankDetails;
import com.vaydeal.member.resp.mod.GetMemberBankDetailsFailureResponse;
import com.vaydeal.member.resp.mod.GetMemberBankDetailsSuccessResponse;
import com.vaydeal.member.result.GetMemberBankDetailsResult;
import com.vaydeal.member.support.controller.UserActivities;
import com.vaydeal.member.validation.GetMemberBankDetailsValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class getMemberBankDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            GetMemberBankDetails req = new GetMemberBankDetails(at);
            GetMemberBankDetailsValidation reqV = new GetMemberBankDetailsValidation(req);
            reqV.validation();
            GetMemberBankDetailsResult reqR = JSONParser.parseJSONGMBD(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getMember_id(), "get_member_bank_details", "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessGetMemberBankDetails process = new ProcessGetMemberBankDetails(req);
                GetMemberBankDetailsSuccessResponse SResp = process.processRequest();
                process.closeConnection();
                ck.setValue(SResp.getAccessToken());
                response.addCookie(ck);
                out.write(SResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                    ua.setEntryStatus("invalid");
                } else {
                    ua.setEntryStatus("invalid");
                }

                GetMemberBankDetailsFailureResponse FResp = new GetMemberBankDetailsFailureResponse(reqR, validSubmission);
                out.write(FResp.toString());
            } else {
                //exception response
            }
            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(getMemberBankDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
