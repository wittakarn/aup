/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.googlel.sheet;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.thaisoftplus.aup.context.ApplicationContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author witta
 */
public class SheetManagement extends Sheet implements Serializable {

    private static int ROW_INDEX;
    private static SheetManagement instance;

    public static SheetManagement getInstance() {
        if (instance == null) {
            ROW_INDEX = ApplicationContext.START_ROW_INDEX;
            instance = new SheetManagement();
        }
        return instance;
    }

    public List<List<Object>> reads(String range) throws IOException {
        ValueRange response = getSheets().spreadsheets().values()
                .get(ApplicationContext.getSheetId(), range)
                .execute();
        return response.getValues();
    }

    public String read(String range) throws IOException {
        String rawData = null;
        List<List<Object>> rows = reads(range);
        if (rows != null && rows.size() > 0) {
            List<Object> columns = rows.get(0);
            if (columns != null && columns.size() > 0) {
                Object column = columns.get(0);
                if (column != null) {
                    rawData = column.toString();
                }
            }
        }
        return rawData;
    }

    public String getDataInColumn(String column, String tabName) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                column,
                ROW_INDEX,
                column,
                ROW_INDEX);
        String value = read(range);
        return value == null ? "" : value;
    }

    public void updateNextIndex() throws IOException {
        ROW_INDEX += 1;
    }
}
