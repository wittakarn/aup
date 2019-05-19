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

    private static SheetManagement instance;

    public static SheetManagement getInstance() {
        if (instance == null) {
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

    public String getDataInColumn(String column, String tabName, int rowIndex) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                column,
                rowIndex,
                column,
                rowIndex);
        String value = read(range);
        return value == null ? "" : value;
    }

    public List<List<Object>> getDataInColumns(String columnStart, String columnEnd, int start, int end, String tabName) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                columnStart,
                start,
                columnEnd,
                end);
        return reads(range);
    }

    public BatchUpdateValuesResponse setDataInColumn(String column, String tabName, String[] value, int rowIndex) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                column,
                rowIndex,
                column,
                rowIndex);
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

    public BatchUpdateValuesResponse setDataInColumns(String columnStart, String columnEnd, int indexStart, int indexEnd, String tabName, List<List<Object>> values) throws IOException {
        String range = String.format("'%s'!%s%d:%s%d",
                tabName,
                columnStart,
                indexStart,
                columnEnd,
                indexEnd);
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
}
