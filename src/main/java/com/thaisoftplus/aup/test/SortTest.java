/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.test;

import com.thaisoftplus.aup.domain.SellerData;
import static com.thaisoftplus.aup.test.SortTest.numbers;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author witta
 */
public class SortTest {

    public static int index = 0;
    public static Queue<Integer> numbers = new ConcurrentLinkedQueue();
    public static List<Integer> resultNumbers = Collections.synchronizedList(new ArrayList<>());
    public static ExecutorService exService;

    public static void main(String[] args) {
        SellerData s1 = new SellerData();
        s1.setRowIndex(10);
        SellerData s2 = new SellerData();
        s2.setRowIndex(1);
        SellerData s3 = new SellerData();
        s3.setRowIndex(7);
        SellerData s4 = new SellerData();
        s4.setRowIndex(101);
        SellerData s5 = new SellerData();
        s5.setRowIndex(17);

        List<SellerData> sellers = Collections.synchronizedList(new ArrayList());
        sellers.add(s1);
        sellers.add(s2);
        sellers.add(s3);
        sellers.add(s4);
        sellers.add(s5);
        
        Collections.sort(sellers);
        
        for (SellerData seller : sellers) {
            System.out.println("seller.getRowIndex() = " + seller.getRowIndex());
        }
    }
}
