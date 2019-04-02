/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.rest.service;

import com.thaisoftplus.aup.domain.ResponseMessage;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thaisoftplus.aup.ejb.DataSettingSessionBeanLocal;
import com.thaisoftplus.aup.jpa.entity.DataSetting;

/**
 * REST Web Service
 *
 * @author witta
 */
@Stateless
@Path("datasetting")
public class DataSettingService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataSettingService.class);
    
    @EJB
    private DataSettingSessionBeanLocal dataSettingSessionBeanLocal;
    @Context
    private HttpServletRequest request;

    public DataSettingService() {
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public DataSetting getJson() {
        return "true".equals(request.getSession().getAttribute("isAdminPrivilege")) ? dataSettingSessionBeanLocal.getLastedSetting() : null;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage createSetting(DataSetting setting) {
        ResponseMessage resp = new ResponseMessage();
        try {
            dataSettingSessionBeanLocal.create(setting);
            resp.setMessage(String.format("บันทึกข้อมูลเรียบร้อยแล้ว"));
        } catch (Exception e) {
            logger.error("", e);
            resp.setMessage(ExceptionUtils.getStackTrace(e));
        }
        return resp;
    }
}
