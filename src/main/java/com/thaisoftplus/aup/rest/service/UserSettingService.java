/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.rest.service;

import com.thaisoftplus.aup.domain.ResponseMessage;
import com.thaisoftplus.aup.ejb.SettingSessionBean;
import com.thaisoftplus.aup.ejb.UserSettingSessionBeanLocal;
import com.thaisoftplus.aup.jpa.entity.UserSetting;
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

/**
 * REST Web Service
 *
 * @author witta
 */
@Stateless
@Path("usersetting")
public class UserSettingService {

    private static final Logger logger = LoggerFactory.getLogger(SettingSessionBean.class);
    @EJB
    private UserSettingSessionBeanLocal userSettingSessionBeanLocal;
    @Context
    private HttpServletRequest request;

    public UserSettingService() {
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public UserSetting getJson() {
        return "true".equals(request.getSession().getAttribute("isAdminPrivilege")) ? userSettingSessionBeanLocal.getLastedSetting() : null;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage createSetting(UserSetting setting) {
        ResponseMessage resp = new ResponseMessage();
        try {
            userSettingSessionBeanLocal.create(setting);
            resp.setMessage(String.format("บันทึกข้อมูลเรียบร้อยแล้ว"));
        } catch (Exception e) {
            logger.error("", e);
            resp.setMessage(ExceptionUtils.getStackTrace(e));
        }
        return resp;
    }
}
