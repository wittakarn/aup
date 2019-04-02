/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.rest.service;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.domain.ResponseMessage;
import com.thaisoftplus.aup.thread.ServiceWorker;
import java.util.concurrent.Executors;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
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
@Path("controller")
public class ControllerService {

    private static final Logger logger = LoggerFactory.getLogger(ControllerService.class);

    public ControllerService() {
    }

    @POST
    @Path("/processrequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage processRequest(String command) {
        ResponseMessage resp = new ResponseMessage();
        try {
            if ("start".equals(command)) {
                if (!ApplicationContext.isRunning) {
                    ApplicationContext.isRunning = true;
                    Executors.newFixedThreadPool(1).execute(new ServiceWorker());
                    resp.setMessage(String.format("โปรแกรมเริ่มทำงาน"));
                } else {
                    resp.setMessage(String.format("โปรแกรมทำงานอยู่"));
                }
            } else {
                ApplicationContext.isRunning = false;
                resp.setMessage(String.format("โปรแกรมหยุดทำงาน โปรดรอ 3 นาทีก่อนที่จะกดสั่งทำงานอีกครั้ง"));
            }
        } catch (Exception e) {
            logger.error("", e);
            resp.setMessage(ExceptionUtils.getStackTrace(e));
        }
        return resp;
    }
}
