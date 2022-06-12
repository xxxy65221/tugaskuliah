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
            switch (core){
                case 1:
                    System.out.printf("\rtime: %ds | guess1: %s",(System.currentTimeMillis()-start)/1000, allguess[0]);
                    break;
                case 2:
                    System.out.printf("\rtime: %ds | guess1: %s guess2: %s",(System.currentTimeMillis()-start)/1000, allguess[0], allguess[1]);
                    break;
                case 4:
                    System.out.printf("\rtime: %ds | guess1: %s guess2: %s guess3: %s guess4: %s",
                            (System.currentTimeMillis()-start)/1000, allguess[0], allguess[1], allguess[2], allguess[3]);
                    break;
                case 8:
                    System.out.printf("\rtime: %ds | guess1: %s guess2: %s guess3: %s guess4: %s  guess5: %s guess6: %s guess7: %s guess8: %s",
                            (System.currentTimeMillis()-start)/1000, allguess[0], allguess[1], allguess[2], allguess[3], allguess[4], allguess[5],
                            allguess[6], allguess[7]);
                    break;
                case 16:
                    System.out.printf("\rtime: %ds | guess1: %s guess2: %s guess3: %s guess4: %s  guess5: %s guess6: %s guess7: %s guess8: %s" +
                                    "guess9: %s guess10: %s guess11: %s guess12: %s  guess13: %s guess14: %s guess15: %s guess16: %s",
                            (System.currentTimeMillis()-start)/1000, allguess[0], allguess[1], allguess[2], allguess[3], allguess[4], allguess[5],
                            allguess[6], allguess[7], allguess[8], allguess[9], allguess[10], allguess[11], allguess[12], allguess[13],
                            allguess[14], allguess[15]);
                    break;
            }
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
