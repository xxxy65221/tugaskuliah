package com.nursyah.bruteforcepassword;

import java.io.IOException;

public class Main {
    final static String AlphabetLowerCase = "abcdefghijklmnopqrstuvwxyz";
    final static String AlphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static String number = "0123456789";
    final static String other = "`~!@#$%^&*()-_+={[}]|\\;:'\"<,>.?/ ";
    Thread t1;
    final long waitingTime = 120 * 1000;
    Bruteforce password;
    long start;

    public static void main(String []args) throws InterruptedException, IOException {
        String password="";
        try {
            password = args[0];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("please add your password\njava -jar bruteforce_password.jar pass");
            System.exit(1);
        }

        Main main = new Main();
        main.password = new BruteforceExtends(password, AlphabetLowerCase);


        main.t1 = new Thread((Runnable) main.password);
        main.t1.start();

        main.waiting();
    }

    private void waiting() throws InterruptedException, IOException {
        start = System.currentTimeMillis();

        while(true){
            System.out.printf("\rrun : %ds | ", (System.currentTimeMillis() - start) / 1000);
            System.out.printf("guesspassword: %s", password.showguessPassword());


            Thread.sleep(50);
            if(!t1.isAlive()){
                System.out.printf("\npassword: %s\n", password.showguessPassword());
                System.out.printf("took %dms\n", System.currentTimeMillis() - start);
                break;
            }
            if(System.currentTimeMillis() - start >= waitingTime){
                t1.stop();
                System.out.printf("\nguess password is stopped because took so long time\n");
                break;
            }
        }
    }
}
