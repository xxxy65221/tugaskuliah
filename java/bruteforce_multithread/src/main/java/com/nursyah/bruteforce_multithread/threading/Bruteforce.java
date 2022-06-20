package com.nursyah.bruteforce_multithread.threading;

import java.util.ArrayList;

public class Bruteforce {
    final String pass, comb;
    final int core = Runtime.getRuntime().availableProcessors();
    final static String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    boolean found = false;
    ArrayList<String> combs;
    String[] allguess = new String[core];
    public String guess;

    public Bruteforce(String pass, String comb){
        this.pass = pass;
        this.comb = comb;
    }

    public void bruteforce() throws RuntimeException, InterruptedException {
        Thread []t = new Thread[core];
        for(int i=0; i< core; i++){
            int finalI = i;
            t[i] = new Thread(()->bruteforceCombination(combs.get(finalI), finalI));
            t[i].start();
        }

        long start = System.currentTimeMillis();
        while(!found && !pass.equals("")){
            System.out.printf("\rtime: %ds |", (System.currentTimeMillis()-start)/1000);
            for(int i=0; i < core; i++) System.out.printf(" guess%d: %s |",i+1, allguess[i]);
            Thread.sleep(50);
        }
        System.out.printf("\ntook time: %dms, password is: %s\n",System.currentTimeMillis()-start, guess);

        for(Thread ti: t){
            ti.stop();
        }
    }

    void bruteforceCombination(String combination, int num){
        int length = 1;

        while(!found){
            for(char c : combination.toCharArray()){
                String temp = ""+c;
                brute(temp, length, num);
            }
            length++;
        }
    }

    void brute(String temp,int length, int num){
        if(length == 0){
            allguess[num] = temp;
            if(temp.equals(pass)) {
                found=true;
                guess = temp;
            }
            return;
        }
        for(char c: c.toCharArray()){
            String newtemp = temp+c;
            if(!found)brute(newtemp, length-1, num);
        }
    }

    /*  create combination password */
    public Bruteforce init(){
        ArrayList<String> combs = new ArrayList<>();
        System.out.printf("core-cpu: %d\npassword: %s\ncombination: %s\n",core, pass,comb);

        /* divide combination according core */
        int i=0,n = comb.length() / core,j=n, temp = core;
        while(temp-- != 0){
            String str = comb.substring(i, j);
            if(temp == 0) str = comb.substring(i, j+(comb.length() % core));
            combs.add(str);

            i=j; j += n;
        }
        System.out.printf("%s\n\n",combs);
        this.combs = combs;
        return this;
    }

}
