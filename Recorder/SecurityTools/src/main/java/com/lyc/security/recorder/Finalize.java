package com.lyc.security.recorder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Tested at Windows10
 * Still some code need to fill
 */
public class Finalize {

    private static Map<String, String> K_coding = new HashMap<String, String>();
    private static Map<String, String> M_coding = new HashMap<String, String>();
    private static Vector<String> result1 = new Vector<String>();
    private static Vector<String> result2 = new Vector<String>();

    public String getValue(String number) {
        return K_coding.get(number);
    }

    public static void executeK(String path) {
        File file = new File(path);
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] sub = line.split("#");
                String value;
                try {
                    value = K_coding.get(sub[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                    value = sub[2];
                }
                if (value == null || value.equals("null")) {
                    value = sub[2];
                }
                String toadd = sub[0] + "#" + sub[1] + "#" + value;
                result1.add(toadd);
            }

            isr.close();
            br.close();

            File file1 = new File(file.getParent(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_FinalKeyboard.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true));

            for (String s : result1) {
                bw.write(s + "\r\n");
                bw.flush();
            }

            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void executeM(String path) {
        File file = new File(path);
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] sub = line.split("#");
                String value = M_coding.get(sub[2]);
                if (value == null || value.equals("null")) {
                    value = sub[2];
                }
                String toAdd = sub[0] + "#" + sub[1] + "#" + value + sub[3];
                result2.add(toAdd);
            }
            isr.close();
            br.close();

            File file1 = new File(file.getParent(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_FinalMouse.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true));

            for (String s : result1) {
                bw.write(s + "\r\n");
                bw.flush();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        M_coding.put("512", "[MOUSEMOVE]");
        M_coding.put("513", "[L_DOWN]");
        M_coding.put("514", "[L_UP]");
        M_coding.put("516", "[R_DOWN]");
        M_coding.put("517", "[R_UP]");
        M_coding.put("519", "[M_DOWN]");
        M_coding.put("520", "[M_UP]");
        M_coding.put("522", "[M_SCROLL]");
        M_coding.put("523", "[SIDE_DOWN]");
        M_coding.put("524", "[SIDE_UP]");

        K_coding.put("27", "[ESCAPE]");
        K_coding.put("112", "[F1]");
        K_coding.put("113", "[F2]");
        K_coding.put("114", "[F3]");
        K_coding.put("115", "[F4]");
        K_coding.put("116", "[F5]");
        K_coding.put("117", "[F6]");
        K_coding.put("118", "[F7]");
        K_coding.put("119", "[F8]");
        K_coding.put("120", "[F9]");
        K_coding.put("121", "[F10]");
        K_coding.put("122", "[F11]");
        K_coding.put("123", "[F12]");
        K_coding.put("192", "[`]");
        K_coding.put("48", "[0]");
        K_coding.put("49", "[1]");
        K_coding.put("50", "[2]");
        K_coding.put("51", "[3]");
        K_coding.put("52", "[4]");
        K_coding.put("53", "[5]");
        K_coding.put("54", "[6]");
        K_coding.put("55", "[7]");
        K_coding.put("56", "[8]");
        K_coding.put("57", "[9]");
        K_coding.put("189", "[-]");
        K_coding.put("187", "[=]");
        K_coding.put("8", "[BACK SPACE]");
        K_coding.put("44", "[PRINT]");
        K_coding.put("145", "[SCROLL LOCK]");
        K_coding.put("19", "[PAUSE]");
        K_coding.put("45", "[INSERT]");
        K_coding.put("36", "[HOME]");
        K_coding.put("33", "[PAGE UP]");
        K_coding.put("46", "[DELETE]");
        K_coding.put("35", "[END]");
        K_coding.put("34", "[PAGE DOWN]");
        K_coding.put("9", "[TAB]");
        K_coding.put("65", "[A]");
        K_coding.put("66", "[B]");
        K_coding.put("67", "[C]");
        K_coding.put("68", "[D]");
        K_coding.put("69", "[E]");
        K_coding.put("70", "[F]");
        K_coding.put("71", "[G]");
        K_coding.put("72", "[H]");
        K_coding.put("73", "[I]");
        K_coding.put("74", "[J]");
        K_coding.put("75", "[K]");
        K_coding.put("76", "[L]");
        K_coding.put("77", "[M]");
        K_coding.put("78", "[N]");
        K_coding.put("79", "[O]");
        K_coding.put("80", "[P]");
        K_coding.put("81", "[Q]");
        K_coding.put("82", "[R]");
        K_coding.put("83", "[S]");
        K_coding.put("84", "[T]");
        K_coding.put("85", "[U]");
        K_coding.put("86", "[V]");
        K_coding.put("87", "[W]");
        K_coding.put("88", "[X]");
        K_coding.put("89", "[Y]");
        K_coding.put("90", "[Z]");
        K_coding.put("20", "[CAPS LOCK]");
        K_coding.put("160", "[L-SHIFT]");
        K_coding.put("162", "[L-CTRL]");
        K_coding.put("91", "[L-WIN]");
        K_coding.put("164", "[L-ALT]");
        K_coding.put("32", "[SPACE]");
        K_coding.put("219", "[");
        K_coding.put("221", "]");
        K_coding.put("220", "[\\]");
        K_coding.put("186", "[;]");
        K_coding.put("222", "[']");
        K_coding.put("13", "[ENTER]");
        K_coding.put("188", "[,]");
        K_coding.put("190", "[.]");
        K_coding.put("191", "[/]");
        K_coding.put("161", "[R-SHIFT]");
        K_coding.put("165", "[R-ALT]");
        K_coding.put("92", "[R-WIN]");
        K_coding.put("93", "[APPS]");
        K_coding.put("163", "[R-CTRL]");
        K_coding.put("38", "[UP]");
        K_coding.put("40", "[DOWN]");
        K_coding.put("37", "[LEFT]");
        K_coding.put("39", "[RIGHT]");
        K_coding.put("96", "[NUM 0]");
        K_coding.put("97", "[NUM 1]");
        K_coding.put("98", "[NUM 2]");
        K_coding.put("99", "[NUM 3]");
        K_coding.put("100", "[NUM 4]");
        K_coding.put("101", "[NUM 5]");
        K_coding.put("102", "[NUM 6]");
        K_coding.put("103", "[NUM 7]");
        K_coding.put("104", "[NUM 8]");
        K_coding.put("105", "[NUM 9]");
        K_coding.put("110", "[NUM .]");
        K_coding.put("144", "[NUM LOCK]");
        K_coding.put("111", "[NUM /]");
        K_coding.put("106", "[NUM *]");
        K_coding.put("109", "[NUM -]");
        K_coding.put("107", "[NUM +]");
        //K_coding.put("13","[NUM ENTER]");
        K_coding.put("175", "[VOLUME UP]");
        K_coding.put("174", "[VOLUME DOWN]");
        K_coding.put("173", "[MUTE]");
        K_coding.put("179", "[PLAY]");
        K_coding.put("178", "[STOP]");
        K_coding.put("177", "[LAST MUSIC]");
        K_coding.put("176", "[NEXT MUSIC]");
    }
}
