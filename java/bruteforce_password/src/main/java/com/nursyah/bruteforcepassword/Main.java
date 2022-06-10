package com.nursyah.bruteforcepassword;

import com.nursyah.bruteforcepassword.utils.BruteforceExtends;
import com.nursyah.bruteforcepassword.utils.Results;
import com.nursyah.bruteforcepassword.utils.Utils;

public class Main {
    private final String[] Combination = new Utils().getCombination();
    private long timeLimit =180 * 1000;
    private Thread t1;
    private BruteforceExtends bruteforce;

    public static void main(String []args) throws InterruptedException {
        Main main = new Main();
        main.terminal(args);
    }

    private void terminal(String []args) throws InterruptedException {
        if(args.length == 0 || args.length > 2) new Utils().showOptions();

        String guess = args[0];
        String combination = String.join("",Combination);
        combination = Combination[0]+Combination[1]+Combination[2];
        int startLengthPassword = 1;

        if(args.length == 2 && args[1].equals("-i")){
            Results res = new Utils().interactive();
            combination = res.Combination;
            startLengthPassword = res.Startlength;
            timeLimit = res.Timelimit;
        }

        new Utils().showSystemInfo();
        bruteforce = new BruteforceExtends(guess, combination);
        bruteforce.changeLength(startLengthPassword);

        t1 = new Thread(bruteforce);
        t1.start();

        waiting();
    }
    private void waiting() throws InterruptedException {
        long start = System.currentTimeMillis();

        while(true){
            System.out.printf("\rrun : %ds | ", (System.currentTimeMillis() - start) / 1000);
            System.out.printf("guess password: %s", bruteforce.showguessPassword());

            Thread.sleep(50);
            if(!t1.isAlive()){
                System.out.printf("\npassword: %s\n", bruteforce.showguessPassword());
                System.out.printf("took %dms\n", System.currentTimeMillis() - start);
                break;
            }
            if(System.currentTimeMillis() - start > timeLimit + 100){
                t1.stop();
                System.out.print("\nguess password is stopped because took so long time\n");
                break;
            }
        }
    }

}
