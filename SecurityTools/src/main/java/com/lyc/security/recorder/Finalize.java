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

    private static Map<String,String> coding = new HashMap<String, String>();
    private static Vector<String> result = new Vector<String>();

    public String getValue(String number){
        return coding.get(number);
    }

    public static void execute(String path){
        File file = new File(path);
        try{
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine())!=null){
                String[] sub = line.split("#");
                String value;
                try {
                    value = coding.get(sub[2]);
                }catch (Exception e){
                    e.printStackTrace();
                    value = sub[2];
                }
                if(value==null||value.equals("null")){
                    value=sub[2];
                }
                String toadd = sub[0]+"#"+sub[1]+"#"+value;
                result.add(toadd);
            }

            isr.close();
            br.close();

            File file1 = new File(file.getParent(),new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_FinalKeyboard.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(file1,true));

            for(String s:result){
                bw.write(s+"\r\n");
                bw.flush();
            }

            bw.close();


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static {
        coding.put("27","[ESCAPE]");
        coding.put("112","[F1]");
        coding.put("113","[F2]");
        coding.put("114","[F3]");
        coding.put("115","[F4]");
        coding.put("116","[F5]");
        coding.put("117","[F6]");
        coding.put("118","[F7]");
        coding.put("119","[F8]");
        coding.put("120","[F9]");
        coding.put("121","[F10]");
        coding.put("122","[F11]");
        coding.put("123","[F12]");
        coding.put("44","[`]");
        coding.put("48","[0]");
        coding.put("49","[1]");
        coding.put("50","[2]");
        coding.put("51","[3]");
        coding.put("52","[4]");
        coding.put("53","[5]");
        coding.put("54","[6]");
        coding.put("55","[7]");
        coding.put("56","[8]");
        coding.put("57","[9]");
        coding.put("189","[-]");
        coding.put("187","[=]");
        coding.put("8","[BACK SPACE]");
        coding.put("44","[PRINT]");
        coding.put("145","[SCROLL LOCK]");
        coding.put("19","[PAUSE]");
        coding.put("45","[INSERT]");
        coding.put("36","[HOME]");
        coding.put("33","[PAGE UP]");
        coding.put("46","[DELETE]");
        coding.put("35","[END]");
        coding.put("34","[PAGE DOWN]");
        coding.put("9","[TAB]");
        coding.put("65","[A]");
        coding.put("66","[B]");
        coding.put("67","[C]");
        coding.put("68","[D]");
        coding.put("69","[E]");
        coding.put("70","[F]");
        coding.put("71","[G]");
        coding.put("72","[H]");
        coding.put("73","[I]");
        coding.put("74","[J]");
        coding.put("75","[K]");
        coding.put("76","[L]");
        coding.put("77","[M]");
        coding.put("78","[N]");
        coding.put("79","[O]");
        coding.put("80","[P]");
        coding.put("81","[Q]");
        coding.put("82","[R]");
        coding.put("83","[S]");
        coding.put("84","[T]");
        coding.put("85","[U]");
        coding.put("86","[V]");
        coding.put("87","[W]");
        coding.put("88","[X]");
        coding.put("89","[Y]");
        coding.put("90","[Z]");
        coding.put("20","[CAPS LOCK]");
        coding.put("160","[L-SHIFT]");
        coding.put("162","[L-CTRL]");
        coding.put("91","[L-WIN]");
        coding.put("164","[L-ALT]");
        coding.put("32","[SPACE]");
        coding.put("219","[");
        coding.put("221","]");
        coding.put("220","[\\]");
        coding.put("186","[;]");
        coding.put("222","[']");
        coding.put("13","[ENTER]");
        coding.put("188","[,]");
        coding.put("190","[.]");
        coding.put("191","[/]");
        coding.put("161","[R-SHIFT]");
        coding.put("165","[R-ALT]");
        coding.put("92","[R-WIN]");
        coding.put("93","[APPS]");
        coding.put("163","[R-CTRL]");
        coding.put("38","[UP]");
        coding.put("40","[DOWN]");
        coding.put("37","[LEFT]");
        coding.put("39","[RIGHT]");
        coding.put("96","[NUM 0]");
        coding.put("97","[NUM 1]");
        coding.put("98","[NUM 2]");
        coding.put("99","[NUM 3]");
        coding.put("100","[NUM 4]");
        coding.put("101","[NUM 5]");
        coding.put("102","[NUM 6]");
        coding.put("103","[NUM 7]");
        coding.put("104","[NUM 8]");
        coding.put("105","[NUM 9]");
        coding.put("110","[NUM .]");
        coding.put("144","[NUM LOCK]");
        coding.put("111","[NUM /]");
        coding.put("106","[NUM *]");
        coding.put("109","[NUM -]");
        coding.put("107","[NUM +]");
        coding.put("13","[NUM ENTER]");
        coding.put("175","[VOLUME UP]");
        coding.put("174","[VOLUME DOWN]");
        coding.put("173","[MUTE]");
        coding.put("179","[PLAY]");
        coding.put("178","[STOP]");
        coding.put("177","[LAST MUSIC]");
        coding.put("176","[NEXT MUSIC]");
    }
}
