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
import com.thaisoftplus.aup.domain.AsinUrl;
import com.thaisoftplus.aup.exception.EnptyRowException;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
            int threadSize = Integer.parseInt(ApplicationContext.configData.get(ApplicationContext.THREAD_SIZE));
            ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
            try {
                GoogleSheetBusiness business = new GoogleSheetBusiness();
                List<List<Object>> url = business.getDataInColumnsWithRetry(
                        ApplicationContext.LINK,
                        ApplicationContext.LINK,
                        SheetContext.startIndexOfBatch,
                        SheetContext.endIndexOfBatch,
                        ApplicationContext.DATA_SHEET_NAME,
                        1);

                if (url == null) {
                    SheetContext.isDone = true;
                } else {
                    SheetContext.urls = convertUrlColumnsToQueue(url);

                    business.updateOldPriceColumn();

                    try {
                        for (int i = 0; i < threadSize; i++) {
                            executorService.submit(new ServiceWorker(i + 1), "done").get();
                        }
                    } catch (Exception ex) {
                        logger.error("", ex);
                    }

                    executorService.shutdown();

                    business.updateAllProductDetailColumns();
                }

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
                    int random = (int) (Math.random() * 10 + 1);
                    Thread.sleep(random * 1000);
                } catch (InterruptedException ex) {
                    logger.error("", ex);
                }
                executeMainThread();
            } catch (IOException | EnptyRowException ex) {
                logger.error("", ex);
            }
        }
    }

    public static void executeMainThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Main());
        executorService.shutdown();
    }

    private void setNextSheet() {
        ApplicationContext.SHEET_INDEX += 1;
        SheetContext.currentIdex = ApplicationContext.START_ROW_INDEX;
        SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
        SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
    }

    private void setRerunSheet() {
        ApplicationContext.SHEET_INDEX = 0;
        SheetContext.currentIdex = ApplicationContext.START_ROW_INDEX;
        SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
        SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
    }

    private void setNextRun() {
        SheetContext.startIndexOfBatch = SheetContext.endIndexOfBatch + 1;
        SheetContext.endIndexOfBatch = SheetContext.startIndexOfBatch + SheetContext.CACHE_RANGE - 1;
    }

    private static Queue<AsinUrl> convertUrlColumnsToQueue(List<List<Object>> rows) {
        Queue queue = new ConcurrentLinkedQueue();
        for (int i = 0; i < rows.size(); i++) {
            queue.add(new AsinUrl(i, rows.get(i).get(0)));
        }
        return queue;
    }
}
