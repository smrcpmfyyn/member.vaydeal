/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.controller;

import com.vaydeal.member.jsn.JSONParser;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.message.ResponseMsg;
import com.vaydeal.member.processreq.ProcessLog;
import com.vaydeal.member.req.mod.Login;
import com.vaydeal.member.resp.mod.LogFailureResponse;
import com.vaydeal.member.resp.mod.LogSuccessResponse;
import com.vaydeal.member.result.LogResult;
import com.vaydeal.member.validation.LoginValidation;
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
public class login extends HttpServlet {

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
            String uname = request.getParameter("uname");
            String password = request.getParameter("pass");
            Login req = new Login(uname, password);
            LoginValidation reqV = new LoginValidation(req);
            reqV.validation();
            LogResult reqR = JSONParser.parseJSONLog(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessLog process = new ProcessLog(req);
                LogSuccessResponse SResp = process.processRequest();
                process.closeConnection();
                Cookie ck = new Cookie("at", SResp.getAccessToken());
                response.addCookie(ck);         
                //check for type and change the page accordingly if neccessary
                if (SResp.getStatus().equals(ResponseMsg.RESP_OK)) {
                    out.write(SResp.toString());
                } else {
                    //Exception Response
                }
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                LogFailureResponse FResp = new LogFailureResponse(reqR, validSubmission);
                out.write(FResp.toString());
            } else {
                //exception response
            }
            out.close();
            out.flush();
        } catch (Exception ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
