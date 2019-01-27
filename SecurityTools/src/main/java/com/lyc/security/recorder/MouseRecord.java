package com.lyc.security.recorder;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MouseRecord implements Runnable {

    private WinUser.HHOOK hhk;

    private StringBuffer data1 = new StringBuffer();
    private StringBuffer data2 = new StringBuffer();
    private boolean sb = false;
    private static final Object lock = new Object();

    // 钩子回调函数
    private WinUser.LowLevelKeyboardProc mouseProc = new WinUser.LowLevelKeyboardProc() {

        public LRESULT callback(int nCode, WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
            // 输出按键值和按键时间
            if (nCode >= 0) {

                if (wParam.intValue() != 512) {

                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    String str = time + "# MOS #" + wParam.intValue() + "### X:" + event.vkCode + " # Y:" + event.scanCode + "\r\n";
                    System.out.println(str);

                    if (sb) {
                        data2.append(str);
                    } else {
                        data1.append(str);
                    }
                }
                //System.exit(0);
                //return new LRESULT(1);
            }
            return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
            //System.out.println(wParam.intValue() + "###" + event.vkCode + "#" + event.scanCode);
        }

    };

    public void run() {
        Thread t = new Thread(new WriteFile());
        t.start();

        setHookOn();
    }

    // 安装钩子
    public void setHookOn() {
        System.out.println("Mouse Recorder has launched");

        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_MOUSE_LL, mouseProc, hMod, 0);
        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                setHookOff();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }
        }
    }

    // 移除钩子并退出
    public void setHookOff() {
        System.out.println("Hook Off!");
        User32.INSTANCE.UnhookWindowsHookEx(hhk);
        System.exit(0);
    }

    //写文件的单独线程
    private class WriteFile implements Runnable {
        public void run() {
            while (true) {
                try {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String fileName = sdf1.format(new Date());

                    File file1 = new File(".//log//" + fileName + "_Mouse.txt");
                    File file2 = new File(".//log//" + fileName + "_General.txt");
                    File file3 = new File(".//log//");

                    if (!file3.exists()) {
                        file3.mkdirs();
                    }

                    if (!file2.exists()) {
                        file2.createNewFile();
                    }

                    if (!file1.exists()) {
                        file1.createNewFile();
                    }

                    BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1, true));
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter(file2, true));

                    if (data1.length() == 0 && data2.length() == 0) {
                        Thread.sleep(10000);
                        continue;
                    }

                    if (sb) {
                        if(data1.length()==0){
                            sb=false;
                            continue;
                        }
                        sb = false;
                        synchronized (lock) {
                            bw1.write(data1.toString());
                            bw2.write(data1.toString());

                            data1 = new StringBuffer();
                        }
                    } else {
                        if(data2.length()==0){
                            sb=true;
                            continue;
                        }
                        sb = true;
                        synchronized (lock) {
                            bw1.write(data2.toString());
                            bw2.write(data2.toString());

                            data2 = new StringBuffer();
                        }

                    }

                    bw1.flush();
                    bw2.flush();
                    bw1.close();
                    bw2.close();

                    Thread.sleep(10000);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
