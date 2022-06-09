package com.nursyah.bruteforcepassword;

public class Bruteforce {
    private String password;
    private char[] combination;
    private int lengthPassword = 1;
    private String guessPassword = "";
    private boolean found = false;

    Bruteforce(String password, String combination){
        this.password = password;
        this.combination = combination.toCharArray();
    }

    private boolean checkPassword(){
        return password.equals(guessPassword);
    }

    public void bruteforce(){
        while (!found && !password.equals("")){
            bruteforceMain(lengthPassword, "");
            lengthPassword++;
        }
    }

    private void bruteforceMain(int length, String temp){
        if(length == 0){
            guessPassword = temp;
            if(checkPassword()) {
                found = true;
            }
            return;
        }
        for(int i=0; i < combination.length; i++){
            String newtemp = temp+combination[i];
            if(!found) bruteforceMain(length-1, newtemp);
        }
    }

    public void changeLength(int length){
        if(length<=0) return;
        lengthPassword = length;
    }
    public String showguessPassword(){
        return guessPassword;
    }
}
