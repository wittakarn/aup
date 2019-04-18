/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.googlel.sheet;

import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.thaisoftplus.aup.context.ApplicationContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author witta
 */
public class SheetManagement extends Sheet implements Serializable {

    public static final int CACHE_RANGE = 2;
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

    public List<Object> getDataInColumn(String column, String tabName, int start, int end) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                column,
                start,
                column,
                end);
        List<List<Object>> rows = reads(range);
        return rows != null && rows.size() > 0 ? rows.get(0) : null;
    }

    public BatchUpdateValuesResponse setDataInColumn(String column, String tabName, String[] value) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                column,
                ROW_INDEX,
                column,
                ROW_INDEX);
        List<List<Object>> values = Arrays.asList(Arrays.asList(value));
        List<ValueRange> data = new ArrayList<ValueRange>();
        data.add(new ValueRange()
                .setRange(range)
                .setMajorDimension("COLUMNS")
                .setValues(values));
        BatchUpdateValuesRequest body = new BatchUpdateValuesRequest()
                .setValueInputOption("RAW")
                .setData(data);
        return getSheets().spreadsheets().values().batchUpdate(ApplicationContext.getSheetId(), body).execute();
    }

    public BatchUpdateValuesResponse setDataInColumn(String columnstart, String columnEnd, String tabName, List<List<Object>> values) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                columnstart,
                ROW_INDEX,
                columnEnd,
                ROW_INDEX);
        List<ValueRange> data = new ArrayList<ValueRange>();
        data.add(new ValueRange()
                .setRange(range)
                .setMajorDimension("COLUMNS")
                .setValues(values));
        BatchUpdateValuesRequest body = new BatchUpdateValuesRequest()
                .setValueInputOption("RAW")
                .setData(data);
        return getSheets().spreadsheets().values().batchUpdate(ApplicationContext.getSheetId(), body).execute();
    }

    public void updateNextIndex() {
        ROW_INDEX += 1;
    }

    public static int getRowIndex() {
        return ROW_INDEX;
    }

    public static void setRowIndex(int newRowIndex) {
        ROW_INDEX = newRowIndex;
    }
}
