/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.test;

import static com.thaisoftplus.aup.test.ThreadTest.numbers;
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
public class ThreadTest {

    public static int index = 0;
    public static Queue<Integer> numbers = new ConcurrentLinkedQueue();
    public static List<Integer> resultNumbers = Collections.synchronizedList(new ArrayList<>());
    public static ExecutorService exService;

    public static void main(String[] args) {
        try {
            for (int i = 1; i < 10001; i++) {
                numbers.add(i);
            }

            System.out.println("numbers.size() = " + numbers.size());

            exService = Executors.newFixedThreadPool(3);
            final Future<String> runFuture1 = exService.submit(new Worker(), "done");
            final Future<String> runFuture2 = exService.submit(new Worker(), "done");
            final Future<String> runFuture3 = exService.submit(new Worker(), "done");

            System.out.println("runFuture1.get() = " + runFuture1.get());
            System.out.println("runFuture2.get() = " + runFuture2.get());
            System.out.println("runFuture3.get() = " + runFuture3.get());

            exService.shutdown();

            Collections.sort(resultNumbers);
            boolean isCorrect = true;
            for (int i = 1; i < 10001; i++) {
                isCorrect = resultNumbers.get(i - 1) == i;
                if (!isCorrect) {
                    System.out.println("resultNumbers.get(i)/i = " + resultNumbers.get(i) + "/" + i);
                    break;
                }
            }

            System.out.println("isCorrect = " + isCorrect);
        } catch (Exception ex) {
            StringWriter writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(writer, true));
            System.out.println("main exeption stack is :\n" + writer.toString());
        }
    }
}

class Worker implements Runnable {

    @Override
    public void run() {
        getData();
    }

    private void getData() {
        try {
            Integer number = ThreadTest.numbers.poll();
            if (number != null) {
                ThreadTest.resultNumbers.add(number);
                ThreadTest.index = ThreadTest.index + 1;
                getData();
            }
        } catch (Exception ex) {
            StringWriter writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(writer, true));
            System.out.println("run exeption stack is :\n" + writer.toString());
        }
    }
}
