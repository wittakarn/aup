/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.thread;

import com.thaisoftplus.aup.business.GoogleSheetBusiness;
import com.thaisoftplus.aup.context.ApplicationContext;
import static com.thaisoftplus.aup.context.ApplicationContext.sheetSetting;
import com.thaisoftplus.aup.context.SheetContext;
import com.thaisoftplus.aup.context.ThreadContext;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author witta
 */
public class Main implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void run() {
        logger.info("Main thread start...");
        if (ApplicationContext.isRunning) {
            GoogleSheetBusiness business = new GoogleSheetBusiness();
            try {
                SheetManagement sheetManagement = SheetManagement.getInstance();
                business.updateOldPriceColumn();
                SheetContext.urls = GoogleSheetBusiness.convert2DListToQueue(sheetManagement.getDataInColumns(
                        ApplicationContext.LINK,
                        ApplicationContext.LINK,
                        SheetContext.startIndexOfBatch,
                        SheetContext.endIndexOfBatch,
                        ApplicationContext.DATA_SHEET_NAME)
                );
                ThreadContext.executorService.submit(new ServiceWorker());
                ThreadContext.executorService.submit(new ServiceWorker());
                ThreadContext.executorService.submit(new ServiceWorker());

                ThreadContext.executorService.shutdown();

                business.updateAllProductDetailColumns();
                if (sheetSetting.size() - 1 > ApplicationContext.SHEET_INDEX) {
                    setNextSheet();
                } else {
                    setRerunSheet();
                }
                setNextRun(1);
            } catch (IOException ex) {
                logger.error("", ex);
            }
        }
    }

    private void setNextSheet() {
        if (sheetSetting.size() - 1 > ApplicationContext.SHEET_INDEX) {
            ApplicationContext.SHEET_INDEX += 1;
            SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
            SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
        }
    }

    private void setRerunSheet() {
        ApplicationContext.SHEET_INDEX = 0;
        SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
        SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
    }

    private void setNextRun(int waitTime) {
        if (ApplicationContext.isRunning) {
            SheetContext.startIndexOfBatch = SheetContext.endIndexOfBatch + 1;
            SheetContext.endIndexOfBatch = SheetContext.startIndexOfBatch + SheetContext.CACHE_RANGE - 1;
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(new Main(), waitTime, TimeUnit.MILLISECONDS);
            scheduler.shutdown();
        }
    }
}
