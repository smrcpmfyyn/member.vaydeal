/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.controller;

import com.vaydeal.member.jsn.JSONParser;
import com.vaydeal.member.message.CorrectMsg;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.processreq.ProcessRegister;
import com.vaydeal.member.req.mod.Register;
import com.vaydeal.member.resp.mod.RegisterFailureResponse;
import com.vaydeal.member.resp.mod.RegisterSuccessResponse;
import com.vaydeal.member.result.RegisterResult;
import com.vaydeal.member.validation.RegisterValidation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class register extends HttpServlet {

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
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mob");
            Register req = new Register(name, email, mobile);
            RegisterValidation reqV = new RegisterValidation(req);
            reqV.validation();
            RegisterResult reqR = JSONParser.parseJSONRR(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if(validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                ProcessRegister process = new ProcessRegister(req);
                RegisterSuccessResponse npSResp = process.processRequest();
                process.closeConnection();
                out.print(npSResp.toString());
            }else if(validSubmission.startsWith(ErrMsg.ERR_ERR)){
                RegisterFailureResponse FResp = new RegisterFailureResponse(reqR, validSubmission,req);
                out.print(FResp);
            }else{
                // Exception Response
            }
        } catch (Exception ex) {
            //Eception Response
            ex.printStackTrace();
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
