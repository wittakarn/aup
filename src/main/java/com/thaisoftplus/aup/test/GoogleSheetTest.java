/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.test;

import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author witta
 */
public class GoogleSheetTest {

    public static void main(String[] args) {
        try {
            SheetManagement sheetManagement = SheetManagement.getInstance();
            Spreadsheet sp = sheetManagement.getSheets().spreadsheets().get("1gdBr89kb6aaLYTME5uaH-O4KgHAkmIJ0cIsJHT2uvVM").execute();
            List<Sheet> sheets = sp.getSheets();
            for (Sheet sheet : sheets) {
                System.out.println(sheet.getProperties().getTitle());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
