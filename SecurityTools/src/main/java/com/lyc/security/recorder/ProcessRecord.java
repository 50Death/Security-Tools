package com.lyc.security.recorder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessRecord implements Runnable {
    private int second;

    public ProcessRecord(int second) {
        this.second = second;
    }

    public void run() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");

        while (true) {

            File file = new File(".//log//ProcessInfo_" + sdf2.format(new Date()) + "hrs.txt");

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                Process process = Runtime.getRuntime().exec("cmd.exe /c tasklist");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

                bw.write("Captured At " + sdf1.format(new Date()) + "\r\n");
                String dataLine = null;
                while ((dataLine = br.readLine()) != null) {
                    bw.write(new String(dataLine.getBytes(),"utf-8") + "\r\n");
                    bw.flush();
                }

                bw.write("\r\n\r\n\r\n\r\n\r\n");

                bw.close();

                br.close();

                System.out.println("Processes Captured!");

                Thread.sleep(second * 1000);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
