/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.controller;

import com.vaydeal.member.jsn.JSONParser;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.processreq.ProcessGetMyProfile;
import com.vaydeal.member.req.mod.GetMyProfile;
import com.vaydeal.member.resp.mod.GetMyProfileFailureResponse;
import com.vaydeal.member.resp.mod.GetMyProfileSuccessResponse;
import com.vaydeal.member.result.GetMyProfileResult;
import com.vaydeal.member.support.controller.UserActivities;
import com.vaydeal.member.validation.GetMyProfileValidation;
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
public class getMyProfile extends HttpServlet {

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
            GetMyProfile req = new GetMyProfile(at);
            GetMyProfileValidation reqV = new GetMyProfileValidation(req);
            reqV.validation();
            GetMyProfileResult reqR = JSONParser.parseJSONGMPR(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getMember_id(), "get_my_profile", req.getMember_type(), "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                response.setContentType("text/html");
                ProcessGetMyProfile process = new ProcessGetMyProfile(req);
                GetMyProfileSuccessResponse SResp = process.processRequest();
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
                GetMyProfileFailureResponse FResp = new GetMyProfileFailureResponse(reqR, validSubmission);
                out.write(FResp.toString());
            } else {
                //exception response
            }
            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(getMyProfile.class.getName()).log(Level.SEVERE, null, ex);
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
