package com.nursyah.bruteforcepassword.utils;

public class BruteforceExtends extends Bruteforce implements Runnable{

    public BruteforceExtends(String password, String combination){
        super(password, combination);
    }

    @Override
    public void run() {
        bruteforce();
    }
}
