package config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wanghongxiang on 16/6/29.
 */
public class SystemLogger {

 private static Logger logger = Logger.getLogger(SystemLogger.class);

    public static Logger getLogger(){return logger;}

    public static String setErrortoLog(Throwable e){
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw =  new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();

            return sw.toString();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }





}
