/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.controller;

import com.vaydeal.member.jsn.JSONParser;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.processreq.ProcessForgotPassword;
import com.vaydeal.member.req.mod.ForgotPassword;
import com.vaydeal.member.resp.mod.ForgotPasswordFailureResponse;
import com.vaydeal.member.resp.mod.ForgotPasswordSuccessResponse;
import com.vaydeal.member.result.ForgotPasswordResult;
import com.vaydeal.member.support.controller.UserActivities;
import com.vaydeal.member.validation.ForgotPasswordValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class forgotPassword extends HttpServlet {

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
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            String mid = request.getParameter("uid");
            String email = request.getParameter("email");
            ForgotPassword req = new ForgotPassword(mid, email);
            ForgotPasswordValidation reqV = new ForgotPasswordValidation(req);
            reqV.validation();
            ForgotPasswordResult reqR = JSONParser.parseJSONFP(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getMid(), "forgot_password", req.getMembeType(), "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessForgotPassword process = new ProcessForgotPassword(req);
                ForgotPasswordSuccessResponse SResp = process.processRequest();
                process.closeConnection();
                out.write(SResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                ForgotPasswordFailureResponse FResp = new ForgotPasswordFailureResponse(req, reqR, validSubmission);
                out.write(FResp.toString());
            } else {
                //exception response
            }
            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(forgotPassword.class.getName()).log(Level.SEVERE, null, ex);
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
