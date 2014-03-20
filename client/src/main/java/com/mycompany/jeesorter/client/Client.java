/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.jeesorter.client;

import com.mycompany.jeesorter.ISorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author han
 */
public class Client {
    static List<Long> results = new ArrayList<>();
    
    public static void main(String[] args) {
        for(int i = 0; i < 20; i++) {
            bench();
        }
        long total = results.stream().mapToLong(b -> b).sum();
        System.out.println("Avg: " + total / (results.size()) + "ms");
    }

    private static final Thread[] threads = new Thread[8];
    
    
    private static void bench() {
        Integer[] input = new Integer[100000];
        Random random = new Random();
        for(int i = 0; i < input.length; i++) 
            input[i] = random.nextInt();

        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    sort(input);
                    long duration = System.currentTimeMillis() - start;
                    results.add(duration);
                }
            });
            
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private static void sort(Integer[] input) {
        try {            
            InitialContext ic = new InitialContext();
            ISorter sorter = (ISorter) ic.lookup(ISorter.class.getName());
            Integer[] output = sorter.sort(input);
        } catch (NamingException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
