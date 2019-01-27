package com.lyc.security.recorder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot implements Runnable {
    Dimension screenSize;
    Rectangle rect;
    Robot robot;
    int delay;

    public Screenshot(int second) {
        this.delay = second;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        rect = new Rectangle(screenSize);
        try {
            robot = new Robot();
        } catch (java.awt.AWTException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            BufferedImage img = robot.createScreenCapture(rect);

            String time = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());

            File file = new File(".//Screenshot//Screenshot_" + time + ".png");

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try {
                if (ImageIO.write(img, "png", file)) {
                    System.out.println("Screenshot Captured!");
                } else {
                    System.out.println("Screenshot Failed");
                }

                Thread.sleep(delay * 1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e1) {
                e1.fillInStackTrace();
            }

        }
    }
}
