package com.nursyah.bruteforcepassword.utils;

import java.util.Scanner;

public class Utils {
    private final String[] Combination = {"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ","0123456789", "`~!@#$%^&*()-_+={[}]|\\;:'\"<,>.?/ "};

    public String[] getCombination(){
        return Combination;
    }

    public String reverseString(String string){
        return new StringBuilder(string).reverse().toString();
    }

    public void showSystemInfo(){
        System.out.printf("available processor: %d\n", Runtime.getRuntime().availableProcessors());
        System.out.printf("os %s\n",System.getProperties().getProperty("os.name"));
    }

    public void showOptions(){
        System.out.println("Guess your password with bruteforce\n\n"+
                "java -jar bruteforce_password.jar <guesspassword>\n" +
                "java -jar bruteforce_password.jar <guesspassword> -i\n");
        System.exit(1);
    }

    public Results interactive(){
        Results res = new Results();
        System.out.println("Combination:\nAlphabetLowerCase (abcdefghijklmnopqrstuvwxyz): 0\n" +
                "AlphabetUpperCase (ABCDEFGHIJKLMNOPQRSTUVWXYZ): 1\n" +
                "Digit (0123456789): 2\n" +
                "Other (`~!@#$%^&*()-_+={[}]|\\;:'\"<,>.?/ ): 3\n");
        System.out.print("input your combination (use + to concat combination): ");

        for (String s : new Scanner(System.in).nextLine().split("\\+")) {
            res.Combination += Combination[Integer.parseInt(s)];
        }

        System.out.print("input your timeLimit (seconds): ");
        res.Timelimit = new Scanner(System.in).nextInt() * 1000;

        System.out.print("input your startLength: ");
        res.Startlength = new Scanner(System.in).nextInt();

        System.out.print("do you want to reverse Combination (y/N): ");
        if(new Scanner(System.in).nextLine().matches("[Yy].*")) res.Combination = reverseString(res.Combination);
        System.out.println("");

        return res;
    }
}
