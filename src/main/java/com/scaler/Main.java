package com.scaler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(1, 1);
        hm.put(7, 23);
        hm.put(7, 45);
        hm.remove(7);
        hm.display();

        HashMap<Integer, String> hm1 = new HashMap<>();
        hm1.put(1, "abc");
        hm1.put(7, "dlkjf");
        hm1.display();
    }
}