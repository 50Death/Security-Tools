package com.lyc.security.recorder;

import java.io.*;

public class Main {


    public static void main(String[] args){

        boolean[] setting = new boolean[4];
        int[] delays = new int[2];

        System.out.println("Loading settings...");

        File file = new File("Settings.txt");
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String line=null;

            int counter=0;
            String[] s;
            while((line = br.readLine())!=null){

                s = line.split("[=|#]");

                if(s[1].equals("true")){
                    setting[counter] = true;
                }

                if(!s[2].equals("NO")){
                    delays[counter-2]=Integer.parseInt(s[2]);
                }
                counter++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e1){
            e1.printStackTrace();
            setting[0]=false;
            setting[1]=true;
            setting[2]=true;
            setting[3]=false;
            delays[0]=300;
            delays[1]=300;
            System.out.println("Load Failed. Set as Default...");
        }

        System.out.println("Load Successful");

        MouseRecord mouse;
        KeyboardRecord keyboard;
        ProcessRecord process;
        Screenshot screenshot;

        Thread[] t = new Thread[4];

        if(setting[0]){
            mouse = new MouseRecord();
            t[0] = new Thread(mouse);
            t[0].start();
        }

        if(setting[1]){
            keyboard = new KeyboardRecord();
            t[1] = new Thread(keyboard);
            t[1].start();
        }

        if(setting[2]){
            process = new ProcessRecord(delays[0]);
            t[2] = new Thread(process);
            t[2].start();
        }

        if(setting[3]){
            screenshot = new Screenshot(delays[1]);
            t[3] = new Thread(screenshot);
            t[3].start();
        }
    }
}
