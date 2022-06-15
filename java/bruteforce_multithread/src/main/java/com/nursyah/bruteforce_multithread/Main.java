package com.nursyah.bruteforce_multithread;

import com.nursyah.bruteforce_multithread.threading.Bruteforce;

public class Main {
    final static String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static String p;

    public static void main(String []args) throws InterruptedException {
        if(args.length != 1) {
            System.out.println("java -jar bruteforce_multithread.jar <pass>");
            return;
        }
            p=args[0];

        new Bruteforce(p, c).init().bruteforce();

    }



}
