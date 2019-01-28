package com.lyc.security.recorder;

public class Run {
    public static void main(String[] args){
        new Thread(new MouseRecord()).start();
    }
}
