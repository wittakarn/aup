/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.rest.service;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.domain.ResponseMessage;
import com.thaisoftplus.aup.domain.StartRequest;
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
    @Path("/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage start(StartRequest startRequest) {
        ResponseMessage resp = new ResponseMessage();
        try {
            if (!ApplicationContext.isRunning) {
                ApplicationContext.isRunning = true;
                ApplicationContext.SHEET_INDEX = startRequest.getIndex();
                Executors.newFixedThreadPool(1).execute(new ServiceWorker());
                resp.setMessage(String.format("โปรแกรมเริ่มทำงาน"));
            } else {
                resp.setMessage(String.format("โปรแกรมทำงานอยู่"));
            }
        } catch (Exception e) {
            logger.error("", e);
            resp.setMessage(ExceptionUtils.getStackTrace(e));
        }
        return resp;
    }

    @POST
    @Path("/stop")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage stop() {
        ResponseMessage resp = new ResponseMessage();
        ApplicationContext.isRunning = false;
        resp.setMessage(String.format("โปรแกรมถูกสั่งให้หยุดทำงานแล้ว โปรดรอ 3 นาที ก่อนสั่งให้โปรแกรมทำงานใหม่"));
        return resp;
    }
}
