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
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            try {
                SheetManagement sheetManagement = SheetManagement.getInstance();
                SheetContext.urls = GoogleSheetBusiness.convert2DListToQueue(sheetManagement.getDataInColumns(
                        ApplicationContext.LINK,
                        ApplicationContext.LINK,
                        SheetContext.startIndexOfBatch,
                        SheetContext.endIndexOfBatch,
                        ApplicationContext.DATA_SHEET_NAME)
                );

                GoogleSheetBusiness business = new GoogleSheetBusiness();
                business.updateOldPriceColumn();

                final Future<String> runFuture1 = executorService.submit(new ServiceWorker(1), "done");
                final Future<String> runFuture2 = executorService.submit(new ServiceWorker(2), "done");
                final Future<String> runFuture3 = executorService.submit(new ServiceWorker(3), "done");
                try {
                    runFuture1.get();
                    runFuture2.get();
                    runFuture3.get();
                } catch (Exception ex) {
                    logger.error("", ex);
                }

                executorService.shutdown();

                business.updateAllProductDetailColumns();

                if (SheetContext.isDone) {
                    SheetContext.isDone = false;
                    if (sheetSetting.size() - 1 > ApplicationContext.SHEET_INDEX) {
                        setNextSheet();
                    } else {
                        setRerunSheet();
                    }
                } else {
                    setNextRun();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    logger.error("", ex);
                }
                executeMainThread();
            } catch (IOException ex) {
                logger.error("", ex);
            }
        }
    }

    private void setNextSheet() {
        ApplicationContext.SHEET_INDEX += 1;
        SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
        SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
    }

    private void setRerunSheet() {
        ApplicationContext.SHEET_INDEX = 0;
        SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
        SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
    }

    private void setNextRun() {
        SheetContext.startIndexOfBatch = SheetContext.endIndexOfBatch + 1;
        SheetContext.endIndexOfBatch = SheetContext.startIndexOfBatch + SheetContext.CACHE_RANGE - 1;
    }

    public static void executeMainThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Main());
        executorService.shutdown();
    }
}
