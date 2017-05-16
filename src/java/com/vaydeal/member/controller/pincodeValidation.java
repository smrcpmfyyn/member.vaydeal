/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.member.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rifaie
 */
public class pincodeValidation extends HttpServlet {

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
            String [] states = {"Telangana","Andhra Pradesh","Pondicherry","Assam","Bihar","Chhattisgarh","Delhi","Gujarat","Daman and Diu","Dadra and Nagar Haveli","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Goa","Manipur","Mizoram","Nagaland","Tripura","Arunachal Pradesh","Meghalaya","Odisha","Chandigarh","Punjab","Rajasthan","Tamil Nadu","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar Islands","Sikkim"};
            String pin = request.getParameter("pin");
            HttpResponse<JsonNode> response2 = Unirest.post("https://pincode.p.mashape.com/").header("X-Mashape-Key", "8wqzH6NZOmmshkNOAYppCcFoVPIap1spqJFjsnbpK5CuvgFoqO").header("Content-Type", "application/json").header("Accept", "application/json").body("{\"searchBy\":\"pincode\",\"value\":"+pin+"}").asJson();
            if(response2.getBody().isArray()){
                JSONObject jsonObject = response2.getBody().getArray().getJSONObject(0);
                String taluk = jsonObject.getString("taluk");
                String district = jsonObject.getString("district");
                String state = states[jsonObject.getInt("state_id")-1];
                out.print("{\"status\":\"valid\",\"taluk\":\""+taluk+"\",\"district\":\""+district+"\",\"state\":\""+state+"\"}");
            }else{
                out.print("{\"status\":\"invalid\"}");
            }
        } catch (UnirestException | JSONException ex) {
            Logger.getLogger(pincodeValidation.class.getName()).log(Level.SEVERE, null, ex);
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
