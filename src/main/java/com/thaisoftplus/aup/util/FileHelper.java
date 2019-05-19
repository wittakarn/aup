/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author witta
 */
public class FileHelper {

    public static String getExistPaths(String[] paths) {
        for (String path : paths) {
            File f = new File(path);
            if (f.exists()) {
                return path;
            }
        }
        return null;
    }
}
