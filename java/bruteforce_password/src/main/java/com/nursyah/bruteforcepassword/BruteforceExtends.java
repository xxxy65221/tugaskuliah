package com.nursyah.bruteforcepassword;

import java.util.concurrent.atomic.AtomicBoolean;

public class BruteforceExtends extends Bruteforce implements Runnable{

    public BruteforceExtends(String password, String combination){
        super(password, combination);
    }

    @Override
    public void run() {
        bruteforce();
    }
}
