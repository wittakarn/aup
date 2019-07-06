/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.servlet;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.ejb.ApiSettingSessionBeanLocal;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.EJB;
import com.thaisoftplus.aup.ejb.DataSettingSessionBeanLocal;
import com.thaisoftplus.aup.ejb.SheetSettingSessionBeanLocal;
import com.thaisoftplus.aup.util.FileHelper;

/**
 *
 * @author Wittakarn
 */
public class InitialConfig extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InitialConfig.class);

    @EJB
    private SheetSettingSessionBeanLocal sheetSettingSessionBeanLocal;
    @EJB
    private DataSettingSessionBeanLocal userSettingSessionBeanLocal;
    @EJB
    private ApiSettingSessionBeanLocal apiSettingSessionBeanLocal;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        processRequest(null, null);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HashMap<String, String> configData = ApplicationContext.configData;
        configData.clear();
        try {
            InputStream input = new FileInputStream(FileHelper.getExistPaths(new String[]{"/opt/aup/config/aup.properties", "D:\\aup\\config\\aup.properties"}));
            Properties reportProperties = new Properties();
            reportProperties.load(input);
            Enumeration<?> e = reportProperties.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = reportProperties.getProperty(key);
                configData.put(key, value);
                logger.info("HashMap {} : {}", key, value);
            }

            System.setProperty("webdriver.gecko.driver", ApplicationContext.getBrowserDriverPath());
            sheetSettingSessionBeanLocal.init();
            userSettingSessionBeanLocal.init();
            apiSettingSessionBeanLocal.init();
        } catch (Exception ex) {
            logger.error("", ex);
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
