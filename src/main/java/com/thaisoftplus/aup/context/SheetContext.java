/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.context;

import com.thaisoftplus.aup.domain.SellerData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author witta
 */
public class SheetContext {
    
    public static final int CACHE_RANGE = 100;
    public static int currentIdex;
    public static int startIndexOfBatch;
    public static int endIndexOfBatch;

    public static Queue<Object> urls = new ConcurrentLinkedQueue();
    public static List<SellerData> sellersData = Collections.synchronizedList(new ArrayList());
}
